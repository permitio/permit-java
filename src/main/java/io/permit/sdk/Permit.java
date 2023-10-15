package io.permit.sdk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.permit.sdk.api.ApiClient;
import io.permit.sdk.api.ElementsApi;
import io.permit.sdk.enforcement.*;
import io.permit.sdk.util.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * The {@code Permit} class represents the main entry point for interacting with the Permit.io SDK.
 * 
 * Example usage:
 *
 * ```java
 * PermitConfig config = new PermitConfig.Builder("[YOUR_API_KEY]")
 *     // in production, you might need to change this url to fit your deployment
 *     .withPdpAddress("http://localhost:7766")
 *     // optionally, if you wish to get more debug messages to your log, set this to true
 *     .withDebugMode(false)
 *     .build();
 *
 * Permit permit = new Permit(config);
 *
 * // `user123` is the user key
 * User user = User.fromString("user123");
 * // `document` is the resource key, where resource is meant as a resource type and not a specific instance
 * Resource resource = Resource.fromString("document");
 *
 * boolean permitted = permit.check(user, "read", resource);
 *
 * if (permitted) {
 *     System.out.println("User is authorized to read a document.");
 * } else {
 *     System.out.println("User is not authorized to read a document.");
 * }
 * ```
 */
public class Permit implements IEnforcerApi {
    final static Logger logger = LoggerFactory.getLogger(Permit.class);
    private final Enforcer enforcer;

    /**
     * Access the SDK configuration using this property.
     * Once the SDK is initialized, the configuration is read-only.
     * 
     * Usage example:
     * 
     * Permit permit = new Permit(config);
     * String url = permit.config.getPdpAddress();
     */
    public final PermitConfig config;

    /**
     * Access the Permit REST API using this property.
     * 
     * Usage example:
     * 
     * Permit permit = new Permit(config);
     * permit.api.roles.create(...);
     */
    public final ApiClient api;

    /**
     * Access the Permit Elements API using this property.
     * 
     * Usage example:
     * 
     * Permit permit = new Permit(config);
     * permit.elements.loginAs(user, tenant);
     */
    public final ElementsApi elements;

    /**
     * Constructs a new instance of the {@code Permit} class with the specified configuration.
     *
     * @param config The configuration for the Permit SDK.
     */
    public Permit(PermitConfig config) {
        this.config = config;
        this.api = new ApiClient(this.config);
        this.elements = api.elements;
        this.enforcer = new Enforcer(this.config);

        if (this.config.isDebugMode()) {
            Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
            logger.info(String.format("Permit.io SDK initialized with config:\n%s", gson.toJson(this.config)));
        }
    }

    /**
     * Checks if a user is authorized to perform an action on a resource within the specified context.
     *
     * @param user     The user object representing the user.
     * @param action   The action to be performed on the resource.
     * @param resource The resource object representing the resource.
     * @param context  The context object representing the context in which the action is performed.
     * @return {@code true} if the user is authorized, {@code false} otherwise.
     * @throws IOException if an error occurs while checking the authorization.
     */
    @Override
    public boolean check(User user, String action, Resource resource, Context context) throws IOException {
        return this.enforcer.check(user, action, resource, context);
    }

    /**
     * Checks if a user is authorized to perform an action on a resource without specifying a context.
     * An empty context will be used.
     *
     * @param user     The user object representing the user.
     * @param action   The action to be performed on the resource.
     * @param resource The resource object representing the resource.
     * @return {@code true} if the user is authorized, {@code false} otherwise.
     * @throws IOException if an error occurs while checking the authorization.
     */
    @Override
    public boolean check(User user, String action, Resource resource) throws IOException {
        return this.enforcer.check(user, action, resource);
    }

    @Override
    public boolean checkUrl(User user, String httpMethod, String url, String tenant, Context context) throws IOException {
        return this.enforcer.checkUrl(user, httpMethod, url, tenant, context);
    }

    @Override
    public boolean checkUrl(User user, String httpMethod, String url, String tenant) throws IOException {
        return this.enforcer.checkUrl(user, httpMethod, url, tenant);
    }

    @Override
    public boolean[] bulkCheck(List<CheckQuery> checks) throws IOException {
        return this.enforcer.bulkCheck(checks);
    }

    @Override
    public List<TenantDetails> checkInAllTenants(User user, String action, Resource resource, Context context) throws IOException {
        return this.enforcer.checkInAllTenants(user, action, resource, context);
    }

    @Override
    public List<TenantDetails> checkInAllTenants(User user, String action, Resource resource) throws IOException {
        return this.enforcer.checkInAllTenants(user, action, resource);
    }

    @Override
    public UserPermissions getUserPermissions(GetUserPermissionsQuery input) throws IOException {
        return this.enforcer.getUserPermissions(input);
    }
}
