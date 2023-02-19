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
import java.util.UUID;

public class RolesApi extends BaseApi {
    public RolesApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(RolesApi.class));
    }

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

    public RoleRead[] list() throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getRolesUrl("");
        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .get()
        );

        try (Response response = client.newCall(request).execute()) {
            String responseString = processResponseBody(response);
            return (new Gson()).fromJson(responseString, RoleRead[].class);
        }
    }

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

    public RoleRead getByKey(String roleKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(roleKey);
    }

    public RoleRead getById(UUID roleId) throws IOException, PermitApiError, PermitContextError {
        return this.get(roleId.toString());
    }

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
