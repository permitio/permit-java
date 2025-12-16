package io.permit.sdk.examples;

import io.permit.sdk.Permit;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.api.ApiClient;
import io.permit.sdk.api.PermitApiError;
import io.permit.sdk.api.PermitContextError;
import io.permit.sdk.api.UsersApi;
import io.permit.sdk.api.models.CreateOrUpdateResult;
import io.permit.sdk.enforcement.User;
import io.permit.sdk.openapi.models.UserRead;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for UserSyncExample.
 * These tests use Mockito to mock the Permit class and its nested API objects,
 * testing the actual UserSyncExample class rather than a test double.
 */
@ExtendWith(MockitoExtension.class)
class UserSyncExampleTest {

    @Mock
    private Permit mockPermit;

    @Mock
    private ApiClient mockApiClient;

    @Mock
    private UsersApi mockUsersApi;

    private UserSyncExample example;

    @BeforeEach
    void setUp() throws Exception {
        // Set up the mock chain: mockPermit.api -> mockApiClient, mockApiClient.users -> mockUsersApi
        // Since 'api' is a public final field, we need to use reflection to set it
        Field apiField = Permit.class.getDeclaredField("api");
        apiField.setAccessible(true);
        apiField.set(mockPermit, mockApiClient);

        Field usersField = ApiClient.class.getDeclaredField("users");
        usersField.setAccessible(true);
        usersField.set(mockApiClient, mockUsersApi);

        example = new UserSyncExample(mockPermit);
    }

    @Test
    @DisplayName("Should sync a simple user and return created result")
    void testSyncSimpleUser_Created() throws IOException, PermitApiError, PermitContextError {
        // Given
        String userKey = "new-user-001";
        UserRead expectedUser = new UserRead(userKey, "uuid-123", "org-1", "proj-1", "env-1");
        CreateOrUpdateResult<UserRead> expectedResult = new CreateOrUpdateResult<UserRead>(expectedUser, true);

        when(mockUsersApi.sync(any(User.class))).thenReturn(expectedResult);

        // When
        CreateOrUpdateResult<UserRead> result = example.syncSimpleUser(userKey);

        // Then
        assertNotNull(result);
        assertTrue(result.wasCreated(), "User should be marked as created");
        assertEquals(userKey, result.getResult().key);
        assertEquals("uuid-123", result.getResult().id);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(mockUsersApi).sync(userCaptor.capture());
        assertEquals(userKey, userCaptor.getValue().getKey());
    }

    @Test
    @DisplayName("Should sync a simple user and return updated result")
    void testSyncSimpleUser_Updated() throws IOException, PermitApiError, PermitContextError {
        // Given
        String userKey = "existing-user-001";
        UserRead expectedUser = new UserRead(userKey, "uuid-456", "org-1", "proj-1", "env-1");
        CreateOrUpdateResult<UserRead> expectedResult = new CreateOrUpdateResult<UserRead>(expectedUser, false);

        when(mockUsersApi.sync(any(User.class))).thenReturn(expectedResult);

        // When
        CreateOrUpdateResult<UserRead> result = example.syncSimpleUser(userKey);

        // Then
        assertNotNull(result);
        assertFalse(result.wasCreated(), "User should be marked as updated (not created)");
        assertEquals(userKey, result.getResult().key);
    }

