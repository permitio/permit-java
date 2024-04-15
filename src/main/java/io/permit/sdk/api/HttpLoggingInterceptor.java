package io.permit.sdk.api;

import io.permit.sdk.PermitConfig;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

public class HttpLoggingInterceptor implements Interceptor {
    private final Logger logger;
    private final PermitConfig config;

    public HttpLoggingInterceptor(Logger logger, PermitConfig config) {
        this.logger = logger;
        this.config = config;
    }

    @NotNull
    @Override public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        if (config.isDebugMode()) {
            logger.info(
                    String.format(
                            "[Permit SDK] Sending HTTP request: %s %s", request.method(), request.url()
                    )
            );
        }

        Response response = chain.proceed(request);

        if (config.isDebugMode()) {
            logger.info(
                    String.format(
                            "[Permit SDK] Received HTTP response: %s %s, status %d", request.method(), request.url(), response.code()
                    )
            );
        }

        return response;
    }
}