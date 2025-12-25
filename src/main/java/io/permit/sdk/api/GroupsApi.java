package io.permit.sdk.api;

import com.google.gson.Gson;
import io.permit.sdk.ApiContextLevel;
import io.permit.sdk.ApiKeyLevel;
import io.permit.sdk.PermitConfig;

import io.permit.sdk.openapi.models.*;
import okhttp3.*;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

// This is a partial support for the Groups API, some methods are currently missing.
interface IGroupsApi {
    GroupRead[] list(int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    GroupRead[] list(int page) throws IOException, PermitApiError, PermitContextError;
    GroupRead[] list() throws IOException, PermitApiError, PermitContextError;
    GroupRead create(GroupCreate groupData) throws IOException, PermitApiError, PermitContextError;
    void delete(String groupInstanceKey) throws IOException, PermitApiError, PermitContextError;
    GroupRead get(String groupInstanceKey) throws IOException, PermitApiError, PermitContextError;
    GroupRead getByKey(String groupInstanceKey) throws IOException, PermitApiError, PermitContextError;
    GroupRead getById(UUID groupInstanceId) throws IOException, PermitApiError, PermitContextError;
    GroupRead assignRoleToGroup(String groupInstanceKey, GroupAddRole groupAddRole) throws IOException, PermitApiError, PermitContextError;
    GroupRead assignUserToGroup(String userId, String groupInstanceKey, String tenant) throws IOException, PermitApiError, PermitContextError;
    void removeRoleFromGroup(String groupInstanceKey, GroupAddRole groupAddRole) throws IOException, PermitApiError, PermitContextError;
    void removeUserFromGroup(String userId, String groupInstanceKey, String tenant) throws IOException, PermitApiError, PermitContextError;
}

public class GroupsApi extends BaseApi implements IGroupsApi {
    /**
     * Constructs a new GroupsApi instance.
     *
     * @param client The OkHttpClient instance used for making HTTP requests.
     * @param config The PermitConfig instance containing the SDK configuration.
     */
    public GroupsApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(GroupsApi.class));
    }

    /**
     * Constructs the URL for the Groups API.
     *
     * @param url The URL fragment.
     * @return The constructed URL string.
     */
    private String getGroupsUrl(String url) {
        return buildUrl(
                String.format(
                        "/v2/schema/%s/%s/groups%s",
                        config.getContext().getProject(),
                        config.getContext().getEnvironment(),
                        url
                )
        );
    }

    /**
     * Retrieves a paginated result of groups.
     *
     * @param page    The page number of the result set to retrieve.
     * @param perPage The number of items per page.
     * @return An array of {@link GroupRead} objects representing the retrieved groups.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    @Override
    public GroupRead[] list(int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getGroupsUrl("");
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
            return (new Gson()).fromJson(responseString, GroupRead[].class);
        }
    }

    /**
     * Retrieves a paginated result of groups with default pagination.
     *
     * @param page The page number of the result set to retrieve.
     * @return A list of {@link GroupRead} objects representing the retrieved paginated result of groups.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    @Override
    public GroupRead[] list(int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(page, 100);
    }

    /**
     * Retrieves a paginated result of groups with default pagination.
     *
     * @return A list of {@link GroupRead} objects representing the retrieved paginated result of groups.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    @Override
    public GroupRead[] list() throws IOException, PermitApiError, PermitContextError {
        return this.list(1);
    }

    /**
     * Retrieves a group by its group instance key.
     *
     * @param groupInstanceKey The key of the group.
     * @return The {@link GroupRead} object representing the group.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    @Override
    public GroupRead get(String groupInstanceKey) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getGroupsUrl(String.format("/%s", groupInstanceKey));
        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .get()
        );

        return this.<GroupRead>callApiAndParseJson(request, GroupRead.class);
    }

    /**
     * Retrieves a group by its group instance key.
     *
     * @param groupInstanceKey The key of the group.
     * @return The {@link GroupRead} object representing the group.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    @Override
    public GroupRead getByKey(String groupInstanceKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(groupInstanceKey);
    }

    /**
     * Retrieves a group by its ID.
     *
     * @param groupInstanceId The ID of the group.
     * @return The {@link GroupRead} object representing the group.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    @Override
    public GroupRead getById(UUID groupInstanceId) throws IOException, PermitApiError, PermitContextError {
        return this.get(groupInstanceId.toString());
    }

    /**
     * Creates a new group.
     *
     * @param groupData The {@link GroupCreate} object representing the group data.
     * @return A {@link GroupRead} object representing the created group.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    @Override
    public GroupRead create(GroupCreate groupData) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getGroupsUrl("");
        RequestBody jsonBody = getJsonRequestBody(groupData);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .post(jsonBody)
        );

        return this.<GroupRead>callApiAndParseJson(request, GroupRead.class);
    }

    /**
     * Deletes a group with the specified group instance key.
     *
     * @param groupInstanceKey The key of the group to delete.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    @Override
    public void delete(String groupInstanceKey) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getGroupsUrl(String.format("/%s", groupInstanceKey));
        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .delete()
        );

        try (Response response = client.newCall(request).execute()) {
            processResponseBody(response, false);
        }
    }

    /**
     * Assign a role to the group, all users in this group will now have this role.
     * It will create relation between the group and the resource, relationship between the resource instances and derivation from the member role to this role.
     *
     * @param groupInstanceKey The key of the group to assign the role to.
     * @param groupAddRole The {@link GroupAddRole} object containing the assignment data.
     * @return The {@link GroupRead} object representing the group after assigning the role.
     * @throws IOException          If an I/O error occurs during the HTTP request.
     * @throws PermitApiError       If the Permit API returns a response with an error status code.
     * @throws PermitContextError   If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    @Override
    public GroupRead assignRoleToGroup(String groupInstanceKey, GroupAddRole groupAddRole) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getGroupsUrl(String.format("/%s/roles", groupInstanceKey));
        RequestBody jsonBody = getJsonRequestBody(groupAddRole);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .post(jsonBody)
        );

        return this.<GroupRead>callApiAndParseJson(request, GroupRead.class);
    }

    /**
     * Assign a user to the group.
     *
     * @param userId The id of the user to assign to the group.
     * @param groupInstanceKey The key of the group to assign the user to.
     * @param tenant The tenant key or id that the user belongs to.
     * @return The {@link GroupRead} object representing the group after assigning the user.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    @Override
    public GroupRead assignUserToGroup(String userId, String groupInstanceKey, String tenant) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getGroupsUrl(String.format("/%s/users/%s", groupInstanceKey, userId));
        RequestBody jsonBody = getJsonRequestBody(new GroupAssignUser(tenant));

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .put(jsonBody)
        );

        return this.<GroupRead>callApiAndParseJson(request, GroupRead.class);
    }

    /**
     * Remove a role from the group, all users in this group will lose this role.
     *
     * @param groupInstanceKey The key of the group to assign the user.
     * @param groupAddRole The {@link GroupAddRole} object containing the assignment data.
     * @throws IOException          If an I/O error occurs during the HTTP request.
     * @throws PermitApiError       If the Permit API returns a response with an error status code.
     * @throws PermitContextError   If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    @Override
    public void removeRoleFromGroup(String groupInstanceKey, GroupAddRole groupAddRole) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getGroupsUrl(String.format("/%s/roles", groupInstanceKey));
        RequestBody jsonBody = getJsonRequestBody(groupAddRole);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .delete(jsonBody)
        );

        try (Response response = client.newCall(request).execute()) {
            processResponseBody(response, false);
        }
    }

    /**
     * Remove a user from the group.
     *
     * @param userId The id of the user to remove from the group.
     * @param groupInstanceKey The key of the group to remove the user from.
     * @param tenant The tenant key or id that the user belongs to.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    @Override
    public void removeUserFromGroup(String userId, String groupInstanceKey, String tenant) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getGroupsUrl(String.format("/%s/users/%s", groupInstanceKey, userId));
        RequestBody jsonBody = getJsonRequestBody(new GroupAssignUser(tenant));

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .delete(jsonBody)
        );

        try (Response response = client.newCall(request).execute()) {
            processResponseBody(response, false);
        }
    }
}
