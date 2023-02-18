package io.permit.sdk.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.openapi.models.RoleRead;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseApi {
    protected final OkHttpClient client;
    protected final PermitConfig config;

    protected final Logger logger;
    protected final Headers headers;

    protected BaseApi(OkHttpClient client, PermitConfig config, Logger logger) {
        this.client = client;
        this.config = config;
        this.logger = logger;
        this.headers = new Headers.Builder()
            .add("Content-Type", "application/json")
            .add("Authorization", String.format("Bearer %s", this.config.getToken()))
            .build();
    }

    protected void throwIfErrorResponseCode(Response response, String responseContent, List<Integer> validErrorCodes) throws PermitApiError {
        if (!response.isSuccessful() && !validErrorCodes.contains(response.code())) {
            if (config.isDebugMode()) {
                prettyPrintErrorJson(responseContent);
            }

            throw new PermitApiError(
                String.format("Got error status code: %d", response.code()),
                response.code(),
                responseContent
            );
        }
    }

    protected void prettyPrintErrorJson(String responseString) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(gson.fromJson(responseString, Object.class));
        logger.info(String.format("[Permit SDK] got error:\n%s", json));
    }

    protected String processResponseBody(Response response) throws IOException, PermitApiError {
        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            throw new IOException("got empty response");
        }
        String responseString = responseBody.string();
        throwIfErrorResponseCode(response, responseString, new ArrayList<Integer>());
        return responseString;
    }

    protected String buildUrl(String relativeUrl) {
        return String.format("%s%s", config.getApiUrl(), relativeUrl);
    }

    protected Request buildRequest(Request.Builder builder) {
        return builder.headers(this.headers).build();
    }
}
