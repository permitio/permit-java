package io.permit.sdk.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.permit.sdk.ApiContextLevel;
import io.permit.sdk.ApiKeyLevel;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.PermitContext;
import io.permit.sdk.openapi.models.APIKeyScopeRead;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

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
        Headers.Builder headersBuilder = new Headers.Builder()
            .add("Content-Type", "application/json")
            .add("Authorization", String.format("Bearer %s", this.config.getToken()))
            .add("X-Permit-SDK-Version", String.format("java:%s", this.config.version));

        if (config.isProxyFactsViaPdp() && config.getFactsSyncTimeout() > 0) {
            headersBuilder.add("X-Wait-Timeout", String.valueOf(config.getFactsSyncTimeout()));
        }
        this.headers = headersBuilder.build();
    }

    protected <T> T callApiAndParseJson(Request request, Class<T> modelClass) throws IOException, PermitApiError {
        try (Response response = client.newCall(request).execute()) {
            String responseString = processResponseBody(response);
            return (new Gson()).fromJson(responseString, modelClass);
        }
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

    protected String processResponseBody(Response response, boolean throwOnEmptyResponse) throws IOException, PermitApiError {
        ResponseBody responseBody = response.body();
        if (responseBody == null && throwOnEmptyResponse) {
            throw new IOException("got empty response");
        }
        String responseString = responseBody.string();
        throwIfErrorResponseCode(response, responseString, new ArrayList<Integer>());
        return responseString;
    }

    @NotNull
    protected static <T> RequestBody getJsonRequestBody(T data) {
        return RequestBody.create((
                        new Gson()).toJson(data),
                MediaType.parse("application/json")
        );
    }

    protected String processResponseBody(Response response) throws IOException, PermitApiError {
        return processResponseBody(response, true);
    }

    protected String buildUrl(String relativeUrl) {
        return String.format("%s%s", config.getApiUrl(), relativeUrl);
    }

    protected String buildPdpUrl(String relativeUrl) {
        return String.format("%s%s", config.getPdpAddress(), relativeUrl);
    }

    protected Request buildRequest(Request.Builder builder) {
        return builder.headers(this.headers).build();
    }

    private void setContextFromApiKey() throws IOException, PermitContextError {
        String url = buildUrl("/v2/api-key/scope");
        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .get()
        );
        try (Response response = client.newCall(request).execute()) {
            String responseString = processResponseBody(response);
            Gson gson = new Gson();
            APIKeyScopeRead scope = gson.fromJson(responseString, APIKeyScopeRead.class);
            if (scope.organizationId != null) {
                if (scope.projectId != null) {
                    if (scope.environmentId != null) {
                        // env level scope
                        this.config.setContext(
                                new PermitContext.Builder().withEnvironment(
                                        scope.organizationId,
                                        scope.projectId,
                                        scope.environmentId
                                ).build());
                        return;
                    }

                    // project level scope
                    this.config.setContext(
                            new PermitContext.Builder().withProject(
                                    scope.organizationId,
                                    scope.projectId
                            ).build());
                    return;
                }

                // org level scope
                this.config.setContext(
                        new PermitContext.Builder().withOrganization(
                                scope.organizationId
                        ).build());
                return;
            }
            throw new PermitContextError("could not set api key scope");

        } catch (PermitApiError e) {
            throw new PermitContextError("could not get api key scope in order to create a context");
        }
    }

    protected void ensureAccessLevel(ApiKeyLevel requiredAccessLevel) throws PermitContextError, IOException {
        // set context if not already set
        // Should only happen once in the lifetime of the SDK
        if (config.getContext().getContextLevel() == ApiContextLevel.WAIT_FOR_INIT ||
            config.getContext().getPermittedAccessLevel() == ApiKeyLevel.WAIT_FOR_INIT) {
            setContextFromApiKey();
        }

        if (requiredAccessLevel != config.getContext().getPermittedAccessLevel()) {
            if (requiredAccessLevel.getValue() < config.getContext().getPermittedAccessLevel().getValue()) {
                throw new PermitContextError(
                    "You're trying to use an SDK method that requires an API Key with access level: " +
                    requiredAccessLevel + ", however the SDK is running with an API key with level " +
                    config.getContext().getPermittedAccessLevel() + "."
                );
            }
        }
    }


    protected void ensureContext(ApiContextLevel requiredContext) throws PermitContextError, IOException {
        // set context if not already set
        // Should only happen once in the lifetime of the SDK
        if (config.getContext().getContextLevel() == ApiContextLevel.WAIT_FOR_INIT ||
            config.getContext().getPermittedAccessLevel() == ApiKeyLevel.WAIT_FOR_INIT) {
            setContextFromApiKey();
        }

        if (config.getContext().getContextLevel().getValue() < requiredContext.getValue()) {
            throw new PermitContextError(
                "You're trying to use an SDK method that requires an API context of " + requiredContext +
                ", however the SDK is running in a less specific context level: " +
                config.getContext().getContextLevel() + "."
            );
        }
    }
}
