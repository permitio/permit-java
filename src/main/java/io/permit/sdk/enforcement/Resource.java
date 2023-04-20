package io.permit.sdk.enforcement;

import io.permit.sdk.PermitConfig;

import java.util.HashMap;

public class Resource {
    private String type;
    private String key = null;
    private String tenant = null;
    private HashMap<String, Object> attributes = null;
    private HashMap<String, Object> context = new HashMap<>();

    public Resource(Builder builder) {
        this.type = builder.type;
        this.key = builder.key;
        this.tenant = builder.tenant;
        this.attributes = builder.attributes;
        this.context = builder.context;
    }

    public String getType() {
        return this.type;
    }

    public String getKey() {
        return this.key;
    }

    public String getTenant() {
        return this.tenant;
    }

    public HashMap<String, Object> getAttributes() {
        return this.attributes;
    }

    public HashMap<String, Object> getContext() {
        return this.context;
    }

    public static Resource fromString(String resourceString) {
        return new Resource(new Resource.Builder(resourceString));
    }

    public String toString() {
        return (this.key == null) ? String.format("%s:*", this.type) : String.format("%s:%s", this.type, this.key);
    }

    public Resource normalize(PermitConfig config) {
        // if tenant is empty, we migth auto-set the default tenant according to config
        String safeTenant = (this.tenant == null && config.shouldUseDefaultTenantIfEmpty())
                ? config.getDefaultTenant()
                : this.tenant;

        // copy tenant from resource.tenant to resource.context.tenant (until we change RBAC policy)
        HashMap<String, Object> safeContext = new HashMap<>();
        safeContext.putAll(this.context);
        if (safeTenant != null && !this.context.containsKey("tenant")) {
            safeContext.put("tenant", safeTenant);
        }

        Resource normalizedResource = new Resource.Builder(this.type)
                .withKey(this.key)
                .withTenant(safeTenant)
                .withAttributes(this.attributes)
                .withContext(safeContext)
                .build();

        return normalizedResource;
    }

    public static class Builder {
        private String type;
        private String key = null;
        private String tenant = null;
        private HashMap<String, Object> attributes = null;
        private HashMap<String, Object> context = new HashMap<>();

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
                this.key = parts[1];
            }
        }

        public Builder withKey(String key) {
            this.key = key;
            return this;
        }

        public Builder withTenant(String tenant) {
            this.tenant = tenant;
            return this;
        }

        public Builder withAttributes(HashMap<String, Object> attributes) {
            this.attributes = attributes;
            return this;
        }

        public Builder withContext(HashMap<String, Object> context) {
            this.context = context;
            return this;
        }

        public Resource build() {
            return new Resource(this);
        }
    }
}