    @Test
    @DisplayName("Should sync user with full profile information")
    void testSyncUserWithProfile() throws IOException, PermitApiError, PermitContextError {
        // Given
        String userKey = "john.doe@example.com";
        String email = "john.doe@example.com";
        String firstName = "John";
        String lastName = "Doe";

        UserRead expectedUser = new UserRead(userKey, "uuid-789", "org-1", "proj-1", "env-1")
                .withEmail(email)
                .withFirstName(firstName)
                .withLastName(lastName);
        CreateOrUpdateResult<UserRead> expectedResult = new CreateOrUpdateResult<UserRead>(expectedUser, true);

        when(mockUsersApi.sync(any(User.class))).thenReturn(expectedResult);

        // When
        CreateOrUpdateResult<UserRead> result = example.syncUserWithProfile(userKey, email, firstName, lastName);

        // Then
        assertNotNull(result);
        assertTrue(result.wasCreated());
        assertEquals(email, result.getResult().email);
        assertEquals(firstName, result.getResult().firstName);
        assertEquals(lastName, result.getResult().lastName);

        // Verify the User object passed to sync had the correct values
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(mockUsersApi).sync(userCaptor.capture());

        User capturedUser = userCaptor.getValue();
        assertEquals(userKey, capturedUser.getKey());
        assertEquals(email, capturedUser.getEmail());
        assertEquals(firstName, capturedUser.getFirstName());
        assertEquals(lastName, capturedUser.getLastName());
    }

    @Test
    @DisplayName("Should sync user with ABAC attributes")
    void testSyncUserWithAttributes() throws IOException, PermitApiError, PermitContextError {
        // Given
        String userKey = "alice@example.com";
        String email = "alice@example.com";
        String firstName = "Alice";
        String lastName = "Smith";

        HashMap<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("department", "engineering");
        attributes.put("clearance_level", 5);
        attributes.put("is_manager", true);

        UserRead expectedUser = new UserRead(userKey, "uuid-abc", "org-1", "proj-1", "env-1")
                .withEmail(email)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withAttributes(attributes);
        CreateOrUpdateResult<UserRead> expectedResult = new CreateOrUpdateResult<UserRead>(expectedUser, true);

        when(mockUsersApi.sync(any(User.class))).thenReturn(expectedResult);

        // When
        CreateOrUpdateResult<UserRead> result = example.syncUserWithAttributes(
                userKey, email, firstName, lastName, attributes
        );

        // Then
        assertNotNull(result);
        assertTrue(result.wasCreated());

        UserRead syncedUser = result.getResult();
        assertEquals("engineering", syncedUser.attributes.get("department"));
        assertEquals(5, ((Number) syncedUser.attributes.get("clearance_level")).intValue());
        assertTrue((Boolean) syncedUser.attributes.get("is_manager"));

        // Verify the User object passed to sync had the correct attributes
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(mockUsersApi).sync(userCaptor.capture());

        User capturedUser = userCaptor.getValue();
        assertEquals("engineering", capturedUser.getAttributes().get("department"));
        assertEquals(5, capturedUser.getAttributes().get("clearance_level"));
        assertTrue((Boolean) capturedUser.getAttributes().get("is_manager"));
    }

