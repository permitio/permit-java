package io.permit.sdk.api;

import com.google.gson.Gson;
import io.permit.sdk.ApiKeyLevel;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.api.models.CreateOrUpdateResult;
import io.permit.sdk.openapi.models.*;
import okhttp3.*;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

interface ITenantsApi {
    TenantRead[] list(int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    TenantRead[] list(int page) throws IOException, PermitApiError, PermitContextError;
    TenantRead[] list() throws IOException, PermitApiError, PermitContextError;
    PaginatedResultUserRead listTenantUsers(String tenantKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    PaginatedResultUserRead listTenantUsers(String tenantKey, int page) throws IOException, PermitApiError, PermitContextError;
    PaginatedResultUserRead listTenantUsers(String tenantKey) throws IOException, PermitApiError, PermitContextError;
    TenantRead get(String tenantKey) throws IOException, PermitApiError, PermitContextError;
    TenantRead getByKey(String tenantKey) throws IOException, PermitApiError, PermitContextError;
    TenantRead getById(UUID tenantId) throws IOException, PermitApiError, PermitContextError;
    TenantRead create(TenantCreate tenantData) throws IOException, PermitApiError, PermitContextError;
    TenantRead update(String tenantKey, TenantUpdate tenantData) throws IOException, PermitApiError, PermitContextError;
    void delete(String tenantKey) throws IOException, PermitApiError, PermitContextError;
}

public class TenantsApi extends BaseApi implements ITenantsApi {
    public TenantsApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(TenantsApi.class));
    }

    private String getTenantsUrl(String url) {
        return buildUrl(
                String.format(
                        "/v2/facts/%s/%s/tenants%s",
                        config.getContext().getProject(),
                        config.getContext().getEnvironment(),
                        url
                )
        );
    }

    public TenantRead[] list(int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getTenantsUrl("");
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
            return (new Gson()).fromJson(responseString, TenantRead[].class);
        }
    }

    public TenantRead[] list(int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(page, 100);
    }

    public TenantRead[] list() throws IOException, PermitApiError, PermitContextError {
        return this.list(1);
    }

    public PaginatedResultUserRead listTenantUsers(String tenantKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getTenantsUrl(String.format("/%s/users", tenantKey));
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

        return this.<PaginatedResultUserRead>callApiAndParseJson(request, PaginatedResultUserRead.class);
    }

    public PaginatedResultUserRead listTenantUsers(String tenantKey, int page) throws IOException, PermitApiError, PermitContextError {
        return this.listTenantUsers(tenantKey, page, 100);
    }

    public PaginatedResultUserRead listTenantUsers(String tenantKey) throws IOException, PermitApiError, PermitContextError {
        return this.listTenantUsers(tenantKey, 1);
    }

    public TenantRead get(String tenantKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getTenantsUrl(String.format("/%s", tenantKey));
        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .get()
        );

        return this.<TenantRead>callApiAndParseJson(request, TenantRead.class);
    }

    public TenantRead getByKey(String tenantKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(tenantKey);
    }

    public TenantRead getById(UUID tenantId) throws IOException, PermitApiError, PermitContextError {
        return this.get(tenantId.toString());
    }

    public TenantRead create(TenantCreate tenantData) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getTenantsUrl("");
        RequestBody jsonBody = getJsonRequestBody(tenantData);

        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .post(jsonBody)
        );

        return this.<TenantRead>callApiAndParseJson(request, TenantRead.class);
    }

    public TenantRead update(String tenantKey, TenantUpdate tenantData) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getTenantsUrl(String.format("/%s", tenantKey));
        RequestBody jsonBody = getJsonRequestBody(tenantData);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .patch(jsonBody)
        );

        return this.<TenantRead>callApiAndParseJson(request, TenantRead.class);
    }

    public void delete(String tenantKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getTenantsUrl(String.format("/%s", tenantKey));
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
