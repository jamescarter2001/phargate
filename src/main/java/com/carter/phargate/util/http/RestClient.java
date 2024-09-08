package com.carter.phargate.util.http;

import java.util.Map;

public interface RestClient {
    <ResponseT> ResponseT get(String url, Class<ResponseT> clazz);
    <ResponseT> ResponseT get(String url, Map<String, String> headers, Class<ResponseT> clazz);
    <BodyT, ResponseT> ResponseT post(String url, BodyT bodyT, Class<ResponseT> clazz);
    <BodyT, ResponseT> ResponseT post(String url, Map<String, String> headers, BodyT bodyT, Class<ResponseT> clazz);
}
