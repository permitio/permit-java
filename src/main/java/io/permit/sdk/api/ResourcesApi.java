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

interface IResourcesApi {
    ResourceRead[] list(int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    ResourceRead[] list(int page) throws IOException, PermitApiError, PermitContextError;
    ResourceRead[] list() throws IOException, PermitApiError, PermitContextError;
    ResourceRead get(String resourceKey) throws IOException, PermitApiError, PermitContextError;
    ResourceRead getByKey(String resourceKey) throws IOException, PermitApiError, PermitContextError;
    ResourceRead getById(UUID resourceId) throws IOException, PermitApiError, PermitContextError;
    ResourceRead create(ResourceCreate resourceData) throws IOException, PermitApiError, PermitContextError;
    ResourceRead replace(String resourceKey, ResourceReplace resourceData) throws IOException, PermitApiError, PermitContextError;
    ResourceRead update(String resourceKey, ResourceUpdate resourceData) throws IOException, PermitApiError, PermitContextError;
    void delete(String resourceKey) throws IOException, PermitApiError, PermitContextError;
}

public class ResourcesApi extends BaseApi implements IResourcesApi {
    public ResourcesApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(ResourcesApi.class));
    }

    private String getResourcesUrl(String url) {
        return buildUrl(
                String.format(
                        "/v2/schema/%s/%s/resources%s",
                        config.getContext().getProject(),
                        config.getContext().getEnvironment(),
                        url
                )
        );
    }

    public ResourceRead[] list(int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getResourcesUrl("");
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
            return (new Gson()).fromJson(responseString, ResourceRead[].class);
        }
    }

    public ResourceRead[] list(int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(page, 100);
    }

    public ResourceRead[] list() throws IOException, PermitApiError, PermitContextError {
        return this.list(1);
    }

    public ResourceRead get(String resourceKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getResourcesUrl(String.format("/%s", resourceKey));
        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .get()
        );

        return this.<ResourceRead>callApiAndParseJson(request, ResourceRead.class);
    }

    public ResourceRead getByKey(String resourceKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(resourceKey);
    }

    public ResourceRead getById(UUID resourceId) throws IOException, PermitApiError, PermitContextError {
        return this.get(resourceId.toString());
    }

    public ResourceRead create(ResourceCreate resourceData) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getResourcesUrl("");
        RequestBody jsonBody = getJsonRequestBody(resourceData);

        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .post(jsonBody)
        );

        return this.<ResourceRead>callApiAndParseJson(request, ResourceRead.class);
    }

    public ResourceRead replace(String resourceKey, ResourceReplace resourceData) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getResourcesUrl(String.format("/%s", resourceKey));
        RequestBody jsonBody = getJsonRequestBody(resourceData);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .put(jsonBody)
        );

        return this.<ResourceRead>callApiAndParseJson(request, ResourceRead.class);
    }

    public ResourceRead update(String resourceKey, ResourceUpdate resourceData) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getResourcesUrl(String.format("/%s", resourceKey));
        RequestBody jsonBody = getJsonRequestBody(resourceData);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .patch(jsonBody)
        );

        return this.<ResourceRead>callApiAndParseJson(request, ResourceRead.class);
    }

    public void delete(String resourceKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getResourcesUrl(String.format("/%s", resourceKey));
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
