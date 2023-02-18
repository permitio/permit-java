package io.permit.sdk;

import java.util.ArrayList;

public class PermitConfig {
    // main config vars
    private final String token;
    private final String pdp;
    private final String apiUrl;
    private final Boolean debugMode;

    // logger config
    private final String logLevel;
    private final String logLabel;
    private final Boolean logAsJson;

    // auto mapping config
    private final Boolean autoMappingEnable;
    private final ArrayList<String> autoMappingIgnoredUrlPrefixes;
    private final Boolean autoMappingReviewMode;

    // multi tenancy config
    private final String defaultTenant;
    private final Boolean useDefaultTenantIfEmpty;

    private PermitConfig(Builder builder) {
        this.token = builder.token;
        this.pdp = builder.pdp;
        this.apiUrl = builder.apiUrl;
        this.debugMode = builder.debugMode;
        this.logLevel = builder.logLevel;
        this.logLabel = builder.logLabel;
        this.logAsJson = builder.logAsJson;
        this.autoMappingEnable = builder.autoMappingEnable;
        this.autoMappingIgnoredUrlPrefixes = builder.autoMappingIgnoredUrlPrefixes;
        this.autoMappingReviewMode = builder.autoMappingReviewMode;
        this.defaultTenant = builder.defaultTenant;
        this.useDefaultTenantIfEmpty = builder.useDefaultTenantIfEmpty;
    }

    // getters
    public String getToken() {
        return token;
    }
    public String getApiUrl() { return apiUrl; }
    public String getPdpAddress() {
        return pdp;
    }
    public Boolean isDebugMode() {
        return debugMode;
    }
    public String getLogLevel() {
        return logLevel;
    }
    public String getLogLabel() {
        return logLabel;
    }
    public Boolean shouldLogAsJson() {
        return logAsJson;
    }
    public Boolean isAutoMappingEnabled() {
        return autoMappingEnable;
    }
    public ArrayList<String> getAutoMappingIgnoredUrlPrefixes() {
        return autoMappingIgnoredUrlPrefixes;
    }
    public Boolean getAutoMappingReviewMode() {
        return autoMappingReviewMode;
    }
    public String getDefaultTenant() {
        return defaultTenant;
    }
    public Boolean shouldUseDefaultTenantIfEmpty() {
        return useDefaultTenantIfEmpty;
    }

    public static class Builder {
        // main config vars
        private String token;
        private String pdp = "http://localhost:7766";
        private String apiUrl = "https://api.permit.io";
        private Boolean debugMode = false;

        // logger config
        private String logLevel = "info";
        private String logLabel = "PermitSDK";
        private Boolean logAsJson = false;

        // auto mapping config
        private Boolean autoMappingEnable = false;
        private ArrayList<String> autoMappingIgnoredUrlPrefixes = new ArrayList<String>();
        private Boolean autoMappingReviewMode = false;

        // multi tenancy config
        private String defaultTenant = "default";
        private Boolean useDefaultTenantIfEmpty = true;

        public Builder(String token) {
            this.token = token;
        }

        public Builder withPdpAddress(String pdp) {
            this.pdp = pdp;
            return this;
        }

        public Builder withApiUrl(String apiUrl) {
            this.apiUrl = apiUrl;
            return this;
        }

        public Builder withDebugMode(Boolean debugMode) {
            this.debugMode = debugMode;
            return this;
        }

        public Builder withLogLevel(String logLevel) {
            this.logLevel = logLevel;
            return this;
        }

        public Builder withLogLabel(String logLabel) {
            this.logLabel = logLabel;
            return this;
        }

        public Builder withLogAsJson(Boolean logAsJson) {
            this.logAsJson = logAsJson;
            return this;
        }

        public Builder withAutoMappingEnable(Boolean autoMappingEnable) {
            this.autoMappingEnable = autoMappingEnable;
            return this;
        }

        public Builder withAutoMappingIgnoredUrlPrefixes(ArrayList<String> autoMappingIgnoredUrlPrefixes) {
            this.autoMappingIgnoredUrlPrefixes = autoMappingIgnoredUrlPrefixes;
            return this;
        }

        public Builder withAutoMappingReviewMode(Boolean autoMappingReviewMode) {
            this.autoMappingReviewMode = autoMappingReviewMode;
            return this;
        }

        public Builder withDefaultTenant(String defaultTenant) {
            this.defaultTenant = defaultTenant;
            return this;
        }

        public Builder withUseDefaultTenantIfEmpty(Boolean useDefaultTenantIfEmpty) {
            this.useDefaultTenantIfEmpty = useDefaultTenantIfEmpty;
            return this;
        }

        public PermitConfig build() {
            PermitConfig config = new PermitConfig(this);
            return config;
        }
    }
}
