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

interface IRolesApi {
    RoleRead[] list(int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    RoleRead[] list(int page) throws IOException, PermitApiError, PermitContextError;
    RoleRead[] list() throws IOException, PermitApiError, PermitContextError;
    RoleRead get(String roleKey) throws IOException, PermitApiError, PermitContextError;
    RoleRead getByKey(String roleKey) throws IOException, PermitApiError, PermitContextError;
    RoleRead getById(UUID roleId) throws IOException, PermitApiError, PermitContextError;
    RoleRead create(RoleCreate roleData) throws IOException, PermitApiError, PermitContextError;
    RoleRead update(String roleKey, RoleUpdate roleData) throws IOException, PermitApiError, PermitContextError;
    void delete(String roleKey) throws IOException, PermitApiError, PermitContextError;
    RoleRead assignPermissions(String roleKey, ArrayList<String> permissions) throws IOException, PermitApiError, PermitContextError;
    RoleRead removePermissions(String roleKey, ArrayList<String> permissions) throws IOException, PermitApiError, PermitContextError;
}

/**
 * The RolesApi class provides methods for interacting with roles using the Permit API.
 */
public class RolesApi extends BaseApi implements IRolesApi {
    /**
     * Constructs a new RolesApi instance.
     *
     * @param client The OkHttpClient instance used for making HTTP requests.
     * @param config The PermitConfig instance containing the SDK configuration.
     */
    public RolesApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(RolesApi.class));
    }

    /**
     * Constructs the URL for the Roles API.
     *
     * @param url The URL fragment.
     * @return The constructed URL string.
     */
    private String getRolesUrl(String url) {
        return buildUrl(
                String.format(
                        "/v2/schema/%s/%s/roles%s",
                        config.getContext().getProject(),
                        config.getContext().getEnvironment(),
                        url
                )
        );
    }

    /**
     * Retrieves a paginated list of roles.
     *
     * @param page    The page number of the results.
     * @param perPage The number of roles per page.
     * @return An array of RoleRead objects representing the roles.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured SDK context {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RoleRead[] list(int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getRolesUrl("");
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
            return (new Gson()).fromJson(responseString, RoleRead[].class);
        }
    }

    /**
     * Retrieves a paginated list of roles with the default number of roles per page.
     *
     * @param page The page number of the results.
     * @return An array of RoleRead objects representing the roles.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RoleRead[] list(int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(page, 100);
    }

    /**
     * Retrieves the first page of roles with the default number of roles per page.
     *
     * @return An array of RoleRead objects representing the roles.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RoleRead[] list() throws IOException, PermitApiError, PermitContextError {
        return this.list(1);
    }

    /**
     * Retrieves a role with the specified role key.
     *
     * @param roleKey The key of the role.
     * @return The RoleRead object representing the role.
     * @throws IOException           If an I/O error occurs during     * the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RoleRead get(String roleKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getRolesUrl(String.format("/%s", roleKey));
        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .get()
        );

        return this.<RoleRead>callApiAndParseJson(request, RoleRead.class);
    }

    /**
     * Retrieves a role with the specified role key.
     * This is an alias for the {@link #get} method.
     *
     * @param roleKey The key of the role.
     * @return The RoleRead object representing the role.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RoleRead getByKey(String roleKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(roleKey);
    }

    /**
     * Retrieves a role with the specified role ID.
     *
     * @param roleId The ID of the role.
     * @return The RoleRead object representing the role.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RoleRead getById(UUID roleId) throws IOException, PermitApiError, PermitContextError {
        return this.get(roleId.toString());
    }

    /**
     * Creates a new role with the specified role data.
     *
     * @param roleData The RoleCreate object representing the role data.
     * @return The RoleRead object representing the created role.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RoleRead create(RoleCreate roleData) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getRolesUrl("");
        RequestBody jsonBody = getJsonRequestBody(roleData);

        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .post(jsonBody)
        );

        return this.<RoleRead>callApiAndParseJson(request, RoleRead.class);
    }

    /**
     * Updates a role with the specified role key.
     *
     * @param roleKey  The key of the role to update.
     * @param roleData The RoleUpdate object representing the updated role data.
     * @return The RoleRead object representing the updated role.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RoleRead update(String roleKey, RoleUpdate roleData) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getRolesUrl(String.format("/%s", roleKey));
        RequestBody jsonBody = getJsonRequestBody(roleData);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .patch(jsonBody)
        );

        return this.<RoleRead>callApiAndParseJson(request, RoleRead.class);
    }

    /**
    * Deletes a role with the specified role key.
    *
    * @param roleKey The key of the role to delete.
    * @throws IOException           If an I/O error occurs during the HTTP request.
    * @throws PermitApiError        If the Permit API returns a response with an error status code.
    * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
    */
    public void delete(String roleKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getRolesUrl(String.format("/%s", roleKey));
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
    * Assigns permissions to a role with the specified role key.
    *
    * @param roleKey      The key of the role to assign permissions to.
    * @param permissions  The list of permissions to assign.
    * @return The RoleRead object representing the role after the permissions are assigned.
    * @throws IOException           If an I/O error occurs during the HTTP request.
    * @throws PermitApiError        If the Permit API returns a response with an error status code.
    * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
    */
    public RoleRead assignPermissions(String roleKey, ArrayList<String> permissions) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getRolesUrl(String.format("/%s/permissions", roleKey));
        RequestBody jsonBody = getJsonRequestBody(new AddRolePermissions(permissions));

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .post(jsonBody)
        );

        return this.<RoleRead>callApiAndParseJson(request, RoleRead.class);
    }

    /**
    * Removes permissions from a role with the specified role key.
    *
    * @param roleKey      The key of the role to remove permissions from.
    * @param permissions  The list of permissions to remove.
    * @return The RoleRead object representing the role after the permissions are removed.
    * @throws IOException           If an I/O error occurs during the HTTP request.
    * @throws PermitApiError        If the Permit API returns a response with an error status code.
    * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
    */
    public RoleRead removePermissions(String roleKey, ArrayList<String> permissions) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getRolesUrl(String.format("/%s/permissions", roleKey));
        RequestBody jsonBody = getJsonRequestBody(new RemoveRolePermissions(permissions));

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .delete(jsonBody)
        );

        return this.<RoleRead>callApiAndParseJson(request, RoleRead.class);
    }
}
