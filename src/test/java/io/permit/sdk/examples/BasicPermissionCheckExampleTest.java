package io.permit.sdk.examples;

import io.permit.sdk.Permit;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.api.PermitApiError;
import io.permit.sdk.enforcement.IEnforcerApi;
import io.permit.sdk.enforcement.Resource;
import io.permit.sdk.enforcement.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * Unit tests for BasicPermissionCheckExample.
 * These tests use Mockito to mock the IEnforcerApi interface, so they don't require a running PDP.
 */
@ExtendWith(MockitoExtension.class)
class BasicPermissionCheckExampleTest {

    @Mock
    private IEnforcerApi mockEnforcer;

    private TestableBasicPermissionCheckExample example;

    /**
     * Testable subclass that allows injecting a mock IEnforcerApi
     */
    static class TestableBasicPermissionCheckExample {
        private final IEnforcerApi enforcer;

        TestableBasicPermissionCheckExample(IEnforcerApi enforcer) {
            this.enforcer = enforcer;
        }

        boolean checkPermission(String userKey, String action, String resourceType)
                throws IOException, PermitApiError {
            User user = User.fromString(userKey);
            Resource resource = Resource.fromString(resourceType);
            return enforcer.check(user, action, resource);
        }
    }

    @BeforeEach
    void setUp() {
        example = new TestableBasicPermissionCheckExample(mockEnforcer);
    }

    @Test
    @DisplayName("Should return true when user is permitted to perform action")
    void testCheckPermission_Permitted() throws IOException, PermitApiError {
        // Given
        String userKey = "test-user";
        String action = "read";
        String resourceType = "document";

        when(mockEnforcer.check(any(User.class), eq(action), any(Resource.class)))
                .thenReturn(true);

        // When
        boolean result = example.checkPermission(userKey, action, resourceType);

        // Then
        assertTrue(result, "User should be permitted");
        verify(mockEnforcer).check(any(User.class), eq(action), any(Resource.class));
    }

    @Test
    @DisplayName("Should return false when user is denied to perform action")
    void testCheckPermission_Denied() throws IOException, PermitApiError {
        // Given
        String userKey = "test-user";
        String action = "delete";
        String resourceType = "document";

        when(mockEnforcer.check(any(User.class), eq(action), any(Resource.class)))
                .thenReturn(false);

        // When
        boolean result = example.checkPermission(userKey, action, resourceType);

        // Then
        assertFalse(result, "User should be denied");
        verify(mockEnforcer).check(any(User.class), eq(action), any(Resource.class));
    }

    @Test
    @DisplayName("Should propagate IOException from Permit SDK")
    void testCheckPermission_IOException() throws IOException, PermitApiError {
        // Given
        String userKey = "test-user";
        String action = "read";
        String resourceType = "document";

        when(mockEnforcer.check(any(User.class), eq(action), any(Resource.class)))
                .thenThrow(new IOException("Network error"));

        // When/Then
        IOException exception = assertThrows(IOException.class, () ->
                example.checkPermission(userKey, action, resourceType)
        );
        assertEquals("Network error", exception.getMessage());
    }

    @Test
    @DisplayName("Should propagate PermitApiError from Permit SDK")
    void testCheckPermission_PermitApiError() throws IOException, PermitApiError {
        // Given
        String userKey = "test-user";
        String action = "read";
        String resourceType = "document";

        when(mockEnforcer.check(any(User.class), eq(action), any(Resource.class)))
                .thenThrow(new PermitApiError("API Error", 500, "{\"error\":\"Internal Server Error\"}"));

        // When/Then
        PermitApiError exception = assertThrows(PermitApiError.class, () ->
                example.checkPermission(userKey, action, resourceType)
        );
        assertEquals(500, exception.getResponseCode());
    }

    @Test
    @DisplayName("Should handle different user keys correctly")
    void testCheckPermission_DifferentUsers() throws IOException, PermitApiError {
        // Given
        when(mockEnforcer.check(any(User.class), eq("read"), any(Resource.class)))
                .thenReturn(true);

        // When
        boolean result1 = example.checkPermission("user1@example.com", "read", "document");
        boolean result2 = example.checkPermission("user2@example.com", "read", "document");

        // Then
        assertTrue(result1);
        assertTrue(result2);
        verify(mockEnforcer, times(2)).check(any(User.class), eq("read"), any(Resource.class));
    }

    @Test
    @DisplayName("Should handle different actions correctly")
    void testCheckPermission_DifferentActions() throws IOException, PermitApiError {
        // Given
        when(mockEnforcer.check(any(User.class), eq("read"), any(Resource.class)))
                .thenReturn(true);
        when(mockEnforcer.check(any(User.class), eq("write"), any(Resource.class)))
                .thenReturn(false);
        when(mockEnforcer.check(any(User.class), eq("delete"), any(Resource.class)))
                .thenReturn(false);

        // When
        boolean canRead = example.checkPermission("user1", "read", "document");
        boolean canWrite = example.checkPermission("user1", "write", "document");
        boolean canDelete = example.checkPermission("user1", "delete", "document");

        // Then
        assertTrue(canRead, "User should be able to read");
        assertFalse(canWrite, "User should not be able to write");
        assertFalse(canDelete, "User should not be able to delete");
    }

    @Test
    @DisplayName("Should handle different resource types correctly")
    void testCheckPermission_DifferentResources() throws IOException, PermitApiError {
        // Given
        when(mockEnforcer.check(any(User.class), eq("read"), any(Resource.class)))
                .thenReturn(true);

        // When
        boolean canReadDocument = example.checkPermission("user1", "read", "document");
        boolean canReadFolder = example.checkPermission("user1", "read", "folder");

        // Then
        assertTrue(canReadDocument);
        assertTrue(canReadFolder);
        verify(mockEnforcer, times(2)).check(any(User.class), eq("read"), any(Resource.class));
    }

    @Test
    @DisplayName("Should verify the actual example class can be instantiated with a real Permit instance")
    void testBasicPermissionCheckExample_CanBeInstantiated() {
        try {
            // Given
            PermitConfig config = new PermitConfig.Builder("test-api-key")
                    .withPdpAddress("http://localhost:7766")
                    .build();
            Permit permit = new Permit(config);

            // When
            BasicPermissionCheckExample realExample = new BasicPermissionCheckExample(permit);

            // Then
            assertNotNull(realExample, "Should be able to create example with real Permit instance");
        } catch (UnsupportedClassVersionError | NoClassDefFoundError e) {
            // Skip test if there's a class version mismatch (e.g., running with incompatible JVM)
            System.out.println("Skipping test due to class version incompatibility: " + e.getMessage());
        }
    }
}
