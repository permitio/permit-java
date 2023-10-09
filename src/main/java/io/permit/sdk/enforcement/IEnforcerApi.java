package io.permit.sdk.enforcement;

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
     * @throws IOException if an error occurs while sending the authorization request to the PDP.
     */
    boolean check(User user, String action, Resource resource, Context context) throws IOException;

    /**
     * Checks if a `user` is authorized to perform an `action` on a `resource` without additional context
     *
     * @param user The user object representing the user.
     * @param action The action to be performed on the resource.
     * @param resource The resource object representing the resource.
     * @return `true` if the user is authorized, `false` otherwise.
     * @throws IOException if an error occurs while sending the authorization request to the PDP.
     */
    boolean check(User user, String action, Resource resource) throws IOException;

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
     * @throws IOException if an error occurs while sending the authorization request to the PDP.
     */
    boolean checkUrl(User user, String httpMethod, String url, String tenant) throws IOException;

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
     * @throws IOException if an error occurs while sending the authorization request to the PDP.
     */
    boolean checkUrl(User user, String httpMethod, String url, String tenant, Context context) throws IOException;

    /**
     * Runs multiple permission checks in a single HTTP Request (Bulk Check).
     *
     * @param checks The check requests, each containing user, action, resource and context.
     * @return array containing `true` if the user is authorized, `false` otherwise for each check request.
     * @throws IOException if an error occurs while sending the authorization request to the PDP.
     */
    boolean[] bulkCheck(List<CheckQuery> checks) throws IOException;
}
