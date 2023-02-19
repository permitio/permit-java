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

public class ResourceAttributesApi extends BaseApi {
    public ResourceAttributesApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(ResourceAttributesApi.class));
    }

    private String getResourceAttributesUrl(String resourceKey, String url) {
        return buildUrl(
                String.format(
                    "/v2/schema/%s/%s/resources/%s/attributes%s",
                    config.getContext().getProject(),
                    config.getContext().getEnvironment(),
                    resourceKey,
                    url
                )
        );
    }

    public ResourceAttributeRead[] list(String resourceKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getResourceAttributesUrl(resourceKey, "");
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
            return (new Gson()).fromJson(responseString, ResourceAttributeRead[].class);
        }
    }

    public ResourceAttributeRead[] list(String resourceKey, int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(resourceKey, page, 100);
    }

    public ResourceAttributeRead[] list(String resourceKey) throws IOException, PermitApiError, PermitContextError {
        return this.list(resourceKey,1);
    }

    public ResourceAttributeRead get(String resourceKey, String attributeKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getResourceAttributesUrl(resourceKey, String.format("/%s", attributeKey));
        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .get()
        );

        return this.<ResourceAttributeRead>callApiAndParseJson(request, ResourceAttributeRead.class);
    }

    public ResourceAttributeRead getByKey(String resourceKey, String attributeKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(resourceKey, attributeKey);
    }

    public ResourceAttributeRead getById(UUID resourceId, UUID attributeId) throws IOException, PermitApiError, PermitContextError {
        return this.get(resourceId.toString(), attributeId.toString());
    }

    public ResourceAttributeRead create(String resourceKey, ResourceAttributeCreate attributeData) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getResourceAttributesUrl(resourceKey,"");
        RequestBody jsonBody = getJsonRequestBody(attributeData);

        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .post(jsonBody)
        );

        return this.<ResourceAttributeRead>callApiAndParseJson(request, ResourceAttributeRead.class);
    }

    public ResourceAttributeRead update(String resourceKey, String attributeKey, ResourceAttributeUpdate attributeData) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getResourceAttributesUrl(resourceKey, String.format("/%s", attributeKey));
        RequestBody jsonBody = getJsonRequestBody(attributeData);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .patch(jsonBody)
        );

        return this.<ResourceAttributeRead>callApiAndParseJson(request, ResourceAttributeRead.class);
    }

    public void delete(String resourceKey, String attributeKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getResourceAttributesUrl(resourceKey, String.format("/%s", attributeKey));
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