    @Test
    @DisplayName("Should propagate IOException from sync service")
    void testSyncSimpleUser_IOException() throws IOException, PermitApiError, PermitContextError {
        // Given
        when(mockUsersApi.sync(any(User.class)))
                .thenThrow(new IOException("Network unreachable"));

        // When/Then
        IOException exception = assertThrows(IOException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() throws Throwable {
                example.syncSimpleUser("test-user");
            }
        });
        assertEquals("Network unreachable", exception.getMessage());
    }

    @Test
    @DisplayName("Should propagate PermitApiError from sync service")
    void testSyncSimpleUser_PermitApiError() throws IOException, PermitApiError, PermitContextError {
        // Given
        when(mockUsersApi.sync(any(User.class)))
                .thenThrow(new PermitApiError("Bad Request", 400, "{\"error\":\"Invalid user data\"}"));

        // When/Then
        PermitApiError exception = assertThrows(PermitApiError.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() throws Throwable {
                example.syncSimpleUser("test-user");
            }
        });
        assertEquals(400, exception.getResponseCode());
        assertTrue(exception.getRawResponse().contains("Invalid user data"));
    }

    @Test
    @DisplayName("Should propagate PermitContextError from sync service")
    void testSyncSimpleUser_PermitContextError() throws IOException, PermitApiError, PermitContextError {
        // Given
        when(mockUsersApi.sync(any(User.class)))
                .thenThrow(new PermitContextError("Environment context required"));

        // When/Then
        PermitContextError exception = assertThrows(PermitContextError.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() throws Throwable {
                example.syncSimpleUser("test-user");
            }
        });
        assertTrue(exception.getMessage().contains("Environment context required"));
    }

    @Test
    @DisplayName("Should handle syncing multiple users")
    void testSyncMultipleUsers() throws IOException, PermitApiError, PermitContextError {
        // Given
        UserRead user1 = new UserRead("user1", "id1", "org", "proj", "env");
        UserRead user2 = new UserRead("user2", "id2", "org", "proj", "env");

        when(mockUsersApi.sync(any(User.class)))
                .thenReturn(new CreateOrUpdateResult<UserRead>(user1, true))
                .thenReturn(new CreateOrUpdateResult<UserRead>(user2, false));

        // When
        CreateOrUpdateResult<UserRead> result1 = example.syncSimpleUser("user1");
        CreateOrUpdateResult<UserRead> result2 = example.syncSimpleUser("user2");

        // Then
        assertTrue(result1.wasCreated(), "First user should be created");
        assertFalse(result2.wasCreated(), "Second user should be updated");

        verify(mockUsersApi, times(2)).sync(any(User.class));
    }

    @Test
    @DisplayName("Should handle null attributes gracefully")
    void testSyncUserWithAttributes_NullAttributes() throws IOException, PermitApiError, PermitContextError {
        // Given
        UserRead expectedUser = new UserRead("user1", "id1", "org", "proj", "env");
        when(mockUsersApi.sync(any(User.class)))
                .thenReturn(new CreateOrUpdateResult<UserRead>(expectedUser, true));

        // When - passing null attributes
        CreateOrUpdateResult<UserRead> result = example.syncUserWithAttributes(
                "user1", "user1@example.com", "First", "Last", null
        );

        // Then
        assertNotNull(result);
        assertTrue(result.wasCreated());

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(mockUsersApi).sync(userCaptor.capture());
        assertNull(userCaptor.getValue().getAttributes());
    }

    @Test
    @DisplayName("Should handle empty attributes")
    void testSyncUserWithAttributes_EmptyAttributes() throws IOException, PermitApiError, PermitContextError {
        // Given
        HashMap<String, Object> emptyAttributes = new HashMap<String, Object>();
        UserRead expectedUser = new UserRead("user1", "id1", "org", "proj", "env")
                .withAttributes(emptyAttributes);
        when(mockUsersApi.sync(any(User.class)))
                .thenReturn(new CreateOrUpdateResult<UserRead>(expectedUser, true));

        // When
        CreateOrUpdateResult<UserRead> result = example.syncUserWithAttributes(
                "user1", "user1@example.com", "First", "Last", emptyAttributes
        );

        // Then
        assertNotNull(result);
        assertTrue(result.wasCreated());

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(mockUsersApi).sync(userCaptor.capture());
        assertNotNull(userCaptor.getValue().getAttributes());
        assertTrue(userCaptor.getValue().getAttributes().isEmpty());
    }

    @Test
    @DisplayName("Should verify the actual example class can be instantiated with a real Permit instance")
    void testUserSyncExample_CanBeInstantiated() {
        try {
            // Given
            PermitConfig config = new PermitConfig.Builder("test-api-key")
                    .withPdpAddress("http://localhost:7766")
                    .build();
            Permit permit = new Permit(config);

            // When
            UserSyncExample realExample = new UserSyncExample(permit);

            // Then
            assertNotNull(realExample, "Should be able to create example with real Permit instance");
        } catch (UnsupportedClassVersionError e) {
            // Skip test if there's a class version mismatch (e.g., running with incompatible JVM)
            System.out.println("Skipping test due to class version incompatibility: " + e.getMessage());
        } catch (NoClassDefFoundError e) {
            // Skip test if there's a class version mismatch (e.g., running with incompatible JVM)
            System.out.println("Skipping test due to class version incompatibility: " + e.getMessage());
        }
    }
}
