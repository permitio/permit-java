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

/**
 * The implementation of the {@link IResourceActionsApi} interface for interacting
 * with resource actions via the Permit REST API.
 */
public class ResourceActionsApi extends BaseApi implements IResourceActionsApi {
    /**
     * Constructs a new instance of the {@link ResourceActionsApi} class.
     *
     * @param client The {@link OkHttpClient} instance used to send HTTP requests.
     * @param config The {@link PermitConfig} instance containing the SDK configuration.
     */
    public ResourceActionsApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(ResourceActionsApi.class));
    }

    /**
     * Builds the URL for resource actions using the specified resource key and URL.
     *
     * @param resourceKey The key of the resource.
     * @param url The URL of the resource actions.
     * @return The built URL for resource actions.
     */
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

    /**
     * Retrieves a list of resource actions for the specified resource with pagination support.
     *
     * @param resourceKey The key of the resource.
     * @param page The page number.
     * @param perPage The number of items per page.
     * @return An array of {@link ResourceActionRead} objects representing the resource actions.
     * @throws IOException If an I/O error occurs during the HTTP request.
     * @throws PermitApiError If an error occurs in the Permit API.
     * @throws PermitContextError If there is an error in the Permit context.
     */
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

    /**
     * Retrieves a list of resource actions for the specified resource with default number of items per page.
     *
     * @param resourceKey The key of the resource.
     * @param page The page number.
     * @return An array of {@link ResourceActionRead} objects representing the resource actions.
     * @throws IOException If an I/O error occurs during the HTTP request.
     * @throws PermitApiError If an error occurs in the Permit API.
     * @throws PermitContextError If there is an error in the Permit context.
     */
    public ResourceActionRead[] list(String resourceKey, int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(resourceKey, page, 100);
    }

    /**
     * Retrieves a list of resource actions for the specified resource with default pagination parameters.
     *
     * @param resourceKey The key of the resource.
     * @return An array of {@link ResourceActionRead} objects representing the resource actions.
     * @throws IOException If an I/O error occurs during the HTTP request.
     * @throws PermitApiError If an error occurs in the Permit API.
     * @throws PermitContextError If there is an error in the Permit context.
     */
    public ResourceActionRead[] list(String resourceKey) throws IOException, PermitApiError, PermitContextError {
        return this.list(resourceKey,1);
    }

    /**
     * Retrieves a resource action by its resource key and action key.
     *
     * @param resourceKey The key of the resource.
     * @param actionKey The key of the action.
     * @return The {@link ResourceActionRead} object representing the resource action.
     * @throws IOException If an I/O error occurs during the HTTP request.
     * @throws PermitApiError If an error occurs in the Permit API.
     * @throws PermitContextError If there is an error in the Permit context.
     */
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

    /**
     * Retrieves a resource action by its resource key and action key.
     * This is an alias for the {@link #get} method.
     *
     * @param resourceKey The key of the resource.
     * @param actionKey The key of the action.
     * @return The {@link ResourceActionRead} object representing the resource action.
     * @throws IOException If an I/O error occurs during the HTTP request.
     * @throws PermitApiError If an error occurs in the Permit API.
     * @throws PermitContextError If there is an error in the Permit context.
     */
    public ResourceActionRead getByKey(String resourceKey, String actionKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(resourceKey, actionKey);
    }

    /**
     * Retrieves a resource action by its resource ID and action ID.
     *
     * @param resourceId The ID of the resource.
     * @param actionId The ID of the action.
     * @return The {@link ResourceActionRead} object representing the resource action.
     * @throws IOException If an I/O error occurs during the HTTP request.
     * @throws PermitApiError If an error occurs in the Permit API.
     * @throws PermitContextError If there is an error in the Permit context.
     */
    public ResourceActionRead getById(UUID resourceId, UUID actionId) throws IOException, PermitApiError, PermitContextError {
        return this.get(resourceId.toString(), actionId.toString());
    }

    /**
     * Creates a new action under the specified resource.
     *
     * @param resourceKey The key of the resource.
     * @param actionData The {@link ResourceActionCreate} object containing the data for the new resource action.
     * @return The {@link ResourceActionRead} object representing the created resource action.
     * @throws IOException If an I/O error occurs during the HTTP request.
     * @throws PermitApiError If an error occurs in the Permit API.
     * @throws PermitContextError If there is an error in the Permit context.
     */
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

    /**
     * Updates an existing resource action belonging to the specified resource.
     *
     * @param resourceKey The key of the resource.
     * @param actionKey The key of the action to update.
     * @param actionData The {@link ResourceActionUpdate} object containing the updated data for the resource action.
     * @return The {@link ResourceActionRead} object representing the updated resource action.
     * @throws IOException If an I/O error occurs during the HTTP request.
     * @throws PermitApiError If an error occurs in the Permit API.
     * @throws PermitContextError If there is an error in the Permit context.
     */
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

    /**
     * Deletes an action belonging to a specified resource.
     *
     * @param resourceKey The key of the resource.
     * @param actionKey The key of the action to delete.
     * @throws IOException If an I/O error occurs during the HTTP request.
     * @throws PermitApiError If an error occurs in the Permit API.
     * @throws PermitContextError If there is an error in the Permit context.
     */
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
