package io.permit.sdk.api;

import com.google.gson.Gson;
import io.permit.sdk.ApiKeyLevel;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.openapi.models.*;
import okhttp3.*;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

interface IRoleAssignmentsApi {
    RoleAssignmentRead[] list(String userKey, String tenantKey, String roleKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    RoleAssignmentRead[] list(String userKey, String tenantKey, String roleKey, int page) throws IOException, PermitApiError, PermitContextError;
    RoleAssignmentRead[] list(String userKey, String tenantKey, String roleKey) throws IOException, PermitApiError, PermitContextError;
    RoleAssignmentRead assign(RoleAssignmentCreate assignment) throws IOException, PermitApiError, PermitContextError;
    void unassign(RoleAssignmentRemove unassignment) throws IOException, PermitApiError, PermitContextError;
    BulkRoleAssignmentReport bulkAssign(List<RoleAssignmentCreate> assignments) throws IOException, PermitApiError, PermitContextError;
    BulkRoleUnassignmentReport bulkUnassign(List<RoleAssignmentRemove> unassignments) throws IOException, PermitApiError, PermitContextError;
}

public class RoleAssignmentsApi extends BaseApi implements IRoleAssignmentsApi {
    public RoleAssignmentsApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(RoleAssignmentsApi.class));
    }

    private String getRoleAssignmentsUrl(String url) {
        return buildUrl(
                String.format(
                        "/v2/facts/%s/%s/role_assignments%s",
                        config.getContext().getProject(),
                        config.getContext().getEnvironment(),
                        url
                )
        );
    }

    public RoleAssignmentRead[] list(String userKey, String tenantKey, String roleKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getRoleAssignmentsUrl("");
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        if (userKey != null) {
            urlBuilder.addQueryParameter("user", userKey);
        }
        if (tenantKey != null) {
            urlBuilder.addQueryParameter("tenant", tenantKey);
        }
        if (roleKey != null) {
            urlBuilder.addQueryParameter("role", roleKey);
        }
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
            return (new Gson()).fromJson(responseString, RoleAssignmentRead[].class);
        }
    }

    public RoleAssignmentRead[] list(String userKey, String tenantKey, String roleKey, int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(userKey, tenantKey, roleKey, page, 100);
    }

    public RoleAssignmentRead[] list(String userKey, String tenantKey, String roleKey) throws IOException, PermitApiError, PermitContextError {
        return this.list(userKey, tenantKey, roleKey, 1);
    }

    public RoleAssignmentRead assign(RoleAssignmentCreate assignment) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getRoleAssignmentsUrl("");
        RequestBody jsonBody = getJsonRequestBody(assignment);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .post(jsonBody)
        );

        return this.<RoleAssignmentRead>callApiAndParseJson(request, RoleAssignmentRead.class);
    }

    public void unassign(RoleAssignmentRemove unassignment) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getRoleAssignmentsUrl("");
        RequestBody jsonBody = getJsonRequestBody(unassignment);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .delete(jsonBody)
        );

        try (Response response = client.newCall(request).execute()) {
            processResponseBody(response, false);
        }
    }

    public BulkRoleAssignmentReport bulkAssign(List<RoleAssignmentCreate> assignments) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getRoleAssignmentsUrl("/bulk");
        RequestBody jsonBody = getJsonRequestBody(assignments);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .post(jsonBody)
        );

        return this.<BulkRoleAssignmentReport>callApiAndParseJson(request, BulkRoleAssignmentReport.class);
    }

    public BulkRoleUnassignmentReport bulkUnassign(List<RoleAssignmentRemove> unassignments) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getRoleAssignmentsUrl("/bulk");
        RequestBody jsonBody = getJsonRequestBody(unassignments);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .delete(jsonBody)
        );

        return this.<BulkRoleUnassignmentReport>callApiAndParseJson(request, BulkRoleUnassignmentReport.class);
    }


}
