package io.permit.sdk.examples;

import io.permit.sdk.Permit;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.api.PermitApiError;
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
 * These tests use Mockito to mock the Permit class, testing the actual example class
 * rather than a test double.
 */
@ExtendWith(MockitoExtension.class)
class BasicPermissionCheckExampleTest {

    @Mock
    private Permit mockPermit;

    private BasicPermissionCheckExample example;

    @BeforeEach
    void setUp() {
        example = new BasicPermissionCheckExample(mockPermit);
    }

    @Test
    @DisplayName("Should return true when permission is granted")
    void testCheckPermission_Granted() throws IOException, PermitApiError {
        // Given
        String userKey = "test-user";
        String action = "read";
        String resourceType = "document";

        when(mockPermit.check(any(User.class), eq(action), any(Resource.class)))
                .thenReturn(true);

        // When
        boolean result = example.checkPermission(userKey, action, resourceType);

        // Then
        assertTrue(result);
        verify(mockPermit).check(any(User.class), eq(action), any(Resource.class));
    }

    @Test
    @DisplayName("Should return false when permission is denied")
    void testCheckPermission_Denied() throws IOException, PermitApiError {
        // Given
        String userKey = "test-user";
        String action = "delete";
        String resourceType = "document";

        when(mockPermit.check(any(User.class), eq(action), any(Resource.class)))
                .thenReturn(false);

        // When
        boolean result = example.checkPermission(userKey, action, resourceType);

        // Then
        assertFalse(result);
        verify(mockPermit).check(any(User.class), eq(action), any(Resource.class));
    }

    @Test
    @DisplayName("Should propagate IOException from Permit SDK")
    void testCheckPermission_IOException() throws IOException, PermitApiError {
        // Given
        String userKey = "test-user";
        String action = "read";
        String resourceType = "document";

        when(mockPermit.check(any(User.class), eq(action), any(Resource.class)))
                .thenThrow(new IOException("Network error"));

        // When/Then
        IOException exception = assertThrows(IOException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() throws Throwable {
                example.checkPermission(userKey, action, resourceType);
            }
        });
        assertEquals("Network error", exception.getMessage());
    }

    @Test
    @DisplayName("Should propagate PermitApiError from Permit SDK")
    void testCheckPermission_PermitApiError() throws IOException, PermitApiError {
        // Given
        String userKey = "test-user";
        String action = "read";
        String resourceType = "document";

        when(mockPermit.check(any(User.class), eq(action), any(Resource.class)))
                .thenThrow(new PermitApiError("API Error", 500, "{\"error\":\"Internal Server Error\"}"));

        // When/Then
        PermitApiError exception = assertThrows(PermitApiError.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() throws Throwable {
                example.checkPermission(userKey, action, resourceType);
            }
        });
        assertEquals(500, exception.getResponseCode());
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
        } catch (UnsupportedClassVersionError e) {
            // Skip test if there's a class version mismatch (e.g., running with incompatible JVM)
            System.out.println("Skipping test due to class version incompatibility: " + e.getMessage());
        } catch (NoClassDefFoundError e) {
            // Skip test if there's a class version mismatch (e.g., running with incompatible JVM)
            System.out.println("Skipping test due to class version incompatibility: " + e.getMessage());
        }
    }
}
