package io.permit.sdk.api;

import com.google.gson.Gson;
import io.permit.sdk.ApiContextLevel;
import io.permit.sdk.ApiKeyLevel;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.api.models.CreateOrUpdateResult;
import io.permit.sdk.enforcement.User;
import io.permit.sdk.openapi.models.*;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

interface IUsersApi {
    PaginatedResultUserRead list(int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    PaginatedResultUserRead list(int page) throws IOException, PermitApiError, PermitContextError;
    PaginatedResultUserRead list() throws IOException, PermitApiError, PermitContextError;
    UserRead get(String userKey) throws IOException, PermitApiError, PermitContextError;
    UserRead getByKey(String userKey) throws IOException, PermitApiError, PermitContextError;
    UserRead getById(UUID userId) throws IOException, PermitApiError, PermitContextError;
    UserRead create(UserCreate userData) throws IOException, PermitApiError, PermitContextError;
    UserRead update(String userKey, UserUpdate userData) throws IOException, PermitApiError, PermitContextError;
    CreateOrUpdateResult<UserRead> sync(UserCreate userData) throws IOException, PermitApiError, PermitContextError;
    CreateOrUpdateResult<UserRead> sync(User user) throws IOException, PermitApiError, PermitContextError;
    void delete(String userKey) throws IOException, PermitApiError, PermitContextError;
    RoleAssignmentRead assignRole(String userKey, String roleKey, String tenantKey) throws IOException, PermitApiError, PermitContextError;
    void unassignRole(String userKey, String roleKey, String tenantKey) throws IOException, PermitApiError, PermitContextError;
    RoleAssignmentRead[] getAssignedRoles(@NotNull String userKey, String tenantKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    RoleAssignmentRead[] getAssignedRoles(@NotNull String userKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    RoleAssignmentRead[] getAssignedRoles(@NotNull String userKey, int page) throws IOException, PermitApiError, PermitContextError;
    RoleAssignmentRead[] getAssignedRoles(@NotNull String userKey) throws IOException, PermitApiError, PermitContextError;
}

/**
 * The UsersApi class provides methods for interacting with the Permit Users REST API.
 */
public class UsersApi extends BaseApi implements IUsersApi {
    /**
     * Constructs a new UsersApi instance with the specified OkHttpClient and PermitConfig.
     *
     * @param client The OkHttpClient instance to be used for making HTTP requests.
     * @param config The PermitConfig instance that contains the SDK configuration.
     */
    public UsersApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(UsersApi.class));
    }

    /**
     * Constructs the URLs for users API endpoints.
     *
     * @param url The URL fragment.
     * @return The formatted URL for the endpoint.
     */
    private String getUsersUrl(String url) {
        if (Boolean.TRUE.equals(config.isProxyFactsViaPdp())) {
            return buildPdpUrl(
                    String.format(
                            "/facts/users%s",
                            url
                    )
            );
        } else {
            return buildUrl(
                    String.format(
                            "/v2/facts/%s/%s/users%s",
                            config.getContext().getProject(),
                            config.getContext().getEnvironment(),
                            url
                    )
            );
        }
    }

    /**
     * Retrieves a paginated result of users.
     *
     * @param page    The page number of the result set to retrieve.
     * @param perPage The number of items per page.
     * @return A PaginatedResultUserRead object representing the retrieved paginated result of users.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public PaginatedResultUserRead list(int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getUsersUrl("");
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
            return (new Gson()).fromJson(responseString, PaginatedResultUserRead.class);
        }
    }

    /**
     * Retrieves a paginated result of users with the default number of items per page.
     *
     * @param page The page number of the result set to retrieve.
     * @return A PaginatedResultUserRead object representing the retrieved paginated result of users.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public PaginatedResultUserRead list(int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(page, 100);
    }

    /**
     * Retrieves a paginated result of users with default pagination.
     *
     * @return A PaginatedResultUserRead object representing the retrieved paginated result of users.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public PaginatedResultUserRead list() throws IOException, PermitApiError, PermitContextError {
        return this.list(1);
    }

    /**
     * Retrieves a user by its key.
     *
     * @param userKey The key of the user.
     * @return A UserRead object representing the retrieved user.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public UserRead get(String userKey) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getUsersUrl(String.format("/%s", userKey));
        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .get()
        );

        return this.<UserRead>callApiAndParseJson(request, UserRead.class);
    }

    /**
     * Retrieves a user by its key.
     *
     * @param userKey The key of the user.
     * @return A UserRead object representing the retrieved user.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public UserRead getByKey(String userKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(userKey);
    }

    /**
     * Retrieves a user by its ID.
     *
     * @param userId The ID of the user.
     * @return A UserRead object representing the retrieved user.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public UserRead getById(UUID userId) throws IOException, PermitApiError, PermitContextError {
        return this.get(userId.toString());
    }

    /**
     * Creates a new user.
     *
     * @param userData The UserCreate object representing the user data.
     * @return A UserRead object representing the created user.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public UserRead create(UserCreate userData) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getUsersUrl("");
        RequestBody jsonBody = getJsonRequestBody(userData);

        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .post(jsonBody)
        );

        return this.<UserRead>callApiAndParseJson(request, UserRead.class);
    }

    /**
     * Updates an existing user.
     *
     * @param userKey  The key of the user to be updated.
     * @param userData The UserUpdate object representing the updated user data.
     * @return A UserRead object representing the updated user.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public UserRead update(String userKey, UserUpdate userData) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getUsersUrl(String.format("/%s", userKey));
        RequestBody jsonBody = getJsonRequestBody(userData);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .patch(jsonBody)
        );

        return this.<UserRead>callApiAndParseJson(request, UserRead.class);
    }

    /**
     * Creates a user or updates a user in place (if the user key already exists).
     *
     * @param userData The UserCreate object representing the user data to synchronize.
     * @return A CreateOrUpdateResult object representing the result of the synchronization.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public CreateOrUpdateResult<UserRead> sync(UserCreate userData) throws IOException, PermitApiError, PermitContextError {
        if (userData.key == null) {
            throw new PermitApiError(
                "You cannot pass a null key to permit.api.users.sync()",
                406, // not acceptable
                "{}"
            );
        }

        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getUsersUrl(String.format("/%s", userData.key)); // TODO: fix url to PUT /v2/.../users
        RequestBody jsonBody = getJsonRequestBody(userData);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .put(jsonBody)
        );

        try (Response response = client.newCall(request).execute()) {
            String responseString = processResponseBody(response);
            UserRead result = (new Gson()).fromJson(responseString, UserRead.class);
            boolean created = (response.code() == 201);
            return new CreateOrUpdateResult<UserRead>(result, created);
        }
    }

    /**
     * Creates a user or updates a user in place (if the user key already exists).
     * This override uses the same User class used for permit.check() authorization checks.
     *
     * @param user The User object representing the user to synchronize.
     * @return A CreateOrUpdateResult object representing the result of the synchronization.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public CreateOrUpdateResult<UserRead> sync(User user) throws IOException, PermitApiError, PermitContextError {
        UserCreate userData = new UserCreate(user.getKey());
        if (user.getEmail() != null) {
            userData.withEmail(user.getEmail());
        }
        if (user.getFirstName() != null) {
            userData.withFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            userData.withLastName(user.getLastName());
        }
        if (user.getAttributes() != null && user.getAttributes().size() > 0) {
            userData.withAttributes(user.getAttributes());
        }

        return this.sync(userData);
    }

    /**
     * Deletes a user.
     *
     * @param userKey The key of the user to be deleted.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public void delete(String userKey) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getUsersUrl(String.format("/%s", userKey));
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
     * Assigns a role to a user in a specific tenant.
     *
     * @param userKey   The key of the user.
     * @param roleKey   The key of the role to assign.
     * @param tenantKey The key of the tenant.
     * @return A RoleAssignmentRead object representing the assigned role.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RoleAssignmentRead assignRole(String userKey, String roleKey, String tenantKey) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getUsersUrl(String.format("/%s/roles", userKey));
        RequestBody jsonBody = getJsonRequestBody(new UserRoleCreate(roleKey, tenantKey));

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .post(jsonBody)
        );

        return this.<RoleAssignmentRead>callApiAndParseJson(request, RoleAssignmentRead.class);
    }

    /**
     * Unassigns a role from a user in a specific tenant.
     *
     * @param userKey   The key of the user.
     * @param roleKey   The key of the role to unassign.
     * @param tenantKey The key of the tenant.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public void unassignRole(String userKey, String roleKey, String tenantKey) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getUsersUrl(String.format("/%s/roles", userKey));
        RequestBody jsonBody = getJsonRequestBody(new UserRoleRemove(roleKey, tenantKey));

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
     * Retrieves a paginated result of role assignments for a specific user and tenant.
     *
     * @param userKey   The key of the user.
     * @param tenantKey The key of the tenant (optional, can be null).
     * @param page      The page number of the result set to retrieve.
     * @param perPage   The number of items per page.
     * @return An array of RoleAssignmentRead objects representing the retrieved role assignments.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RoleAssignmentRead[] getAssignedRoles(@NotNull String userKey, String tenantKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = buildUrl(
            String.format(
                "/v2/facts/%s/%s/role_assignments",
                config.getContext().getProject(),
                config.getContext().getEnvironment()
            )
        );
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        if (tenantKey != null) {
            urlBuilder.addQueryParameter("tenant", tenantKey);
        }
        Request request = buildRequest(
            new Request.Builder()
                .url(
                    urlBuilder
                        .addQueryParameter("user", userKey)
                        .addQueryParameter("page", Integer.toString(page))
                        .addQueryParameter("per_page", Integer.toString(perPage))
                        .build()
                )
                .get()
        );

        try (Response response = client.newCall(request).execute()) {
            String responseString = processResponseBody(response);
            return (new Gson()).fromJson(responseString, RoleAssignmentRead[].class);
        }
    }

    /**
     * Retrieves a paginated result of roles assigned to the user across all tenants.
     * For example - if the user is Admin in tenant `green`, and Viewer in tenant `blue`,
     * this override of getAssignedRoles will return both roles (will not filter on one tenant).
     *
     * @param userKey The key of the user.
     * @param page    The page number of the result set to retrieve.
     * @param perPage The number of items per page.
     * @return An array of RoleAssignmentRead objects representing the retrieved role assignments.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RoleAssignmentRead[] getAssignedRoles(@NotNull String userKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        return this.getAssignedRoles(userKey, null, page, perPage);
    }

    /**
     * Retrieves a paginated result of of roles assigned to the user across all tenants with the default number of items per page.
     *
     * @param userKey The key of the user.
     * @param page    The page number of the result set to retrieve.
     * @return An array of RoleAssignmentRead objects representing the retrieved role assignments.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RoleAssignmentRead[] getAssignedRoles(@NotNull String userKey, int page) throws IOException, PermitApiError, PermitContextError {
        return this.getAssignedRoles(userKey, page, 100);
    }

    /**
     * Retrieves a paginated result of of roles assigned to the user across all tenants with the default pagination.
     *
     * @param userKey The key of the user.
     * @return An array of RoleAssignmentRead objects representing the retrieved role assignments.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RoleAssignmentRead[] getAssignedRoles(@NotNull String userKey) throws IOException, PermitApiError, PermitContextError {
        return this.getAssignedRoles(userKey, 1);
    }
}
