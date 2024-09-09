package com.carter.phargate.util.http;

import lombok.RequiredArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.Duration;

@RequiredArgsConstructor
public class RateLimitInterceptor implements Interceptor {

    private final Duration rateLimit;
    private long lastRequestTime = 0;

    @NotNull
    @Override
    public synchronized Response intercept(@NotNull Chain chain) throws IOException {
        long currentTime = System.currentTimeMillis();
        long timeSinceLastRequest = currentTime - lastRequestTime;

        long rateLimitTime = rateLimit.toMillis();

        // Has enough time elapsed since the last request?
        if (timeSinceLastRequest < rateLimitTime) {
            try {
                Thread.sleep(rateLimitTime - timeSinceLastRequest);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Cache the current time for subsequent requests
        lastRequestTime = System.currentTimeMillis();

        return chain.proceed(chain.request());
    }
}
