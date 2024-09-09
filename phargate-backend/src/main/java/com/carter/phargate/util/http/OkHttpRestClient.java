package com.carter.phargate.util.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
class OkHttpRestClient implements RestClient {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final OkHttpClient client;

    public OkHttpRestClient() {
        this(Collections.emptyList());
    }

    public OkHttpRestClient(List<Interceptor> interceptors) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        interceptors.forEach(clientBuilder::addInterceptor);

        this.client = clientBuilder.build();
    }

    public <ResponseT> ResponseT get(String url, Class<ResponseT> clazz) {
        return get(url, clazz, Collections.emptyMap());
    }

    @Override
    public <ResponseT> ResponseT get(String url, Class<ResponseT> clazz, Map<String, String> headers) {
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(headers))
                .build();

        try {
            String response = client.newCall(request)
                    .execute()
                    .body()
                    .string();

            return mapper.readValue(response, clazz);

        } catch (IOException ex) {
            log.error("Error handling GET request: {} {}", url, ex.toString());
        }

        return null;
    }

    @Override
    public <BodyT, ResponseT> ResponseT post(String url, BodyT bodyT, Class<ResponseT> clazz) {
        return null;
    }

    @Override
    public <BodyT, ResponseT> ResponseT post(String url, BodyT bodyT, Class<ResponseT> clazz, Map<String, String> headers) {
        return null;
    }
}
