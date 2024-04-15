package io.permit.sdk.enforcement;

import io.permit.sdk.util.Context;

import java.util.ArrayList;
import java.util.List;

public final class GetUserPermissionsQuery {
    public final User user;
    public final List<String> tenants;
    public final List<String> resource_types;
    public final List<String> resources;
    public final Context context;

    /**
     * input to get user permissions api
     *
     * @param user              the user we'd like to get a list of permissions for.
     * @param tenants           filter only permissions granted on specific tenants.
     * @param resource_types    filter permissions based on resource type.
     * @param resources         filter permissions based on resource instance key.
     * @param context  The context for the authorization check.
     */
    public GetUserPermissionsQuery(User user, List<String> tenants, List<String> resource_types, List<String> resources, Context context) {
        this.user = user;
        this.tenants = tenants;
        this.resource_types = resource_types;
        this.resources = resources;
        this.context = context;
    }

    public GetUserPermissionsQuery(User user, List<String> tenants, List<String> resource_types, List<String> resources) {
        this(user, tenants, resource_types, resources, new Context());
    }

    public GetUserPermissionsQuery(User user, List<String> tenants, List<String> resource_types) {
        this(user, tenants, resource_types, null);
    }

    public GetUserPermissionsQuery(User user, List<String> tenants) {
        this(user, tenants, null);
    }

    public GetUserPermissionsQuery(User user) {
        this(user, null);
    }
}
