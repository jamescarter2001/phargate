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
    private long lastRequestTime = 0;
    private long requestCount = 0;

    @NotNull
    @Override
    public synchronized Response intercept(@NotNull Chain chain) throws IOException {
        long currentTime = System.currentTimeMillis();
        long timeSinceLastRequest = currentTime - lastRequestTime;

        long rateLimitTime = rateLimit.toMillis();

        // Has the rate limit time elapsed? If so, we can safely reset the counter.
        if (timeSinceLastRequest >= rateLimitTime) {
            requestCount = 0;
        }

        // Have we hit the rate limit? If so, suspend the thread.
        // TODO: should thread suspension be forced at this level? Should clients handle a thrown exception instead?
        if (requestCount == maxRequests && timeSinceLastRequest < rateLimitTime) {
            long suspendTime = rateLimitTime - timeSinceLastRequest;
            log.info("[{}] Rate limit reached: {} requests per {} seconds. Suspending thread for {} seconds", chain.request().url(), maxRequests, rateLimit.getSeconds(), suspendTime / 1000);
            try {
                Thread.sleep(suspendTime);
                requestCount = 0;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Cache the current time and update the request count for subsequent requests.
        lastRequestTime = System.currentTimeMillis();
        requestCount++;

        return chain.proceed(chain.request());
    }
}
