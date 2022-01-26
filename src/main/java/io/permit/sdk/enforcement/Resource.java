package io.permit.sdk.enforcement;

import io.permit.sdk.PermitConfig;

import java.util.HashMap;

public class Resource {
    private String type;
    private String id = null;
    private String tenant = null;
    private HashMap<String, String> attributes = null;
    private HashMap<String, String> context = new HashMap<>();

    public Resource(Builder builder) {
        this.type = builder.type;
        this.id = builder.id;
        this.tenant = builder.tenant;
        this.attributes = builder.attributes;
        this.context = builder.context;
    }

    public static Resource fromString(String resourceString) {
        return new Resource(new Resource.Builder(resourceString));
    }

    public Resource normalize(PermitConfig config) {
        // if tenant is empty, we migth auto-set the default tenant according to config
        String safeTenant = (this.tenant == null && config.shouldUseDefaultTenantIfEmpty())
                ? config.getDefaultTenant()
                : this.tenant;

        // copy tenant from resource.tenant to resource.context.tenant (until we change RBAC policy)
        HashMap<String, String> safeContext = new HashMap<>();
        safeContext.putAll(this.context);
        if (this.tenant != null && !this.context.containsKey("tenant")) {
            safeContext.put("tenant", this.tenant);
        }

        Resource normalizedResource = new Resource.Builder(this.type)
                .withId(this.id)
                .withTenant(safeTenant)
                .withAttributes(this.attributes)
                .withContext(safeContext)
                .build();

        return normalizedResource;
    }

    public static class Builder {
        private String type;
        private String id = null;
        private String tenant = null;
        private HashMap<String, String> attributes = null;
        private HashMap<String, String> context = null;

        private final String resourceDelimiter = ":";

        public Builder(String resourceString) {
            String[] parts = resourceString.split(resourceDelimiter);
            if (parts.length < 1 || parts.length > 2) {
                throw new IllegalArgumentException(
                    String.format(
                        "permit.check() got invalid resource string: '%s'",
                        resourceString
                    )
                );
            }
            this.type = parts[0];
            if (parts.length > 1) {
                this.id = parts[1];
            }
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withTenant(String tenant) {
            this.tenant = tenant;
            return this;
        }

        public Builder withAttributes(HashMap<String, String> attributes) {
            this.attributes = attributes;
            return this;
        }

        public Builder withContext(HashMap<String, String> context) {
            this.context = context;
            return this;
        }

        public Resource build() {
            return new Resource(this);
        }
    }
}
