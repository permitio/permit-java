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

/**
 * The {@code ResourceActionGroupsApi} class provides methods for interacting with action groups in the Permit API.
 * It implements the {@code IResourceActionGroupsApi} interface.
 */
public class ResourceActionGroupsApi extends BaseApi implements IResourceActionGroupsApi {
    /**
     * Constructs an instance of the {@code ResourceActionGroupsApi} class.
     *
     * @param client The {@link OkHttpClient} instance to be used for HTTP requests.
     * @param config The {@link PermitConfig} instance containing the API configuration.
     */
    public ResourceActionGroupsApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(ResourceActionGroupsApi.class));
    }

    /**
     * Constructs the URL for action groups based on the resource key and URL path.
     *
     * @param resourceKey The resource key.
     * @param url The URL path.
     * @return The constructed URL for action groups.
     */
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

    /**
     * Lists action groups for the specified resource with pagination.
     *
     * @param resourceKey The resource key.
     * @param page The page number.
     * @param perPage The number of action groups per page.
     * @return An array of {@link ResourceActionGroupRead} objects representing the action groups.
     * @throws IOException If an I/O error occurs during the HTTP request.
     * @throws PermitApiError If the Permit API returns a response with an error status code.
     * @throws PermitContextError If there is an error in the Permit context.
     */
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

    /**
     * Lists action groups for the specified resource with the default number of action groups per page.
     *
     * @param resourceKey The resource key.
     * @param page The page number.
     * @return An array of {@link ResourceActionGroupRead} objects representing the action groups.
     * @throws IOException If an I/O error occurs during the HTTP request.
     * @throws PermitApiError If the Permit API returns a response with an error status code.
     * @throws PermitContextError If there is an error in the Permit context.
     */
    public ResourceActionGroupRead[] list(String resourceKey, int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(resourceKey, page, 100);
    }

    /**
     * Lists all action groups for the specified resource with the default page number and number of action groups per page.
     *
     * @param resourceKey The resource key.
     * @return An array of {@link ResourceActionGroupRead} objects representing the action groups.
     * @throws IOException If an I/O error occurs during the HTTP request.
     * @throws PermitApiError If the Permit API returns a response with an error status code.
     * @throws PermitContextError If there is an error in the Permit context.
     */
    public ResourceActionGroupRead[] list(String resourceKey) throws IOException, PermitApiError, PermitContextError {
        return this.list(resourceKey,1);
    }

    /**
     * Retrieves a action group by its resource key and group key.
     *
     * @param resourceKey The resource key.
     * @param groupKey The group key.
     * @return The {@link ResourceActionGroupRead} object representing the action group.
     * @throws IOException If an I/O error occurs during the HTTP request.
     * @throws PermitApiError If the Permit API returns a response with an error status code.
     * @throws PermitContextError If there is an error in the Permit context.
     */
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

    /**
     * Retrieves a action group by its resource key and group key.
     * This method is an alias for the {@link #get(String,String)} method.
     *
     * @param resourceKey The resource key.
     * @param groupKey The group key.
     * @return The {@link ResourceActionGroupRead} object representing the action group.
     * @throws IOException If an I/O error occurs during the HTTP request.
     * @throws PermitApiError If the Permit API returns a response with an error status code.
     * @throws PermitContextError If there is an error in the Permit context.
     */
    public ResourceActionGroupRead getByKey(String resourceKey, String groupKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(resourceKey, groupKey);
    }

    /**
     * Retrieves a action group by its resource ID and group ID.
     *
     * @param resourceId The resource ID.
     * @param groupId The group ID.
     * @return The {@link ResourceActionGroupRead} object representing the action group.
     * @throws IOException If an I/O error occurs during the HTTP request.
     * @throws PermitApiError If the Permit API returns a response with an error status code.
     * @throws PermitContextError If there is an error in the Permit context.
     */
    public ResourceActionGroupRead getById(UUID resourceId, UUID groupId) throws IOException, PermitApiError, PermitContextError {
        return this.get(resourceId.toString(), groupId.toString());
    }

    /**
     * Creates a new action group under the specified resource.
     *
     * @param resourceKey The resource key.
     * @param groupData The {@link ResourceActionGroupCreate} object containing the data for the new action group.
     * @return The {@link ResourceActionGroupRead} object representing the created action group.
     * @throws IOException If an I/O error occurs during the HTTP request.
     * @throws PermitApiError If the Permit API returns a response with an error status code.
     * @throws PermitContextError If there is an error in the Permit context.
     */
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

    /**
     * Deletes a action group by its resource key and group key.
     *
     * @param resourceKey The resource key.
     * @param groupKey The group key.
     * @throws IOException If an I/O error occurs during the HTTP request.
     * @throws PermitApiError If the Permit API returns a response with an error status code.
     * @throws PermitContextError If there is an error in the Permit context.
     */
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
