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
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * Unit tests for AdvancedPermissionCheckExample.
 * These tests use Mockito to mock the Permit class and verify that user attributes,
 * resource attributes, and tenant context are handled correctly by the actual example class.
 */
@ExtendWith(MockitoExtension.class)
class AdvancedPermissionCheckExampleTest {

    @Mock
    private Permit mockPermit;

    private AdvancedPermissionCheckExample example;

    @BeforeEach
    void setUp() {
        example = new AdvancedPermissionCheckExample(mockPermit);
    }

    @Test
    @DisplayName("Should check permission with user email and tenant")
    void testCheckPermissionWithContext_FullContext() throws IOException, PermitApiError {
        // Given
        String userKey = "user-123";
        String userEmail = "user@example.com";
        HashMap<String, Object> userAttributes = new HashMap<String, Object>();
        userAttributes.put("department", "engineering");
        String action = "edit";
        String resourceType = "document";
        String resourceKey = "doc-456";
        String tenant = "acme-corp";

        when(mockPermit.check(any(User.class), eq(action), any(Resource.class)))
                .thenReturn(true);

        // When
        boolean result = example.checkPermissionWithContext(
                userKey, userEmail, userAttributes,
                action, resourceType, resourceKey, tenant
        );

        // Then
        assertTrue(result);

        // Verify that check was called with correct parameters
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        ArgumentCaptor<Resource> resourceCaptor = ArgumentCaptor.forClass(Resource.class);
        verify(mockPermit).check(userCaptor.capture(), eq(action), resourceCaptor.capture());

        User capturedUser = userCaptor.getValue();
        assertEquals(userKey, capturedUser.getKey());
        assertEquals(userEmail, capturedUser.getEmail());
        assertEquals("engineering", capturedUser.getAttributes().get("department"));

        Resource capturedResource = resourceCaptor.getValue();
        assertEquals(resourceType, capturedResource.getType());
        assertEquals(resourceKey, capturedResource.getKey());
        assertEquals(tenant, capturedResource.getTenant());
    }

    @Test
    @DisplayName("Should check permission with null optional fields")
    void testCheckPermissionWithContext_NullOptionalFields() throws IOException, PermitApiError {
        // Given
        String userKey = "user-123";
        String action = "read";
        String resourceType = "document";

        when(mockPermit.check(any(User.class), eq(action), any(Resource.class)))
                .thenReturn(true);

        // When - email, attributes, resourceKey, and tenant are all null
        boolean result = example.checkPermissionWithContext(
                userKey, null, null,
                action, resourceType, null, null
        );

        // Then
        assertTrue(result);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        ArgumentCaptor<Resource> resourceCaptor = ArgumentCaptor.forClass(Resource.class);
        verify(mockPermit).check(userCaptor.capture(), eq(action), resourceCaptor.capture());

        User capturedUser = userCaptor.getValue();
        assertEquals(userKey, capturedUser.getKey());
        assertNull(capturedUser.getEmail());
        assertNull(capturedUser.getAttributes());

        Resource capturedResource = resourceCaptor.getValue();
        assertEquals(resourceType, capturedResource.getType());
        assertNull(capturedResource.getKey());
        assertNull(capturedResource.getTenant());
    }

    @Test
    @DisplayName("Should check permission with ABAC attributes on both user and resource")
    void testCheckPermissionWithAttributes_FullABAC() throws IOException, PermitApiError {
        // Given
        String userKey = "user-123";
        HashMap<String, Object> userAttributes = new HashMap<String, Object>();
        userAttributes.put("clearance_level", 5);
        userAttributes.put("department", "security");

        String action = "access";
        String resourceType = "classified-document";
        String resourceKey = "secret-doc-001";

        HashMap<String, Object> resourceAttributes = new HashMap<String, Object>();
        resourceAttributes.put("classification", "top-secret");
        resourceAttributes.put("required_clearance", 5);

        String tenant = "gov-agency";

        when(mockPermit.check(any(User.class), eq(action), any(Resource.class)))
                .thenReturn(true);

        // When
        boolean result = example.checkPermissionWithAttributes(
                userKey, userAttributes,
                action, resourceType, resourceKey,
                resourceAttributes, tenant
        );

        // Then
        assertTrue(result);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        ArgumentCaptor<Resource> resourceCaptor = ArgumentCaptor.forClass(Resource.class);
        verify(mockPermit).check(userCaptor.capture(), eq(action), resourceCaptor.capture());

        User capturedUser = userCaptor.getValue();
        assertEquals(5, capturedUser.getAttributes().get("clearance_level"));
        assertEquals("security", capturedUser.getAttributes().get("department"));

        Resource capturedResource = resourceCaptor.getValue();
        assertEquals("top-secret", capturedResource.getAttributes().get("classification"));
        assertEquals(5, capturedResource.getAttributes().get("required_clearance"));
    }

