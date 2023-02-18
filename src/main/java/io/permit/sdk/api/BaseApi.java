package io.permit.sdk.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.permit.sdk.ApiKeyLevel;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.PermitContext;
import io.permit.sdk.openapi.models.APIKeyScopeRead;
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

    protected String processResponseBody(Response response) throws IOException, PermitApiError {
        return processResponseBody(response, true);
    }

    protected String buildUrl(String relativeUrl) {
        return String.format("%s%s", config.getApiUrl(), relativeUrl);
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

    protected void ensureContext(ApiKeyLevel callLevel) throws PermitContextError, IOException {
        // set context if not already set
        if (this.config.getContext().getApiKeyLevel() == ApiKeyLevel.WAIT_FOR_INIT) {
            setContextFromApiKey();
        }

        // verify context matches requested call level
        if (callLevel == ApiKeyLevel.PROJECT_LEVEL_API_KEY && this.config.getContext().getProject() == null) {
            throw new PermitContextError("""
                You're trying to use an SDK method that's specific to a project,
                but you haven't set the current project in your client's context yet,
                or you are using an organization level API key.
                Please set the context to a specific
                project using `permit.set_context()` method.
            """);
        }

        if (callLevel == ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY && this.config.getContext().getEnvironment() == null) {
            throw new PermitContextError("""
                You're trying to use an SDK method that's specific to an environment,
                but you haven't set the current environment in your client's context yet,
                or you are using an organization/project level API key.
                Please set the context to a specific
                environment using `permit.set_context()` method.
            """);
        }
    }
}
