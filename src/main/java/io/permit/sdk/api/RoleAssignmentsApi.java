package io.permit.sdk.api;

import com.google.gson.Gson;
import io.permit.sdk.ApiContextLevel;
import io.permit.sdk.ApiKeyLevel;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.openapi.models.*;
import okhttp3.*;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

interface IRoleAssignmentsApi {
    RoleAssignmentRead[] list(String userKey, String tenantKey, String roleKey, String resourceInstanceKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    RoleAssignmentRead[] list(String userKey, String tenantKey, String roleKey, String resourceInstanceKey, int page) throws IOException, PermitApiError, PermitContextError;
    RoleAssignmentRead[] list(String userKey, String tenantKey, String roleKey, String resourceInstanceKey) throws IOException, PermitApiError, PermitContextError;
    RoleAssignmentRead[] list(String userKey, String tenantKey, String roleKey) throws IOException, PermitApiError, PermitContextError;
    RoleAssignmentRead[] list() throws IOException, PermitApiError, PermitContextError;
    RoleAssignmentRead assign(RoleAssignmentCreate assignment) throws IOException, PermitApiError, PermitContextError;
    void unassign(RoleAssignmentRemove unassignment) throws IOException, PermitApiError, PermitContextError;
    BulkRoleAssignmentReport bulkAssign(List<RoleAssignmentCreate> assignments) throws IOException, PermitApiError, PermitContextError;
    BulkRoleUnassignmentReport bulkUnassign(List<RoleAssignmentRemove> unassignments) throws IOException, PermitApiError, PermitContextError;
}

/**
 * The RoleAssignmentsApi class provides methods for managing role assignments in the Permit API.
 */
public class RoleAssignmentsApi extends BaseApi implements IRoleAssignmentsApi {
    /**
     * Constructs a new RoleAssignmentsApi instance with the specified OkHttpClient and PermitConfig.
     *
     * @param client The OkHttpClient instance to use for API requests.
     * @param config The PermitConfig instance that contains the SDK configuration.
     */
    public RoleAssignmentsApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(RoleAssignmentsApi.class));
    }

    /**
     * Constructs the URL for role assignments based on the project and environment in the PermitConfig.
     *
     * @param url The URL string to append to the base URL.
     * @return The complete URL for role assignments.
     */
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

    /**
     * Returns a paginated list of role assignments filtered by the optional user, role, tenant or resource instance.
     * To mark "all users" or an empty user filter - pass `null` instead of the user key (same for tenant and role).
     *
     * @param userKey   The key of the user (optional).
     * @param tenantKey The key of the tenant (optional).
     * @param roleKey   The key of the role (optional).
     * @param resourceInstanceKey   The key of the resource instance (optional).
     * @param page      The page number of the results.
     * @param perPage   The number of results per page.
     * @return An array of RoleAssignmentRead objects representing the role assignments.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RoleAssignmentRead[] list(String userKey, String tenantKey, String roleKey, String resourceInstanceKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getRoleAssignmentsUrl("");
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        if (userKey != null) {
            urlBuilder.addQueryParameter("user", userKey);
        }
        if (roleKey != null) {
            urlBuilder.addQueryParameter("role", roleKey);
        }
        if (tenantKey != null) {
            urlBuilder.addQueryParameter("tenant", tenantKey);
        }
        if (resourceInstanceKey != null) {
            urlBuilder.addQueryParameter("resource_instance", resourceInstanceKey);
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

    /**
     * Lists role assignments based on the specified user, role, tenant, resource instance and page parameters with the default number of items per page.
     *
     * @param userKey   The key of the user.
     * @param tenantKey The key of the tenant.
     * @param roleKey   The key of the role.
     * @param resourceInstanceKey   The key of the resource instance (optional).
     * @param page      The page number of the results.
     * @return An array of RoleAssignmentRead objects representing the role assignments.
     * @throws IOException           If an I/Oerror occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RoleAssignmentRead[] list(String userKey, String tenantKey, String roleKey, String resourceInstanceKey, int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(userKey, tenantKey, roleKey, resourceInstanceKey, page, 100);
    }

    /**
     * Lists role assignments based on the specified user, role, tenant and resource instance parameters with the default pagination parameters.
     *
     * @param userKey   The key of the user.
     * @param tenantKey The key of the tenant.
     * @param roleKey   The key of the role.
     * @param resourceInstanceKey   The key of the resource instance (optional).
     * @return An array of RoleAssignmentRead objects representing the role assignments.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RoleAssignmentRead[] list(String userKey, String tenantKey, String roleKey, String resourceInstanceKey) throws IOException, PermitApiError, PermitContextError {
        return this.list(userKey, tenantKey, roleKey, resourceInstanceKey, 1);
    }

    /**
     * Lists role assignments based on the specified user, role and tenant parameters with the default pagination parameters.
     *
     * @param userKey   The key of the user.
     * @param tenantKey The key of the tenant.
     * @param roleKey   The key of the role.
     * @return An array of RoleAssignmentRead objects representing the role assignments.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    @Override
    public RoleAssignmentRead[] list(String userKey, String tenantKey, String roleKey) throws IOException, PermitApiError, PermitContextError {
        return this.list(userKey, tenantKey, roleKey, null, 1);
    }

    /**
     * Lists all role assignments with the default pagination parameters.
     *
     * @return An array of RoleAssignmentRead objects representing the role assignments.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    @Override
    public RoleAssignmentRead[] list() throws IOException, PermitApiError, PermitContextError {
        return this.list(null, null, null, null, 1);
    }

    /**
     * Assigns a role using the provided role assignment data:
     * - the user that will be assigned the role
     * - the role to assign
     * - the tenant in which the assignment will be granted
     *
     * @param assignment The RoleAssignmentCreate object containing the assignment data.
     * @return The RoleAssignmentRead object representing the assigned role.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RoleAssignmentRead assign(RoleAssignmentCreate assignment) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getRoleAssignmentsUrl("");
        RequestBody jsonBody = getJsonRequestBody(assignment);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .post(jsonBody)
        );

        return this.<RoleAssignmentRead>callApiAndParseJson(request, RoleAssignmentRead.class);
    }

    /**
     * Removes a role assignment using the provided unassignment data:
     * - the user that the role will be unassigned for
     * - the role to unassign
     * - the tenant in which the assignment will be removed
     *
     * @param unassignment The RoleAssignmentRemove object containing the unassignment data.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public void unassign(RoleAssignmentRemove unassignment) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
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

    /**
     * Assigns multiple roles in bulk using the provided role assignments data.
     * Each role assignment is a tuple of (user, role, tenant).
     *
     * @param assignments The list of RoleAssignmentCreate objects containing the assignment data.
     * @return The BulkRoleAssignmentReport object representing the report of bulk role assignments.
     * @throws IOException           If an I/O error occurs during the HTTP request     
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public BulkRoleAssignmentReport bulkAssign(List<RoleAssignmentCreate> assignments) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getRoleAssignmentsUrl("/bulk");
        RequestBody jsonBody = getJsonRequestBody(assignments);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .post(jsonBody)
        );

        return this.<BulkRoleAssignmentReport>callApiAndParseJson(request, BulkRoleAssignmentReport.class);
    }

    /**
     * Removes multiple role assignments in bulk using the provided unassignment data.
     * Each role to unassign is a tuple of (user, role, tenant).
     *
     * @param unassignments The list of RoleAssignmentRemove objects containing the unassignment data.
     * @return The BulkRoleUnassignmentReport object representing the report of bulk role unassignments.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public BulkRoleUnassignmentReport bulkUnassign(List<RoleAssignmentRemove> unassignments) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
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
