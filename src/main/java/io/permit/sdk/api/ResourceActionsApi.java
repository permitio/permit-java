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

interface IResourceActionsApi {
    ResourceActionRead[] list(String resourceKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    ResourceActionRead[] list(String resourceKey, int page) throws IOException, PermitApiError, PermitContextError;
    ResourceActionRead[] list(String resourceKey) throws IOException, PermitApiError, PermitContextError;
    ResourceActionRead get(String resourceKey, String actionKey) throws IOException, PermitApiError, PermitContextError;
    ResourceActionRead getByKey(String resourceKey, String actionKey) throws IOException, PermitApiError, PermitContextError;
    ResourceActionRead getById(UUID resourceId, UUID actionId) throws IOException, PermitApiError, PermitContextError;
    ResourceActionRead create(String resourceKey, ResourceActionCreate actionData) throws IOException, PermitApiError, PermitContextError;
    ResourceActionRead update(String resourceKey, String actionKey, ResourceActionUpdate actionData) throws IOException, PermitApiError, PermitContextError;
    void delete(String resourceKey, String actionKey) throws IOException, PermitApiError, PermitContextError;
}

public class ResourceActionsApi extends BaseApi implements IResourceActionsApi {
    public ResourceActionsApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(ResourceActionsApi.class));
    }

    private String getResourceActionsUrl(String resourceKey, String url) {
        return buildUrl(
                String.format(
                    "/v2/schema/%s/%s/resources/%s/actions%s",
                    config.getContext().getProject(),
                    config.getContext().getEnvironment(),
                    resourceKey,
                    url
                )
        );
    }

    public ResourceActionRead[] list(String resourceKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getResourceActionsUrl(resourceKey, "");
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
            return (new Gson()).fromJson(responseString, ResourceActionRead[].class);
        }
    }

    public ResourceActionRead[] list(String resourceKey, int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(resourceKey, page, 100);
    }

    public ResourceActionRead[] list(String resourceKey) throws IOException, PermitApiError, PermitContextError {
        return this.list(resourceKey,1);
    }

    public ResourceActionRead get(String resourceKey, String actionKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getResourceActionsUrl(resourceKey, String.format("/%s", actionKey));
        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .get()
        );

        return this.<ResourceActionRead>callApiAndParseJson(request, ResourceActionRead.class);
    }

    public ResourceActionRead getByKey(String resourceKey, String actionKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(resourceKey, actionKey);
    }

    public ResourceActionRead getById(UUID resourceId, UUID actionId) throws IOException, PermitApiError, PermitContextError {
        return this.get(resourceId.toString(), actionId.toString());
    }

    public ResourceActionRead create(String resourceKey, ResourceActionCreate actionData) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getResourceActionsUrl(resourceKey,"");
        RequestBody jsonBody = getJsonRequestBody(actionData);

        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .post(jsonBody)
        );

        return this.<ResourceActionRead>callApiAndParseJson(request, ResourceActionRead.class);
    }

    public ResourceActionRead update(String resourceKey, String actionKey, ResourceActionUpdate actionData) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getResourceActionsUrl(resourceKey, String.format("/%s", actionKey));
        RequestBody jsonBody = getJsonRequestBody(actionData);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .patch(jsonBody)
        );

        return this.<ResourceActionRead>callApiAndParseJson(request, ResourceActionRead.class);
    }

    public void delete(String resourceKey, String actionKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getResourceActionsUrl(resourceKey, String.format("/%s", actionKey));
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
