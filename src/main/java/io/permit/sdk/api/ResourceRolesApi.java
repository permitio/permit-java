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

interface IResourceRolesApi {
    ResourceRoleRead[] list(String resourceKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    ResourceRoleRead[] list(String resourceKey, int page) throws IOException, PermitApiError, PermitContextError;
    ResourceRoleRead[] list(String resourceKey) throws IOException, PermitApiError, PermitContextError;
    ResourceRoleRead get(String resourceKey, String roleKey) throws IOException, PermitApiError, PermitContextError;
    ResourceRoleRead getByKey(String resourceKey, String roleKey) throws IOException, PermitApiError, PermitContextError;
    ResourceRoleRead getById(UUID resourceId, UUID roleId) throws IOException, PermitApiError, PermitContextError;
    ResourceRoleRead create(String resourceKey, ResourceRoleCreate roleData) throws IOException, PermitApiError, PermitContextError;
    ResourceRoleRead update(String resourceKey, String roleKey, ResourceRoleUpdate roleData) throws IOException, PermitApiError, PermitContextError;
    void delete(String resourceKey, String roleKey) throws IOException, PermitApiError, PermitContextError;
    ResourceRoleRead assignPermissions(String resourceKey, String roleKey, ArrayList<String> permissions) throws IOException, PermitApiError, PermitContextError;
    ResourceRoleRead removePermissions(String resourceKey, String roleKey, ArrayList<String> permissions) throws IOException, PermitApiError, PermitContextError;
    DerivedRoleRuleRead createRoleDerivation(String resourceKey, String roleKey, DerivedRoleRuleCreate derivationRule) throws IOException, PermitApiError, PermitContextError;
    void deleteRoleDerivation(String resourceKey, String roleKey, DerivedRoleRuleDelete derivationRule) throws IOException, PermitApiError, PermitContextError;
    DerivedRoleSettings updateRoleDerivationConditions(String resourceKey, String roleKey, DerivedRoleSettings conditions) throws IOException, PermitApiError, PermitContextError;
}

/**
 * The ResourceRolesApi class provides methods for interacting with roles using the Permit API.
 */
public class ResourceRolesApi extends BaseApi implements IResourceRolesApi {
    /**
     * Constructs a new ResourceRolesApi instance.
     *
     * @param client The OkHttpClient instance used for making HTTP requests.
     * @param config The PermitConfig instance containing the SDK configuration.
     */
    public ResourceRolesApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(ResourceRolesApi.class));
    }

    /**
     * Constructs the URL for the ResourceRoles API.
     *
     * @param resourceKey The key of the resource.
     * @param url The URL fragment.
     * @return The constructed URL string.
     */
    private String getResourceRolesUrl(String resourceKey, String url) {
        return buildUrl(
                String.format(
                        "/v2/schema/%s/%s/resources/%s/roles%s",
                        config.getContext().getProject(),
                        config.getContext().getEnvironment(),
                        resourceKey,
                        url
                )
        );
    }

    /**
     * Retrieves a paginated list of resource roles.
     *
     * @param page    The page number of the results.
     * @param perPage The number of roles per page.
     * @return An array of ResourceRoleRead objects representing the resource roles.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured SDK context {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceRoleRead[] list(String resourceKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getResourceRolesUrl(resourceKey, "");
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
            return (new Gson()).fromJson(responseString, ResourceRoleRead[].class);
        }
    }

    /**
     * Retrieves a paginated list of resource roles with the default number of roles per page.
     *
     * @param page The page number of the results.
     * @return An array of ResourceRoleRead objects representing the roles.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceRoleRead[] list(String resourceKey, int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(resourceKey, page, 100);
    }

    /**
     * Retrieves the first page of resource roles with the default number of roles per page.
     *
     * @return An array of ResourceRoleRead objects representing the roles.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceRoleRead[] list(String resourceKey) throws IOException, PermitApiError, PermitContextError {
        return this.list(resourceKey, 1);
    }

    /**
     * Retrieves a role with the specified role key.
     *
     * @param resourceKey The key of the resource the role belongs to.
     * @param roleKey The key of the role.
     * @return The ResourceRoleRead object representing the role.
     * @throws IOException           If an I/O error occurs during     * the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceRoleRead get(String resourceKey, String roleKey) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getResourceRolesUrl(resourceKey, String.format("/%s", roleKey));
        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .get()
        );

        return this.<ResourceRoleRead>callApiAndParseJson(request, ResourceRoleRead.class);
    }

    /**
     * Retrieves a role with the specified role key.
     * This is an alias for the {@link #get} method.
     *
     * @param resourceKey The key of the resource the role belongs to.
     * @param roleKey The key of the role.
     * @return The ResourceRoleRead object representing the role.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceRoleRead getByKey(String resourceKey, String roleKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(resourceKey, roleKey);
    }

    /**
     * Retrieves a role with the specified role ID.
     *
     * @param resourceId The ID of the resource the role belongs to.
     * @param roleId The ID of the role.
     * @return The ResourceRoleRead object representing the role.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceRoleRead getById(UUID resourceId, UUID roleId) throws IOException, PermitApiError, PermitContextError {
        return this.get(resourceId.toString(), roleId.toString());
    }

    /**
     * Creates a new role with the specified role data.
     *
     * @param resourceKey The key of the resource the role will belongs to.
     * @param roleData The ResourceRoleCreate object representing the role data.
     * @return The ResourceRoleRead object representing the created role.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceRoleRead create(String resourceKey, ResourceRoleCreate roleData) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getResourceRolesUrl(resourceKey, "");
        RequestBody jsonBody = getJsonRequestBody(roleData);

        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .post(jsonBody)
        );

        return this.<ResourceRoleRead>callApiAndParseJson(request, ResourceRoleRead.class);
    }

    /**
     * Updates a role with the specified role key.
     *
     * @param resourceKey The key of the resource the role belongs to.
     * @param roleKey  The key of the role to update.
     * @param roleData The ResourceRoleUpdate object representing the updated role data.
     * @return The ResourceRoleRead object representing the updated role.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ResourceRoleRead update(String resourceKey, String roleKey, ResourceRoleUpdate roleData) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getResourceRolesUrl(resourceKey, String.format("/%s", roleKey));
        RequestBody jsonBody = getJsonRequestBody(roleData);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .patch(jsonBody)
        );

        return this.<ResourceRoleRead>callApiAndParseJson(request, ResourceRoleRead.class);
    }

    /**
    * Deletes a role with the specified role key.
    *
    * @param resourceKey The key of the resource the role belongs to.
    * @param roleKey The key of the role to delete.
    * @throws IOException           If an I/O error occurs during the HTTP request.
    * @throws PermitApiError        If the Permit API returns a response with an error status code.
    * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
    */
    public void delete(String resourceKey, String roleKey) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getResourceRolesUrl(resourceKey, String.format("/%s", roleKey));
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
    * @param resourceKey The key of the resource the role belongs to.
    * @param roleKey      The key of the role to assign permissions to.
    * @param permissions  The list of permissions to assign.
    * @return The ResourceRoleRead object representing the role after the permissions are assigned.
    * @throws IOException           If an I/O error occurs during the HTTP request.
    * @throws PermitApiError        If the Permit API returns a response with an error status code.
    * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
    */
    public ResourceRoleRead assignPermissions(String resourceKey, String roleKey, ArrayList<String> permissions) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getResourceRolesUrl(resourceKey, String.format("/%s/permissions", roleKey));
        RequestBody jsonBody = getJsonRequestBody(new AddRolePermissions(permissions));

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .post(jsonBody)
        );

        return this.<ResourceRoleRead>callApiAndParseJson(request, ResourceRoleRead.class);
    }

    /**
    * Removes permissions from a role with the specified role key.
    *
    * @param resourceKey The key of the resource the role belongs to.
    * @param roleKey      The key of the role to remove permissions from.
    * @param permissions  The list of permissions to remove.
    * @return The ResourceRoleRead object representing the role after the permissions are removed.
    * @throws IOException           If an I/O error occurs during the HTTP request.
    * @throws PermitApiError        If the Permit API returns a response with an error status code.
    * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
    */
    public ResourceRoleRead removePermissions(String resourceKey, String roleKey, ArrayList<String> permissions) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getResourceRolesUrl(resourceKey, String.format("/%s/permissions", roleKey));
        RequestBody jsonBody = getJsonRequestBody(new RemoveRolePermissions(permissions));

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .delete(jsonBody)
        );

        return this.<ResourceRoleRead>callApiAndParseJson(request, ResourceRoleRead.class);
    }

    /**
     * Create a conditional derivation from another role.
     * The derivation states that users with some other role on a related object will implicitly also be granted this role.
     *
     * @param resourceKey    The key of the resource the role belongs to.
     * @param roleKey        The key of the role.
     * @param derivationRule A rule when to derive this role from another related role.
     * @return A {@link DerivedRoleRuleRead} object representing the newly created role derivation.
     * @throws IOException If an I/O error occurs during the HTTP request.
     * @throws PermitApiError If the Permit API returns a response with an error status code.
     * @throws PermitContextError If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public DerivedRoleRuleRead createRoleDerivation(String resourceKey, String roleKey, DerivedRoleRuleCreate derivationRule) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getResourceRolesUrl(resourceKey, String.format("/%s/implicit_grants", roleKey));
        RequestBody jsonBody = getJsonRequestBody(derivationRule);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .post(jsonBody)
        );

        return this.callApiAndParseJson(request, DerivedRoleRuleRead.class);
    }

    /**
     * Delete a role derivation.
     *
     * @param resourceKey    The key of the resource the role belongs to.
     * @param roleKey        The key of the role.
     * @param derivationRule The details of the derivation rule to delete.
     * @throws IOException If an I/O error occurs during the HTTP request.
     * @throws PermitApiError If the Permit API returns a response with an error status code.
     * @throws PermitContextError If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public void deleteRoleDerivation(String resourceKey, String roleKey, DerivedRoleRuleDelete derivationRule) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getResourceRolesUrl(resourceKey, String.format("/%s/implicit_grants", roleKey));
        RequestBody jsonBody = getJsonRequestBody(derivationRule);

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
     * Update the optional (ABAC) conditions when to derive this role from other roles.
     *
     * @param resourceKey The key of the resource the role belongs to.
     * @param roleKey     The key of the role.
     * @param conditions  The conditions object.
     * @return The updated DerivedRoleSettings object.
     * @throws IOException If an I/O error occurs during the HTTP request.
     * @throws PermitApiError If the Permit API returns a response with an error status code.
     * @throws PermitContextError If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public DerivedRoleSettings updateRoleDerivationConditions(String resourceKey, String roleKey, DerivedRoleSettings conditions) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getResourceRolesUrl(resourceKey, String.format("/%s/implicit_grants/conditions", roleKey));
        RequestBody jsonBody = getJsonRequestBody(conditions);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .post(jsonBody)
        );

        return this.callApiAndParseJson(request, DerivedRoleSettings.class);
    }
}
