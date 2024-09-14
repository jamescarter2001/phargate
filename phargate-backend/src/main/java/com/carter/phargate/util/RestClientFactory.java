package com.carter.phargate.util;

import com.google.common.collect.ImmutableList;
import lombok.experimental.UtilityClass;

import java.time.Duration;

@UtilityClass
public class RestClientFactory {

    public static RestClient newClient() {
        return new OkHttpRestClient();
    }

    public static RestClient newRateLimitedClient(long maxRequests, Duration timeFrame) {
        return newRateLimitedClient(maxRequests, timeFrame, false);
    }

    public static RestClient newRateLimitedClient(long maxRequests, Duration timeFrame, boolean allowBlocking) {
        return new OkHttpRestClient(ImmutableList.of(new RateLimitInterceptor(maxRequests, timeFrame, allowBlocking)));
    }

}
