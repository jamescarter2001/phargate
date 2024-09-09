package com.carter.phargate.util.http;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.Duration;

/**
 * OkHttp interceptor for enforcing a rate limit on HTTP requests. This allows calls to an endpoint to be
 * restricted to a maximum number within a defined timeframe (eg. 5 requests a minute).
 */
@RequiredArgsConstructor
@Slf4j
public class RateLimitInterceptor implements Interceptor {

    private final long maxRequests;
    private final Duration rateLimit;
    private final boolean allowBlocking;
    private long lastRequestTime = 0;
    private long requestCount = 0;

    @NotNull
    @Override
    public synchronized Response intercept(@NotNull Chain chain) throws IOException {
        final long currentTime = System.currentTimeMillis();
        final long timeSinceLastRequest = currentTime - lastRequestTime;

        final long rateLimitTime = rateLimit.toMillis();

        // Has the rate limit time elapsed? If so, we can safely reset the counter.
        if (timeSinceLastRequest >= rateLimitTime) {
            requestCount = 0;
        }

        // Have we hit the rate limit?
        if (requestCount == maxRequests && timeSinceLastRequest < rateLimitTime) {
            final String message = String.format("[%s] Rate limit reached: %d requests per %d seconds.", chain.request().url(), maxRequests, rateLimit.getSeconds());

            // Can we block the thread until the rate limit time has elapsed?
            if (allowBlocking) {
                long suspendTime = rateLimitTime - timeSinceLastRequest;
                try {
                    log.info(message + " Suspending thread for {} seconds", suspendTime / 1000);
                    Thread.sleep(suspendTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                requestCount = 0;
            } else {
                log.error(message);
                throw new IOException(message);
            }
        }

        // Cache the current time and update the request count for subsequent requests.
        lastRequestTime = System.currentTimeMillis();
        requestCount++;

        return chain.proceed(chain.request());
    }
}
