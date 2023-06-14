package io.permit.sdk.api;

import com.google.gson.Gson;
import io.permit.sdk.ApiContextLevel;
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

interface IResourceAttributesApi {
    ResourceAttributeRead[] list(String resourceKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    ResourceAttributeRead[] list(String resourceKey, int page) throws IOException, PermitApiError, PermitContextError;
    ResourceAttributeRead[] list(String resourceKey) throws IOException, PermitApiError, PermitContextError;
    ResourceAttributeRead get(String resourceKey, String attributeKey) throws IOException, PermitApiError, PermitContextError;
    ResourceAttributeRead getByKey(String resourceKey, String attributeKey) throws IOException, PermitApiError, PermitContextError;
    ResourceAttributeRead getById(UUID resourceId, UUID attributeId) throws IOException, PermitApiError, PermitContextError;
    ResourceAttributeRead create(String resourceKey, ResourceAttributeCreate attributeData) throws IOException, PermitApiError, PermitContextError;
    ResourceAttributeRead update(String resourceKey, String attributeKey, ResourceAttributeUpdate attributeData) throws IOException, PermitApiError, PermitContextError;
    void delete(String resourceKey, String attributeKey) throws IOException, PermitApiError, PermitContextError;
}

/**
 * The implementation of the {@link IResourceAttributesApi} interface for
 * interacting with resource attributes using the Permit REST API.
 * This API defines attribute *types* meaning that a resource of type `document`
 * has a `created` attribute of type `timestamp` and a `secret` of type `boolean`.
 * 
 * This API DOES NOT define attribute values (e.g: document.secret for `budget23` is `true`)
 */
public class ResourceAttributesApi extends BaseApi implements IResourceAttributesApi {
    /**
     * Constructs a new instance of the {@link ResourceAttributesApi} class.
     *
     * @param client The {@link OkHttpClient} instance used to send HTTP requests.
     * @param config The {@link PermitConfig} instance containing the SDK configuration.
     */
    public ResourceAttributesApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(ResourceAttributesApi.class));
    }

    /**
     * Builds the URL for resource attributes using the specified resource key and URL.
     *
     * @param resourceKey The key of the resource.
     * @param url         The URL of the resource attributes.
     * @return The built URL for resource attributes.
     */
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

    /**
     * Retrieves a list of resource attributes for a specified resource with pagination support.
     *
     * @param resourceKey The key of the resource.
     * @param page        The page number.
     * @param perPage     The number of items per page.
     * @return An array of {@link ResourceAttributeRead} objects representing the resource attributes.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceAttributeRead[] list(String resourceKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
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

    /**
     * Retrieves a list of resource attributes for the specified resource with default number of items per page.
     *
     * @param resourceKey The key of the resource.
     * @param page        The page number.
     * @return An array of {@link ResourceAttributeRead} objects representing the resource attributes.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceAttributeRead[] list(String resourceKey, int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(resourceKey, page, 100);
    }

    /**
     * Retrieves a list of resource attributes for the specified resource with default pagination parameters.
     *
     * @param resourceKey The key of the resource.
     * @return An array of {@link ResourceAttributeRead} objects representing the resource attributes.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceAttributeRead[] list(String resourceKey) throws IOException, PermitApiError, PermitContextError {
        return this.list(resourceKey,1);
    }

    /**
     * Retrieves an attribute by its resource key and attribute key.
     *
     * @param resourceKey   The key of the resource.
     * @param attributeKey  The key of the attribute.
     * @return The {@link ResourceAttributeRead} object representing the resource attribute.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceAttributeRead get(String resourceKey, String attributeKey) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getResourceAttributesUrl(resourceKey, String.format("/%s", attributeKey));
        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .get()
        );

        return this.<ResourceAttributeRead>callApiAndParseJson(request, ResourceAttributeRead.class);
    }

    /**
     * Retrieves an attribute by its resource key and attribute key.
     * This is an alias for the {@link #get} method.
     *
     * @param resourceKey The key of the resource.
     * @param attributeKey The key of the attribute.
     * @return The {@link ResourceAttributeRead} object representing the resource attribute.
     * @throws IOException          If an I/O error occurs during the HTTP request.
     * @throws PermitApiError       If the Permit API returns a response with an error status code.
     * @throws PermitContextError   If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceAttributeRead getByKey(String resourceKey, String attributeKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(resourceKey, attributeKey);
    }

    /**
     * Retrieves an attribute by its resource ID and attribute ID.
     *
     * @param resourceId The ID of the resource.
     * @param attributeId The ID of the attribute.
     * @return The {@link ResourceAttributeRead} object representing the resource attribute.
     * @throws IOException          If an I/O error occurs during the HTTP request.
     * @throws PermitApiError       If the Permit API returns a response with an error status code.
     * @throws PermitContextError   If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceAttributeRead getById(UUID resourceId, UUID attributeId) throws IOException, PermitApiError, PermitContextError {
        return this.get(resourceId.toString(), attributeId.toString());
    }

    /**
     * Creates a new attribute under the specified resource.
     * Since resource respresents a type, the attribute itself represents a type and not a value.
     *
     * @param resourceKey The key of the resource.
     * @param attributeData The {@link ResourceAttributeCreate} object containing the data for the new resource attribute.
     * @return The {@link ResourceAttributeRead} object representing the created resource attribute.
     * @throws IOException          If an I/O error occurs during the HTTP request.
     * @throws PermitApiError       If the Permit API returns a response with an error status code.
     * @throws PermitContextError   If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceAttributeRead create(String resourceKey, ResourceAttributeCreate attributeData) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getResourceAttributesUrl(resourceKey,"");
        RequestBody jsonBody = getJsonRequestBody(attributeData);

        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .post(jsonBody)
        );

        return this.<ResourceAttributeRead>callApiAndParseJson(request, ResourceAttributeRead.class);
    }

    /**
     * Updates an existing resource attribute for the specified resource.
     *
     * @param resourceKey The key of the resource.
     * @param attributeKey The key of the attribute to update.
     * @param attributeData The {@link ResourceAttributeUpdate} object containing the updateddata for the resource attribute.
     * @return The {@link ResourceAttributeRead} object representing the updated resource attribute.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceAttributeRead update(String resourceKey, String attributeKey, ResourceAttributeUpdate attributeData) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getResourceAttributesUrl(resourceKey, String.format("/%s", attributeKey));
        RequestBody jsonBody = getJsonRequestBody(attributeData);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .patch(jsonBody)
        );

        return this.<ResourceAttributeRead>callApiAndParseJson(request, ResourceAttributeRead.class);
    }

    /**
     * Deletes a resource attribute for the specified resource.
     *
     * @param resourceKey   The key of the resource.
     * @param attributeKey  The key of the attribute to delete.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public void delete(String resourceKey, String attributeKey) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
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
