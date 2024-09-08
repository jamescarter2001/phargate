package com.carter.phargate.util.http;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RestClientFactory {

    public static RestClient newClient() {
        return new OkHttpRestClient();
    }

}
