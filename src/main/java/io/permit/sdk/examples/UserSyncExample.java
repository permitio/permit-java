package io.permit.sdk.examples;

import io.permit.sdk.Permit;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.api.PermitApiError;
import io.permit.sdk.api.PermitContextError;
import io.permit.sdk.api.models.CreateOrUpdateResult;
import io.permit.sdk.enforcement.User;
import io.permit.sdk.openapi.models.UserRead;

import java.io.IOException;
import java.util.HashMap;

/**
 * UserSyncExample demonstrates how to synchronize users with the Permit API.
 *
 * User synchronization is the process of creating or updating users in Permit.
 * This is typically done when:
 * - A new user signs up in your application
 * - User information changes (email, name, attributes)
 * - You want to pre-populate users before they first access your application
 *
 * This example shows:
 * - How to sync a simple user by key
 * - How to sync a user with full profile information
 * - How to sync a user with custom attributes for ABAC
 * - How to handle the sync result (created vs updated)
 */
public class UserSyncExample {

    private final Permit permit;

    /**
     * Creates a new UserSyncExample with the given Permit instance.
     * This constructor allows dependency injection for testing.
     *
     * @param permit The Permit SDK instance to use for user synchronization
     */
    public UserSyncExample(Permit permit) {
        this.permit = permit;
    }

    /**
     * Creates a new UserSyncExample with the given API key.
     *
     * @param apiKey Your Permit.io API key
     * @param pdpAddress The address of your Policy Decision Point
     */
    public UserSyncExample(String apiKey, String pdpAddress) {
        PermitConfig config = new PermitConfig.Builder(apiKey)
                .withPdpAddress(pdpAddress)
                .withDebugMode(false)
                .build();
        this.permit = new Permit(config);
    }

    /**
     * Syncs a simple user identified only by their key.
     * If the user already exists, this will update them (no-op if no changes).
     * If the user doesn't exist, this will create them.
     *
     * @param userKey The unique identifier for the user
     * @return The result containing the user data and whether it was created
     * @throws IOException if there's a network error
     * @throws PermitApiError if the Permit API returns an error
     * @throws PermitContextError if the API context is not properly configured
     */
    public CreateOrUpdateResult<UserRead> syncSimpleUser(String userKey)
            throws IOException, PermitApiError, PermitContextError {
        User user = User.fromString(userKey);
        return permit.api.users.sync(user);
    }

    /**
     * Syncs a user with full profile information.
     *
     * @param userKey   The unique identifier for the user
     * @param email     The user's email address
     * @param firstName The user's first name
     * @param lastName  The user's last name
     * @return The result containing the user data and whether it was created
     * @throws IOException if there's a network error
     * @throws PermitApiError if the Permit API returns an error
     * @throws PermitContextError if the API context is not properly configured
     */
    public CreateOrUpdateResult<UserRead> syncUserWithProfile(
            String userKey,
            String email,
            String firstName,
            String lastName) throws IOException, PermitApiError, PermitContextError {

        User user = new User.Builder(userKey)
                .withEmail(email)
                .withFirstName(firstName)
                .withLastName(lastName)
                .build();

        return permit.api.users.sync(user);
    }

    /**
     * Syncs a user with custom attributes for Attribute-Based Access Control (ABAC).
     * Attributes can be used in permission policies to make fine-grained access decisions.
     *
     * @param userKey    The unique identifier for the user
     * @param email      The user's email address
     * @param firstName  The user's first name
     * @param lastName   The user's last name
     * @param attributes Custom attributes for ABAC (e.g., department, role, clearance)
     * @return The result containing the user data and whether it was created
     * @throws IOException if there's a network error
     * @throws PermitApiError if the Permit API returns an error
     * @throws PermitContextError if the API context is not properly configured
     */
    public CreateOrUpdateResult<UserRead> syncUserWithAttributes(
            String userKey,
            String email,
            String firstName,
            String lastName,
            HashMap<String, Object> attributes) throws IOException, PermitApiError, PermitContextError {

        User user = new User.Builder(userKey)
                .withEmail(email)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withAttributes(attributes)
                .build();

        return permit.api.users.sync(user);
    }

    /**
     * Main method demonstrating user synchronization with the Permit SDK.
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
        UserSyncExample example = new UserSyncExample(apiKey, pdpAddress);

        try {
            // Example 1: Sync a simple user
            System.out.println("Example 1: Syncing a simple user...");
            CreateOrUpdateResult<UserRead> result1 = example.syncSimpleUser("user-001");

            UserRead user1 = result1.getResult();
            System.out.println("  User key: " + user1.key);
            System.out.println("  User ID: " + user1.id);
            System.out.println("  Was created: " + result1.wasCreated());
            System.out.println();

            // Example 2: Sync a user with profile information
            System.out.println("Example 2: Syncing a user with profile...");
            CreateOrUpdateResult<UserRead> result2 = example.syncUserWithProfile(
                    "john.doe@example.com",
                    "john.doe@example.com",
                    "John",
                    "Doe"
            );

            UserRead user2 = result2.getResult();
            System.out.println("  User key: " + user2.key);
            System.out.println("  Email: " + user2.email);
            System.out.println("  First name: " + user2.firstName);
            System.out.println("  Last name: " + user2.lastName);
            System.out.println("  Was created: " + result2.wasCreated());
            System.out.println();

            // Example 3: Sync a user with ABAC attributes
            System.out.println("Example 3: Syncing a user with ABAC attributes...");
            HashMap<String, Object> attributes = new HashMap<String, Object>();
            attributes.put("department", "engineering");
            attributes.put("clearance_level", 5);
            attributes.put("is_manager", true);
            attributes.put("teams", new String[]{"backend", "devops"});

            CreateOrUpdateResult<UserRead> result3 = example.syncUserWithAttributes(
                    "alice.smith@example.com",
                    "alice.smith@example.com",
                    "Alice",
                    "Smith",
                    attributes
            );

            UserRead user3 = result3.getResult();
            System.out.println("  User key: " + user3.key);
            System.out.println("  Email: " + user3.email);
            System.out.println("  First name: " + user3.firstName);
            System.out.println("  Last name: " + user3.lastName);
            System.out.println("  Attributes: " + user3.attributes);
            System.out.println("  Was created: " + result3.wasCreated());

        } catch (IOException e) {
            System.err.println("Network error while syncing user: " + e.getMessage());
            e.printStackTrace();
        } catch (PermitApiError e) {
            System.err.println("Permit API error: " + e.getMessage());
            System.err.println("Response code: " + e.getResponseCode());
            System.err.println("Raw response: " + e.getRawResponse());
            e.printStackTrace();
        } catch (PermitContextError e) {
            System.err.println("Permit context error: " + e.getMessage());
            System.err.println("Make sure you are using an Environment-level API key");
            e.printStackTrace();
        }
    }
}
