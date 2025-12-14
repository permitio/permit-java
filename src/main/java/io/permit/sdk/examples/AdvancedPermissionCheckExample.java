package io.permit.sdk.examples;

import io.permit.sdk.Permit;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.api.PermitApiError;
import io.permit.sdk.enforcement.Resource;
import io.permit.sdk.enforcement.User;

import java.io.IOException;
import java.util.HashMap;

/**
 * AdvancedPermissionCheckExample demonstrates advanced permission checking
 * with user attributes, resource attributes, and explicit tenant specification.
 *
 * This example shows:
 * - How to create users with attributes (for ABAC - Attribute-Based Access Control)
 * - How to create resources with specific instances and tenants
 * - How to perform permission checks with full context
 */
public class AdvancedPermissionCheckExample {

    private final Permit permit;

    /**
     * Creates a new AdvancedPermissionCheckExample with the given Permit instance.
     * This constructor allows dependency injection for testing.
     *
     * @param permit The Permit SDK instance to use for permission checks
     */
    public AdvancedPermissionCheckExample(Permit permit) {
        this.permit = permit;
    }

    /**
     * Creates a new AdvancedPermissionCheckExample with the given API key.
     *
     * @param apiKey Your Permit.io API key
     * @param pdpAddress The address of your Policy Decision Point
     */
    public AdvancedPermissionCheckExample(String apiKey, String pdpAddress) {
        PermitConfig config = new PermitConfig.Builder(apiKey)
                .withPdpAddress(pdpAddress)
                .withDebugMode(false)
                .build();
        this.permit = new Permit(config);
    }

    /**
     * Checks permission for a user with attributes against a resource in a specific tenant.
     *
     * @param userKey        The unique identifier for the user
     * @param userEmail      The user's email address
     * @param userAttributes Additional attributes for the user (for ABAC)
     * @param action         The action to check (e.g., "read", "write", "delete")
     * @param resourceType   The type of resource (e.g., "document", "folder")
     * @param resourceKey    The specific resource instance key (can be null for type-level check)
     * @param tenant         The tenant context for the permission check
     * @return true if the user is permitted, false otherwise
     * @throws IOException if there's a network error communicating with the PDP
     * @throws PermitApiError if the PDP returns an error response
     */
    public boolean checkPermissionWithContext(
            String userKey,
            String userEmail,
            HashMap<String, Object> userAttributes,
            String action,
            String resourceType,
            String resourceKey,
            String tenant) throws IOException, PermitApiError {

        // Build a User with attributes using the Builder pattern
        User.Builder userBuilder = new User.Builder(userKey);
        if (userEmail != null) {
            userBuilder.withEmail(userEmail);
        }
        if (userAttributes != null) {
            userBuilder.withAttributes(userAttributes);
        }
        User user = userBuilder.build();

        // Build a Resource with tenant and optional key using the Builder pattern
        Resource.Builder resourceBuilder = new Resource.Builder(resourceType);
        if (resourceKey != null) {
            resourceBuilder.withKey(resourceKey);
        }
        if (tenant != null) {
            resourceBuilder.withTenant(tenant);
        }
        Resource resource = resourceBuilder.build();

        // Perform the permission check
        return permit.check(user, action, resource);
    }

    /**
     * Checks permission for a user with attributes against a resource with attributes.
     * This is useful for Attribute-Based Access Control (ABAC) scenarios.
     *
     * @param userKey            The unique identifier for the user
     * @param userAttributes     Attributes for the user (for ABAC)
     * @param action             The action to check
     * @param resourceType       The type of resource
     * @param resourceKey        The specific resource instance key
     * @param resourceAttributes Attributes for the resource (for ABAC)
     * @param tenant             The tenant context
     * @return true if the user is permitted, false otherwise
     * @throws IOException if there's a network error
     * @throws PermitApiError if the PDP returns an error
     */
    public boolean checkPermissionWithAttributes(
            String userKey,
            HashMap<String, Object> userAttributes,
            String action,
            String resourceType,
            String resourceKey,
            HashMap<String, Object> resourceAttributes,
            String tenant) throws IOException, PermitApiError {

        // Build a User with attributes
        User user = new User.Builder(userKey)
                .withAttributes(userAttributes)
                .build();

        // Build a Resource with attributes and tenant
        Resource.Builder resourceBuilder = new Resource.Builder(resourceType);
        if (resourceKey != null) {
            resourceBuilder.withKey(resourceKey);
        }
        if (resourceAttributes != null) {
            resourceBuilder.withAttributes(resourceAttributes);
        }
        if (tenant != null) {
            resourceBuilder.withTenant(tenant);
        }
        Resource resource = resourceBuilder.build();

        // Perform the permission check
        return permit.check(user, action, resource);
    }

    /**
     * Main method demonstrating advanced usage of the Permit SDK.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Get API key from environment variable
        String apiKey = System.getenv("PERMIT_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            System.err.println("Please set the PERMIT_API_KEY environment variable");
            System.exit(1);
        }

        String pdpAddress = System.getenv().getOrDefault("PDP_URL", "http://localhost:7766");
        AdvancedPermissionCheckExample example = new AdvancedPermissionCheckExample(apiKey, pdpAddress);

        try {
            // Example 1: Check permission with user attributes and tenant
            String userKey = "john@acme.com";
            String userEmail = "john@acme.com";

            // User attributes for ABAC (e.g., department, clearance level)
            HashMap<String, Object> userAttributes = new HashMap<String, Object>();
            userAttributes.put("department", "engineering");
            userAttributes.put("clearance_level", 3);

            String action = "edit";
            String resourceType = "document";
            String resourceKey = "doc-123";
            String tenant = "acme-corp";

            boolean permitted = example.checkPermissionWithContext(
                    userKey, userEmail, userAttributes,
                    action, resourceType, resourceKey, tenant
            );

            System.out.println("Example 1 - Permission check with tenant:");
            System.out.println("  User: " + userKey);
            System.out.println("  Action: " + action);
            System.out.println("  Resource: " + resourceType + ":" + resourceKey);
            System.out.println("  Tenant: " + tenant);
            System.out.println("  Result: " + (permitted ? "PERMITTED" : "DENIED"));
            System.out.println();

            // Example 2: Check permission with both user and resource attributes
            HashMap<String, Object> resourceAttributes = new HashMap<String, Object>();
            resourceAttributes.put("classification", "confidential");
            resourceAttributes.put("owner", "engineering");

            boolean permitted2 = example.checkPermissionWithAttributes(
                    userKey, userAttributes,
                    "delete", resourceType, resourceKey,
                    resourceAttributes, tenant
            );

            System.out.println("Example 2 - Permission check with ABAC attributes:");
            System.out.println("  User: " + userKey + " (department=engineering, clearance=3)");
            System.out.println("  Action: delete");
            System.out.println("  Resource: " + resourceType + ":" + resourceKey + " (classification=confidential)");
            System.out.println("  Tenant: " + tenant);
            System.out.println("  Result: " + (permitted2 ? "PERMITTED" : "DENIED"));

        } catch (IOException e) {
            System.err.println("Network error while checking permission: " + e.getMessage());
            e.printStackTrace();
        } catch (PermitApiError e) {
            System.err.println("Permit API error: " + e.getMessage());
            System.err.println("Response code: " + e.getResponseCode());
            e.printStackTrace();
        }
    }
}
