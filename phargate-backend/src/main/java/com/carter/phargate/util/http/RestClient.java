package com.carter.phargate.util.http;

import java.util.Map;

public interface RestClient {
    <ResponseT> ResponseT get(String url, Class<ResponseT> clazz);
    <ResponseT> ResponseT get(String url, Class<ResponseT> clazz, Map<String, String> headers);
    <BodyT, ResponseT> ResponseT post(String url, BodyT bodyT, Class<ResponseT> clazz);
    <BodyT, ResponseT> ResponseT post(String url, BodyT bodyT, Class<ResponseT> clazz, Map<String, String> headers);
}
