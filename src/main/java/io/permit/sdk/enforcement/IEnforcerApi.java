package io.permit.sdk.enforcement;

import io.permit.sdk.api.PermitApiError;
import io.permit.sdk.util.Context;

import java.io.IOException;
import java.util.List;

public interface IEnforcerApi {
    /**
     * Checks if a `user` is authorized to perform an `action` on a `resource` within the specified context.
     *
     * @param user The user object representing the user.
     * @param action The action to be performed on the resource.
     * @param resource The resource object representing the resource.
     * @param context The context object representing the context in which the action is performed.
     * @return `true` if the user is authorized, `false` otherwise.
     * @throws PermitApiError if an error occurs while sending the authorization request to the PDP.
     * @throws IOException if could not read the content of the returned http response.
     */
    boolean check(User user, String action, Resource resource, Context context) throws IOException, PermitApiError;

    /**
     * Checks if a `user` is authorized to perform an `action` on a `resource` without additional context
     *
     * @param user The user object representing the user.
     * @param action The action to be performed on the resource.
     * @param resource The resource object representing the resource.
     * @return `true` if the user is authorized, `false` otherwise.
     * @throws PermitApiError if an error occurs while sending the authorization request to the PDP.
     * @throws IOException if could not read the content of the returned http response.
     */
    boolean check(User user, String action, Resource resource) throws IOException, PermitApiError;

    /**
     * Performs a permission check on a (resource, action) pair that are represented by an HTTP endpoint.
     * The resource and actions are extracted from the HTTP url and method.
     * A tenant must be provided to determine the scope of the permission check.
     *
     * @param user The user object representing the user.
     * @param httpMethod the HTTP method the user is calling, typically determines the action.
     * @param url the url the user is calling, typically determines the resource.
     * @param tenant the tenant determines the scope of the permission check.
     * @return `true` if the user is authorized, `false` otherwise.
     * @throws PermitApiError if an error occurs while sending the authorization request to the PDP.
     * @throws IOException if could not read the content of the returned http response.
     */
    boolean checkUrl(User user, String httpMethod, String url, String tenant) throws IOException, PermitApiError;

    /**
     * Performs a permission check on a (resource, action) pair that are represented by an HTTP endpoint.
     * The resource and actions are extracted from the HTTP url and method.
     * A tenant must be provided to determine the scope of the permission check.
     * Receives additional context
     *
     * @param user The user object representing the user.
     * @param httpMethod the HTTP method the user is calling, typically determines the action.
     * @param url the url the user is calling, typically determines the resource.
     * @param tenant the tenant determines the scope of the permission check.
     * @param context The context object representing the context in which the action is performed.
     * @return `true` if the user is authorized, `false` otherwise.
     * @throws PermitApiError if an error occurs while sending the authorization request to the PDP.
     * @throws IOException if could not read the content of the returned http response.
     */
    boolean checkUrl(User user, String httpMethod, String url, String tenant, Context context) throws IOException, PermitApiError;

    /**
     * Runs multiple permission checks in a single HTTP Request (Bulk Check).
     *
     * @param checks The check requests, each containing user, action, resource and context.
     * @return array containing `true` if the user is authorized, `false` otherwise for each check request.
     * @throws PermitApiError if an error occurs while sending the authorization request to the PDP.
     * @throws IOException if could not read the content of the returned http response.
     */
    boolean[] bulkCheck(List<CheckQuery> checks) throws IOException, PermitApiError;

    /**
     * Checks if a `user` is authorized to perform an `action` on a `resource` (with `context`) across all tenants.
     * Returns only tenants in which the action is allowed for this user, including the tenant attributes.
     *
     * @param user     The user object representing the user.
     * @param action   The action to be performed on the resource.
     * @param resource The resource object representing the resource.
     * @param context  The context object representing the context in which the action is performed.
     * @return List of TenantDetails objects, representing the tenants in which the action is allowed.
     * @throws PermitApiError if an error occurs while sending the authorization request to the PDP.
     * @throws IOException if could not read the content of the returned http response.
     */
    List<TenantDetails> checkInAllTenants(User user, String action, Resource resource, Context context) throws IOException, PermitApiError;

    /**
     * Checks if a `user` is authorized to perform an `action` on a `resource` across all tenants,
     * without additional context. Returns only tenants in which the action is allowed for this user,
     * including the tenant attributes.
     *
     * @param user The user object representing the user.
     * @param action The action to be performed on the resource.
     * @param resource The resource object representing the resource.
     * @return List of TenantDetails objects, representing the tenants in which the action is allowed.
     * @throws PermitApiError if an error occurs while sending the authorization request to the PDP.
     * @throws IOException if could not read the content of the returned http response.
     */
    List<TenantDetails> checkInAllTenants(User user, String action, Resource resource) throws IOException, PermitApiError;

    /**
     * list all the permissions granted to a user (by default in all tenants and for all objects).
     *
     * @param input input to get user permissions api
     * @return A UserPermissions object, that contains all the permissions granted to the user.
     * @throws PermitApiError if an error occurs while sending the authorization request to the PDP.
     * @throws IOException if could not read the content of the returned http response.
     */
    UserPermissions getUserPermissions(GetUserPermissionsQuery input) throws IOException, PermitApiError;

    /**
     * list all the tenants the user is associated with.
     *
     * @param input input to get user tenants api
     * @return List of TenantDetails objects, representing the tenants in which the action is allowed.
     * @throws PermitApiError if an error occurs while sending the authorization request to the PDP.
     * @throws IOException if could not read the content of the returned http response.
     */
    List<TenantDetails> getUserTenants(GetUserTenantsQuery input) throws IOException, PermitApiError;
}
