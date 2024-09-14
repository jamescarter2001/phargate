package com.carter.phargate.util;

import java.util.Map;

public interface RestClient {
    <ResponseT> ResponseT get(String url, Class<ResponseT> clazz);
    <ResponseT> ResponseT get(String url, Class<ResponseT> clazz, Map<String, String> headers);
    <BodyT, ResponseT> ResponseT post(String url, BodyT body, Class<ResponseT> clazz);
    <BodyT, ResponseT> ResponseT post(String url, BodyT body, Class<ResponseT> clazz, Map<String, String> headers);
}
