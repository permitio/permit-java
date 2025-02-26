package io.permit.sdk.api;

import com.google.gson.Gson;
import io.permit.sdk.ApiContextLevel;
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

interface IResourceInstancesApi {
    ResourceInstanceRead[] list(int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    ResourceInstanceRead[] list(int page) throws IOException, PermitApiError, PermitContextError;
    ResourceInstanceRead[] list() throws IOException, PermitApiError, PermitContextError;
    ResourceInstanceRead get(String instanceKey) throws IOException, PermitApiError, PermitContextError;
    ResourceInstanceRead getByKey(String instanceKey) throws IOException, PermitApiError, PermitContextError;
    ResourceInstanceRead getById(UUID instanceId) throws IOException, PermitApiError, PermitContextError;
    ResourceInstanceRead create(ResourceInstanceCreate instanceData) throws IOException, PermitApiError, PermitContextError;
    ResourceInstanceRead update(String instanceKey, ResourceInstanceUpdate instanceData) throws IOException, PermitApiError, PermitContextError;
    void delete(String instanceKey) throws IOException, PermitApiError, PermitContextError;
}

/**
 * The ResourceInstancesApi class provides methods for interacting with resource instances using the Permit REST API.
 */
public class ResourceInstancesApi extends BaseApi implements IResourceInstancesApi {
    /**
     * Constructs a new ResourceInstancesApi instance with the specified OkHttpClient and PermitConfig.
     *
     * @param client The OkHttpClient instance to be used for making HTTP requests.
     * @param config The PermitConfig instance that contains the SDK configuration.
     */
    public ResourceInstancesApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(ResourceInstancesApi.class));
    }

    /**
     * Constructs the api URLs for the ResourceInstances API.
     *
     * @param url The URL fragment.
     * @return The formatted URL for the ResourceInstances API.
     */
    private String getResourceInstancesUrl(String url) {
        if (Boolean.TRUE.equals(config.isProxyFactsViaPdp())) {
            return buildPdpUrl(
                    String.format(
                            "/facts/resource_instances%s",
                            url
                    )
            );
        } else {
            return buildUrl(
                    String.format(
                            "/v2/facts/%s/%s/resource_instances%s",
                            config.getContext().getProject(),
                            config.getContext().getEnvironment(),
                            url
                    )
            );
        }
    }

    /**
     * Retrieves a paginated list of resource instances.
     *
     * @param page    The page number of the result set to retrieve.
     * @param perPage The number of items per page.
     * @return An array of ResourceInstanceRead objects representing the retrieved resource instances.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceInstanceRead[] list(int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getResourceInstancesUrl("");
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
            return (new Gson()).fromJson(responseString, ResourceInstanceRead[].class);
        }
    }

    /**
     * Retrieves a paginated list of resource instances with the default number of items per page.
     *
     * @param page The page number of the result set to retrieve.
     * @return An array of ResourceInstanceRead objects representing the retrieved resource instances.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceInstanceRead[] list(int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(page, 100);
    }

    /**
     * Retrieves a list of resource instances with default pagination.
     *
     * @return An array of ResourceInstanceRead objects representing the retrieved resource instances.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceInstanceRead[] list() throws IOException, PermitApiError, PermitContextError {
        return this.list(1);
    }

    /**
     * Retrieves a resource instance by its key.
     *
     * @param instanceKey The key of the resource instance.
     * @return A ResourceInstanceRead object representing the retrieved resource instance.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceInstanceRead get(String instanceKey) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getResourceInstancesUrl(String.format("/%s", instanceKey));
        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .get()
        );

        return this.<ResourceInstanceRead>callApiAndParseJson(request, ResourceInstanceRead.class);
    }

    /**
     * Retrieves a resource instance by its key.
     *
     * @param instanceKey The key of the resource instance.
     * @return A ResourceInstanceRead object representing the retrieved resource instance.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceInstanceRead getByKey(String instanceKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(instanceKey);
    }

    /**
     * Retrieves a resource instance by its ID.
     *
     * @param instanceId The ID of the resource instance.
     * @return A ResourceInstanceRead object representing the retrieved resource instance.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceInstanceRead getById(UUID instanceId) throws IOException, PermitApiError, PermitContextError {
        return this.get(instanceId.toString());
    }

    /**
     * Creates a new resource instance.
     *
     * @param instanceData The ResourceInstanceCreate object representing the resource instance data.
     * @return A ResourceInstanceRead object representing the created resource instance.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceInstanceRead create(ResourceInstanceCreate instanceData) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getResourceInstancesUrl("");
        RequestBody jsonBody = getJsonRequestBody(instanceData);

        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .post(jsonBody)
        );

        return this.<ResourceInstanceRead>callApiAndParseJson(request, ResourceInstanceRead.class);
    }

    /**
     * Updates an existing resource instance.
     *
     * @param instanceKey  The key of the resource instance to be updated.
     * @param instanceData The ResourceInstanceUpdate object representing the updated resource instance data.
     * @return A ResourceInstanceRead object representing the updated resource instance.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceInstanceRead update(String instanceKey, ResourceInstanceUpdate instanceData) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getResourceInstancesUrl(String.format("/%s", instanceKey));
        RequestBody jsonBody = getJsonRequestBody(instanceData);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .patch(jsonBody)
        );

        return this.<ResourceInstanceRead>callApiAndParseJson(request, ResourceInstanceRead.class);
    }

    /**
     * Deletes a resource instance.
     *
     * @param instanceKey The key of the resource instance to be deleted.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public void delete(String instanceKey) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getResourceInstancesUrl(String.format("/%s", instanceKey));
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