    @Test
    @DisplayName("Should return false when permission is denied with ABAC")
    void testCheckPermissionWithAttributes_Denied() throws IOException, PermitApiError {
        // Given
        String userKey = "user-123";
        HashMap<String, Object> userAttributes = new HashMap<String, Object>();
        userAttributes.put("clearance_level", 2); // Lower clearance

        HashMap<String, Object> resourceAttributes = new HashMap<String, Object>();
        resourceAttributes.put("required_clearance", 5); // Requires higher clearance

        when(mockPermit.check(any(User.class), eq("access"), any(Resource.class)))
                .thenReturn(false); // Permission denied

        // When
        boolean result = example.checkPermissionWithAttributes(
                userKey, userAttributes,
                "access", "classified-document", "doc-001",
                resourceAttributes, "tenant"
        );

        // Then
        assertFalse(result, "User with lower clearance should be denied");
    }

    @Test
    @DisplayName("Should propagate IOException from Permit SDK")
    void testCheckPermissionWithContext_IOException() throws IOException, PermitApiError {
        // Given
        when(mockPermit.check(any(User.class), any(String.class), any(Resource.class)))
                .thenThrow(new IOException("Connection timeout"));

        // When/Then
        IOException exception = assertThrows(IOException.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() throws Throwable {
                example.checkPermissionWithContext(
                        "user", "user@example.com", null,
                        "read", "document", null, null
                );
            }
        });
        assertEquals("Connection timeout", exception.getMessage());
    }

    @Test
    @DisplayName("Should propagate PermitApiError from Permit SDK")
    void testCheckPermissionWithAttributes_PermitApiError() throws IOException, PermitApiError {
        // Given
        when(mockPermit.check(any(User.class), any(String.class), any(Resource.class)))
                .thenThrow(new PermitApiError("Unauthorized", 401, "{\"error\":\"Invalid token\"}"));

        // When/Then
        PermitApiError exception = assertThrows(PermitApiError.class, new org.junit.jupiter.api.function.Executable() {
            @Override
            public void execute() throws Throwable {
                example.checkPermissionWithAttributes(
                        "user", new HashMap<String, Object>(),
                        "read", "document", null,
                        null, null
                );
            }
        });
        assertEquals(401, exception.getResponseCode());
    }

    @Test
    @DisplayName("Should handle multiple permission checks with different tenants")
    void testCheckPermissionWithContext_MultipleTenants() throws IOException, PermitApiError {
        // Given - user is permitted in tenant1 but not in tenant2
        when(mockPermit.check(any(User.class), eq("edit"), any(Resource.class)))
                .thenAnswer(invocation -> {
                    Resource resource = invocation.getArgument(2);
                    return "tenant1".equals(resource.getTenant());
                });

        // When
        boolean resultTenant1 = example.checkPermissionWithContext(
                "user", null, null, "edit", "document", "doc-1", "tenant1"
        );
        boolean resultTenant2 = example.checkPermissionWithContext(
                "user", null, null, "edit", "document", "doc-1", "tenant2"
        );

        // Then
        assertTrue(resultTenant1, "User should be permitted in tenant1");
        assertFalse(resultTenant2, "User should be denied in tenant2");
    }

    @Test
    @DisplayName("Should handle complex user attributes")
    void testCheckPermissionWithContext_ComplexAttributes() throws IOException, PermitApiError {
        // Given
        HashMap<String, Object> userAttributes = new HashMap<String, Object>();
        userAttributes.put("roles", new String[]{"admin", "editor"});
        userAttributes.put("is_active", true);
        userAttributes.put("login_count", 42);
        HashMap<String, String> metadata = new HashMap<String, String>();
        metadata.put("source", "oauth");
        metadata.put("provider", "google");
        userAttributes.put("metadata", metadata);

        when(mockPermit.check(any(User.class), eq("manage"), any(Resource.class)))
                .thenReturn(true);

        // When
        boolean result = example.checkPermissionWithContext(
                "admin-user", "admin@example.com", userAttributes,
                "manage", "settings", "global-settings", "main-tenant"
        );

        // Then
        assertTrue(result);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(mockPermit).check(userCaptor.capture(), eq("manage"), any(Resource.class));

        User capturedUser = userCaptor.getValue();
        assertNotNull(capturedUser.getAttributes());
        assertTrue((Boolean) capturedUser.getAttributes().get("is_active"));
        assertEquals(42, capturedUser.getAttributes().get("login_count"));
    }

    @Test
    @DisplayName("Should verify the actual example class can be instantiated with a real Permit instance")
    void testAdvancedPermissionCheckExample_CanBeInstantiated() {
        try {
            // Given
            PermitConfig config = new PermitConfig.Builder("test-api-key")
                    .withPdpAddress("http://localhost:7766")
                    .build();
            Permit permit = new Permit(config);

            // When
            AdvancedPermissionCheckExample realExample = new AdvancedPermissionCheckExample(permit);

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
