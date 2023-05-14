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

/**
 * The TenantsApi class provides methods for interacting with tenants using the Permit REST API.
 */
public class TenantsApi extends BaseApi implements ITenantsApi {
    /**
     * Constructs a new TenantsApi instance with the specified OkHttpClient and PermitConfig.
     *
     * @param client The OkHttpClient instance to be used for making HTTP requests.
     * @param config The PermitConfig instance that contains the SDK configuration.
     */
    public TenantsApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(TenantsApi.class));
    }

    /**
     * Constructs the api URLs for the Tenants API.
     *
     * @param url The URL fragment.
     * @return The formatted URL for the Tenants API.
     */
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

    /**
     * Retrieves a paginated list of tenants.
     *
     * @param page    The page number of the result set to retrieve.
     * @param perPage The number of items per page.
     * @return An array of TenantRead objects representing the retrieved tenants.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If there is an error in the Permit context.
     */
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

    /**
     * Retrieves a paginated list of tenants with the default number of items per page.
     *
     * @param page The page number of the result set to retrieve.
     * @return An array of TenantRead objects representing the retrieved tenants.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If there is an error in the Permit context.
     */
    public TenantRead[] list(int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(page, 100);
    }

    /**
     * Retrieves a list of tenants with default pagination.
     *
     * @return An array of TenantRead objects representing the retrieved tenants.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If there is an error in the Permit context.
     */
    public TenantRead[] list() throws IOException, PermitApiError, PermitContextError {
        return this.list(1);
    }

    /**
     * Retrieves a paginated result of users for the specified tenant.
     *
     * @param tenantKey The key of the tenant.
     * @param page      The page number
     * @param perPage The number of items per page.
     * @return A PaginatedResultUserRead object representing the retrieved paginated result of users.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If there is an error in the Permit context.
     */
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

    /**
     * Retrieves a paginated result of users for the specified tenant with the specified page number.
     *
     * @param tenantKey The key of the tenant.
     * @param page      The page number of the result set to retrieve.
     * @return A PaginatedResultUserRead object representing the retrieved paginated result of users.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If there is an error in the Permit context.
     */
    public PaginatedResultUserRead listTenantUsers(String tenantKey, int page) throws IOException, PermitApiError, PermitContextError {
        return this.listTenantUsers(tenantKey, page, 100);
    }

    /**
     * Retrieves a paginated result of users for the specified tenant with default pagination.
     *
     * @param tenantKey The key of the tenant.
     * @return A PaginatedResultUserRead object representing the retrieved paginated result of users.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If there is an error in the Permit context.
     */
    public PaginatedResultUserRead listTenantUsers(String tenantKey) throws IOException, PermitApiError, PermitContextError {
        return this.listTenantUsers(tenantKey, 1);
    }

    /**
     * Retrieves a tenant by its key.
     *
     * @param tenantKey The key of the tenant.
     * @return A TenantRead object representing the retrieved tenant.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If there is an error in the Permit context.
     */
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

    /**
     * Retrieves a tenant by its key.
     *
     * @param tenantKey The key of the tenant.
     * @return A TenantRead object representing the retrieved tenant.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If there is an error in the Permit context.
     */
    public TenantRead getByKey(String tenantKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(tenantKey);
    }

    /**
     * Retrieves a tenant by its ID.
     *
     * @param tenantId The ID of the tenant.
     * @return A TenantRead object representing the retrieved tenant.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If there is an error in the Permit context.
     */
    public TenantRead getById(UUID tenantId) throws IOException, PermitApiError, PermitContextError {
        return this.get(tenantId.toString());
    }

    /**
     * Creates a new tenant.
     *
     * @param tenantData The TenantCreate object representing the tenant data.
     * @return A TenantRead object representing the created tenant.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If there is an error in the Permit context.
     */
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

    /**
     * Updates an existing tenant.
     *
     * @param tenantKey  The key of the tenant to be updated.
     * @param tenantData The TenantUpdate object representing the updated tenant data.
     * @return A TenantRead object representing the updated tenant.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If there is an error in the Permit context.
     */
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

    /**
     * Deletes a tenant.
     *
     * @param tenantKey The key of the tenant to be deleted.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If there is an error in the Permit context.
     */
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
