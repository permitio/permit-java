package io.permit.sdk.enforcement;

import java.util.List;

/**
 * The {@code ObjectPermissions} class represents a single object (tenant or resource instance) that the queried user can access.
 */
public class ObjectPermissions {

    public final TenantDetails tenant;
    public final ResourceDetails resource;
    public final List<String> permissions;

    public ObjectPermissions(TenantDetails tenant, ResourceDetails resource, List<String> permissions) {
        this.tenant = tenant;
        this.resource = resource;
        this.permissions = permissions;
    }
}
