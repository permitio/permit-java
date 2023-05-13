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

interface IResourceActionGroupsApi {
    ResourceActionGroupRead[] list(String resourceKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    ResourceActionGroupRead[] list(String resourceKey, int page) throws IOException, PermitApiError, PermitContextError;
    ResourceActionGroupRead[] list(String resourceKey) throws IOException, PermitApiError, PermitContextError;
    ResourceActionGroupRead get(String resourceKey, String groupKey) throws IOException, PermitApiError, PermitContextError;
    ResourceActionGroupRead getByKey(String resourceKey, String groupKey) throws IOException, PermitApiError, PermitContextError;
    ResourceActionGroupRead getById(UUID resourceId, UUID groupId) throws IOException, PermitApiError, PermitContextError;
    ResourceActionGroupRead create(String resourceKey, ResourceActionGroupCreate actionData) throws IOException, PermitApiError, PermitContextError;
    void delete(String resourceKey, String groupKey) throws IOException, PermitApiError, PermitContextError;
}

public class ResourceActionGroupsApi extends BaseApi implements IResourceActionGroupsApi {
    public ResourceActionGroupsApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(ResourceActionGroupsApi.class));
    }

    private String getResourceActionGroupsUrl(String resourceKey, String url) {
        return buildUrl(
                String.format(
                    "/v2/schema/%s/%s/resources/%s/action_groups%s",
                    config.getContext().getProject(),
                    config.getContext().getEnvironment(),
                    resourceKey,
                    url
                )
        );
    }

    public ResourceActionGroupRead[] list(String resourceKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getResourceActionGroupsUrl(resourceKey, "");
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
            return (new Gson()).fromJson(responseString, ResourceActionGroupRead[].class);
        }
    }

    public ResourceActionGroupRead[] list(String resourceKey, int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(resourceKey, page, 100);
    }

    public ResourceActionGroupRead[] list(String resourceKey) throws IOException, PermitApiError, PermitContextError {
        return this.list(resourceKey,1);
    }

    public ResourceActionGroupRead get(String resourceKey, String groupKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getResourceActionGroupsUrl(resourceKey, String.format("/%s", groupKey));
        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .get()
        );

        return this.<ResourceActionGroupRead>callApiAndParseJson(request, ResourceActionGroupRead.class);
    }

    public ResourceActionGroupRead getByKey(String resourceKey, String groupKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(resourceKey, groupKey);
    }

    public ResourceActionGroupRead getById(UUID resourceId, UUID groupId) throws IOException, PermitApiError, PermitContextError {
        return this.get(resourceId.toString(), groupId.toString());
    }

    public ResourceActionGroupRead create(String resourceKey, ResourceActionGroupCreate groupData) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getResourceActionGroupsUrl(resourceKey,"");
        RequestBody jsonBody = getJsonRequestBody(groupData);

        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .post(jsonBody)
        );

        return this.<ResourceActionGroupRead>callApiAndParseJson(request, ResourceActionGroupRead.class);
    }

    public void delete(String resourceKey, String groupKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getResourceActionGroupsUrl(resourceKey, String.format("/%s", groupKey));
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
