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

/**
 * The implementation of the {@link IResourcesApi} interface for interacting with
 * resources in the Permit REST API.
 */
public class ResourcesApi extends BaseApi implements IResourcesApi {
    /**
     * Constructs a new instance of the {@link ResourcesApi} class.
     *
     * @param client The {@link OkHttpClient} instance used to send HTTP requests.
     * @param config The {@link PermitConfig} instance containing the SDK configuration.
     */
    public ResourcesApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(ResourcesApi.class));
    }

    /**
     * Builds the URL for the resources API using the specified URL.
     *
     * @param url The URL fragment.
     * @return The built URL for resources.
     */
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

    /**
     * Retrieves a list of resources with pagination support.
     *
     * @param page    The page number.
     * @param perPage The number of items per page.
     * @return An array of {@link ResourceRead} objects representing the resources.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If there is an error in the Permit context.
     */
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

    /**
     * Retrieves a list of resources with default number of items per page.
     *
     * @param page The page number.
     * @return An array of {@link ResourceRead} objects representing the resources.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If there is an error in the Permit context.
     */
    public ResourceRead[] list(int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(page, 100);
    }

    /**
     * Retrieves a list of all resources with default pagination parameters.
     *
     * @return An array of {@link ResourceRead} objects representing the resources.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If there is an error in the Permit context.
     */
    public ResourceRead[] list() throws IOException, PermitApiError, PermitContextError {
        return this.list(1);
    }

    /**
     * Retrieves a resource by its resource key.
     *
     * @param resourceKey The key of the resource.
     * @return The {@link ResourceRead} object representing the resource.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If there is an error in the Permit context.
     */
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

    /**
     * Retrieves a resource by its resource key.
     * This is an alias for the {@link #get} method.
     *
     * @param resourceKey The key of the resource.
     * @return The {@link ResourceRead} object representing the resource.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If there is an error in the Permit context.
     */
    public ResourceRead getByKey(String resourceKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(resourceKey);
    }

    /**
     * Retrieves a resource by its resource ID.
     *
     * @param resourceId The ID of the resource.
     * @return The {@link ResourceRead} object representing the resource.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If there is an error in the Permit context.
     */
    public ResourceRead getById(UUID resourceId) throws IOException, PermitApiError, PermitContextError {
        return this.get(resourceId.toString());
    }

    /**
     * Creates a new resource.
     *
     * @param resourceData The {@link ResourceCreate} object containing the data for the new resource.
     * @return The {@link ResourceRead} object representing the created resource.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If there is an error in the Permit context.
     */
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

    /**
     * Replaces an existing resource with the specified resource key.
     *
     * @param resourceKey  The key of the resource to replace.
     * @param resourceData The {@link ResourceReplace} object containing the updated data for the resource.
     * @return The {@link ResourceRead} object representing the replaced resource.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    * If there is an error in the Permit context.
     */
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

    /**
     * Updates an existing resource with the specified resource key.
     *
     * @param resourceKey  The key of the resource to update.
     * @param resourceData The {@link ResourceUpdate} object containing the updated data for the resource.
     * @return The {@link ResourceRead} object representing the updated resource.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If there is an error in the Permit context.
     */
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

    /**
     * Deletes a resource with the specified resource key.
     *
     * @param resourceKey The key of the resource to delete.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If there is an error in the Permit context.
     */
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
