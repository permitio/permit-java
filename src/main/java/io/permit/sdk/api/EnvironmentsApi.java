package io.permit.sdk.api;

import com.google.gson.Gson;
import io.permit.sdk.ApiKeyLevel;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.openapi.models.*;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

interface IEnvironmentsApi {
    EnvironmentRead[] list(String projectKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    EnvironmentRead[] list(String projectKey, int page) throws IOException, PermitApiError, PermitContextError;
    EnvironmentRead[] list(String projectKey) throws IOException, PermitApiError, PermitContextError;
    EnvironmentRead get(String projectKey, String environmentKey) throws IOException, PermitApiError, PermitContextError;
    EnvironmentRead getByKey(String projectKey, String environmentKey) throws IOException, PermitApiError, PermitContextError;
    EnvironmentRead getById(UUID projectId, UUID environmentId) throws IOException, PermitApiError, PermitContextError;
    EnvironmentRead create(String projectKey, EnvironmentCreate environmentData) throws IOException, PermitApiError, PermitContextError;
    EnvironmentRead update(String projectKey, String environmentKey, EnvironmentUpdate environmentData) throws IOException, PermitApiError, PermitContextError;
    EnvironmentRead copy(String projectKey, String environmentKey, EnvironmentCopy copyParams) throws IOException, PermitApiError, PermitContextError;
    void delete(String projectKey, String environmentKey) throws IOException, PermitApiError, PermitContextError;
}

public class EnvironmentsApi extends BaseApi implements IEnvironmentsApi {
    public EnvironmentsApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(EnvironmentsApi.class));
    }

    private String getEnvironmentsUrl(String projectKey, String url) {
        return buildUrl(
                String.format(
                    "/v2/projects/%s/envs%s",
                    projectKey,
                    url
                )
        );
    }

    public EnvironmentRead[] list(String projectKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.PROJECT_LEVEL_API_KEY);
        String url = getEnvironmentsUrl(projectKey, "");
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        Request request = buildRequest(
                new Request.Builder()
                    .url(
                        urlBuilder
                            .addQueryParameter("page", Integer.toString(page))
                            .addQueryParameter("per_page", Integer.toString(perPage))
                            .build()
                    )
                    .get()
        );

        try (Response response = client.newCall(request).execute()) {
            String responseString = processResponseBody(response);
            return (new Gson()).fromJson(responseString, EnvironmentRead[].class);
        }
    }

    public EnvironmentRead[] list(String projectKey, int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(projectKey, page, 100);
    }

    public EnvironmentRead[] list(String projectKey) throws IOException, PermitApiError, PermitContextError {
        return this.list(projectKey,1);
    }

    public EnvironmentRead get(String projectKey, String environmentKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.PROJECT_LEVEL_API_KEY);
        String url = getEnvironmentsUrl(projectKey, String.format("/%s", environmentKey));
        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .get()
        );

        return this.<EnvironmentRead>callApiAndParseJson(request, EnvironmentRead.class);
    }

    public EnvironmentRead getByKey(String projectKey, String environmentKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(projectKey, environmentKey);
    }

    public EnvironmentRead getById(UUID projectId, UUID environmentId) throws IOException, PermitApiError, PermitContextError {
        return this.get(projectId.toString(), environmentId.toString());
    }

    public EnvironmentRead create(String projectKey, EnvironmentCreate environmentData) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.PROJECT_LEVEL_API_KEY);
        String url = getEnvironmentsUrl(projectKey,"");
        RequestBody jsonBody = getJsonRequestBody(environmentData);

        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .post(jsonBody)
        );

        return this.<EnvironmentRead>callApiAndParseJson(request, EnvironmentRead.class);
    }

    public EnvironmentRead update(String projectKey, String environmentKey, EnvironmentUpdate environmentData) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.PROJECT_LEVEL_API_KEY);
        String url = getEnvironmentsUrl(projectKey, String.format("/%s", environmentKey));
        RequestBody jsonBody = getJsonRequestBody(environmentData);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .patch(jsonBody)
        );

        return this.<EnvironmentRead>callApiAndParseJson(request, EnvironmentRead.class);
    }

    public EnvironmentRead copy(String projectKey, String environmentKey, EnvironmentCopy copyParams) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.PROJECT_LEVEL_API_KEY);
        String url = getEnvironmentsUrl(projectKey, String.format("/%s/copy", environmentKey));
        RequestBody jsonBody = getJsonRequestBody(copyParams);

        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .post(jsonBody)
        );

        return this.<EnvironmentRead>callApiAndParseJson(request, EnvironmentRead.class);
    }

    public void delete(String projectKey, String environmentKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.PROJECT_LEVEL_API_KEY);
        String url = getEnvironmentsUrl(projectKey, String.format("/%s", environmentKey));
        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .delete()
        );

        try (Response response = client.newCall(request).execute()) {
            processResponseBody(response, false);
        }
    }
}
