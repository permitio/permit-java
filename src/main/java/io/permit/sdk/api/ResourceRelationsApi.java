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

interface IResourceRelationsApi {
    RelationRead[] list(String resourceKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    RelationRead[] list(String resourceKey, int page) throws IOException, PermitApiError, PermitContextError;
    RelationRead[] list(String resourceKey) throws IOException, PermitApiError, PermitContextError;
    RelationRead get(String resourceKey, String relationKey) throws IOException, PermitApiError, PermitContextError;
    RelationRead getByKey(String resourceKey, String relationKey) throws IOException, PermitApiError, PermitContextError;
    RelationRead getById(UUID resourceId, UUID relationId) throws IOException, PermitApiError, PermitContextError;
    RelationRead create(String resourceKey, RelationCreate relationData) throws IOException, PermitApiError, PermitContextError;
    void delete(String resourceKey, String relationKey) throws IOException, PermitApiError, PermitContextError;
}

/**
 * The implementation of the {@link IResourceRelationsApi} interface for
 * interacting with resource relations using the Permit REST API.
 */
public class ResourceRelationsApi extends BaseApi implements IResourceRelationsApi {
    /**
     * Constructs a new instance of the {@link ResourceRelationsApi} class.
     *
     * @param client The {@link OkHttpClient} instance used to send HTTP requests.
     * @param config The {@link PermitConfig} instance containing the SDK configuration.
     */
    public ResourceRelationsApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(ResourceRelationsApi.class));
    }

    /**
     * Builds the URL for resource relations using the specified resource key and URL.
     *
     * @param resourceKey The key of the resource.
     * @param url         The URL of the resource relations.
     * @return The built URL for resource relations.
     */
    private String getResourceRelationsUrl(String resourceKey, String url) {
        return buildUrl(
                String.format(
                    "/v2/schema/%s/%s/resources/%s/relations%s",
                    config.getContext().getProject(),
                    config.getContext().getEnvironment(),
                    resourceKey,
                    url
                )
        );
    }

    /**
     * Retrieves a list of resource relations for a specified resource with pagination support.
     *
     * @param resourceKey The key of the resource.
     * @param page        The page number.
     * @param perPage     The number of items per page.
     RelationRead} objects representing the resource relations.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RelationRead[] list(String resourceKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getResourceRelationsUrl(resourceKey, "");
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
            return (new Gson()).fromJson(responseString, RelationRead[].class);
        }
    }

    /**
     * Retrieves a list of resource relations for the specified resource with default number of items per page.
     *
     * @param resourceKey The key of the resource.
     * @param page        The page number.
     RelationRead} objects representing the resource relations.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RelationRead[] list(String resourceKey, int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(resourceKey, page, 100);
    }

    /**
     * Retrieves a list of resource relations for the specified resource with default pagination parameters.
     *
     * @param resourceKey The key of the resource.
     RelationRead} objects representing the resource relations.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RelationRead[] list(String resourceKey) throws IOException, PermitApiError, PermitContextError {
        return this.list(resourceKey,1);
    }

    /**
     * Retrieves a relation by its resource key and relation key.
     *
     * @param resourceKey   The key of the resource.
     * @param relationKey  The key of the relation.
     RelationRead} object representing the resource relation.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RelationRead get(String resourceKey, String relationKey) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getResourceRelationsUrl(resourceKey, String.format("/%s", relationKey));
        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .get()
        );

        return this.callApiAndParseJson(request, RelationRead.class);
    }

    /**
     * Retrieves a relation by its resource key and relation key.
     * This is an alias for the {@link #get} method.
     *
     * @param resourceKey The key of the resource.
     * @param relationKey The key of the relation.
     RelationRead} object representing the resource relation.
     * @throws IOException          If an I/O error occurs during the HTTP request.
     * @throws PermitApiError       If the Permit API returns a response with an error status code.
     * @throws PermitContextError   If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RelationRead getByKey(String resourceKey, String relationKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(resourceKey, relationKey);
    }

    /**
     * Retrieves a relation by its resource ID and relation ID.
     *
     * @param resourceId The ID of the resource.
     * @param relationId The ID of the relation.
     RelationRead} object representing the resource relation.
     * @throws IOException          If an I/O error occurs during the HTTP request.
     * @throws PermitApiError       If the Permit API returns a response with an error status code.
     * @throws PermitContextError   If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RelationRead getById(UUID resourceId, UUID relationId) throws IOException, PermitApiError, PermitContextError {
        return this.get(resourceId.toString(), relationId.toString());
    }

    /**
     * Creates a new relation under the specified resource.
     * Since resource represents a type, the relation itself represents a type and not a value.
     *
     * @param resourceKey The key of the resource.
     * @param relationData The {@link RelationCreate} object containing the data for the new resource relation.
     RelationRead} object representing the created resource relation.
     * @throws IOException          If an I/O error occurs during the HTTP request.
     * @throws PermitApiError       If the Permit API returns a response with an error status code.
     * @throws PermitContextError   If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RelationRead create(String resourceKey, RelationCreate relationData) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getResourceRelationsUrl(resourceKey,"");
        RequestBody jsonBody = getJsonRequestBody(relationData);

        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .post(jsonBody)
        );

        return this.callApiAndParseJson(request, RelationRead.class);
    }

    /**
     * Deletes a resource relation for the specified resource.
     *
     * @param resourceKey   The key of the resource.
     * @param relationKey  The key of the relation to delete.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public void delete(String resourceKey, String relationKey) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getResourceRelationsUrl(resourceKey, String.format("/%s", relationKey));
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
