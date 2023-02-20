package io.permit.sdk.api;

import com.google.gson.Gson;
import io.permit.sdk.ApiKeyLevel;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.api.models.CreateOrUpdateResult;
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
    void delete(String userKey) throws IOException, PermitApiError, PermitContextError;
    RoleAssignmentRead assignRole(String userKey, String roleKey, String tenantKey) throws IOException, PermitApiError, PermitContextError;
    void unassignRole(String userKey, String roleKey, String tenantKey) throws IOException, PermitApiError, PermitContextError;
    RoleAssignmentRead[] getAssignedRoles(@NotNull String userKey, String tenantKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    RoleAssignmentRead[] getAssignedRoles(@NotNull String userKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    RoleAssignmentRead[] getAssignedRoles(@NotNull String userKey, int page) throws IOException, PermitApiError, PermitContextError;
    RoleAssignmentRead[] getAssignedRoles(@NotNull String userKey) throws IOException, PermitApiError, PermitContextError;
}

public class UsersApi extends BaseApi implements IUsersApi {
    public UsersApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(UsersApi.class));
    }

    private String getUsersUrl(String url) {
        return buildUrl(
                String.format(
                        "/v2/facts/%s/%s/users%s",
                        config.getContext().getProject(),
                        config.getContext().getEnvironment(),
                        url
                )
        );
    }

    public PaginatedResultUserRead list(int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
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

    public PaginatedResultUserRead list(int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(page, 100);
    }

    public PaginatedResultUserRead list() throws IOException, PermitApiError, PermitContextError {
        return this.list(1);
    }

    public UserRead get(String userKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getUsersUrl(String.format("/%s", userKey));
        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .get()
        );

        return this.<UserRead>callApiAndParseJson(request, UserRead.class);
    }

    public UserRead getByKey(String userKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(userKey);
    }

    public UserRead getById(UUID userId) throws IOException, PermitApiError, PermitContextError {
        return this.get(userId.toString());
    }

    public UserRead create(UserCreate userData) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getUsersUrl("");
        RequestBody jsonBody = getJsonRequestBody(userData);

        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .post(jsonBody)
        );

        return this.<UserRead>callApiAndParseJson(request, UserRead.class);
    }

    public UserRead update(String userKey, UserUpdate userData) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getUsersUrl(String.format("/%s", userKey));
        RequestBody jsonBody = getJsonRequestBody(userData);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .patch(jsonBody)
        );

        return this.<UserRead>callApiAndParseJson(request, UserRead.class);
    }

    public CreateOrUpdateResult<UserRead> sync(UserCreate userData) throws IOException, PermitApiError, PermitContextError {
        if (userData.key == null) {
            throw new PermitApiError(
                "You cannot pass a null key to permit.api.users.sync()",
                406, // not acceptable
                "{}"
            );
        }

        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
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
            boolean created = (response.code() == 200); // TODO: fix response code to 201
            return new CreateOrUpdateResult<UserRead>(result, created);
        }
    }

    public void delete(String userKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
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

    public RoleAssignmentRead assignRole(String userKey, String roleKey, String tenantKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getUsersUrl(String.format("/%s/roles", userKey));
        RequestBody jsonBody = getJsonRequestBody(new UserRoleCreate(roleKey, tenantKey));

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .post(jsonBody)
        );

        return this.<RoleAssignmentRead>callApiAndParseJson(request, RoleAssignmentRead.class);
    }

    public void unassignRole(String userKey, String roleKey, String tenantKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
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

    public RoleAssignmentRead[] getAssignedRoles(@NotNull String userKey, String tenantKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
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

    public RoleAssignmentRead[] getAssignedRoles(@NotNull String userKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        return this.getAssignedRoles(userKey, null, page, perPage);
    }

    public RoleAssignmentRead[] getAssignedRoles(@NotNull String userKey, int page) throws IOException, PermitApiError, PermitContextError {
        return this.getAssignedRoles(userKey, page, 100);
    }

    public RoleAssignmentRead[] getAssignedRoles(@NotNull String userKey) throws IOException, PermitApiError, PermitContextError {
        return this.getAssignedRoles(userKey, 1);
    }
}
