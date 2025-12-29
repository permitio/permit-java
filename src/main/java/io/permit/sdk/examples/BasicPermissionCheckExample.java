package io.permit.sdk.examples;

import io.permit.sdk.Permit;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.api.PermitApiError;
import io.permit.sdk.enforcement.Resource;
import io.permit.sdk.enforcement.User;

import java.io.IOException;

/**
 * BasicPermissionCheckExample demonstrates how to initialize the Permit SDK
 * and perform a basic permission check.
 *
 * This example shows:
 * - How to create a PermitConfig with your API key
 * - How to initialize the Permit SDK
 * - How to create User and Resource objects
 * - How to perform a permission check
 */
public class BasicPermissionCheckExample {

    private final Permit permit;

    /**
     * Creates a new BasicPermissionCheckExample with the given Permit instance.
     * This constructor allows dependency injection for testing.
     *
     * @param permit The Permit SDK instance to use for permission checks
     */
    public BasicPermissionCheckExample(Permit permit) {
        this.permit = permit;
    }

    /**
     * Creates a new BasicPermissionCheckExample with the given API key.
     * Uses default PDP address (localhost:7766).
     *
     * @param apiKey Your Permit.io API key
     */
    public BasicPermissionCheckExample(String apiKey) {
        PermitConfig config = new PermitConfig.Builder(apiKey)
                .withPdpAddress("http://localhost:7766")
                .withDebugMode(false)
                .build();
        this.permit = new Permit(config);
    }

    /**
     * Checks if a user is permitted to perform an action on a resource.
     *
     * @param userKey    The unique identifier for the user
     * @param action     The action to check (e.g., "read", "write", "delete")
     * @param resourceType The type of resource (e.g., "document", "folder")
     * @return true if the user is permitted, false otherwise
     * @throws IOException if there's a network error communicating with the PDP
     * @throws PermitApiError if the PDP returns an error response
     */
    public boolean checkPermission(String userKey, String action, String resourceType)
            throws IOException, PermitApiError {
        // Create a User object from the user key
        User user = User.fromString(userKey);

        // Create a Resource object from the resource type
        Resource resource = Resource.fromString(resourceType);

        // Perform the permission check
        return permit.check(user, action, resource);
    }

    /**
     * Main method demonstrating basic usage of the Permit SDK.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Get API key from environment variable or use a placeholder
        String apiKey = System.getenv("PERMIT_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            System.err.println("Please set the PERMIT_API_KEY environment variable");
            System.exit(1);
        }

        BasicPermissionCheckExample example = new BasicPermissionCheckExample(apiKey);

        try {
            // Example: Check if user "john@example.com" can "read" a "document"
            String userKey = "john@example.com";
            String action = "read";
            String resourceType = "document";

            boolean permitted = example.checkPermission(userKey, action, resourceType);

            if (permitted) {
                System.out.println("User '" + userKey + "' is PERMITTED to " + action + " " + resourceType);
            } else {
                System.out.println("User '" + userKey + "' is DENIED to " + action + " " + resourceType);
            }
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
