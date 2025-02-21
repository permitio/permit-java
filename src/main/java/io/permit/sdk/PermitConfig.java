package io.permit.sdk;

import java.util.ArrayList;

/**
 * The {@code PermitConfig} class represents the configuration for the Permit SDK.
 */
public class PermitConfig {
    // main config vars
    private final String token;
    private final String pdp;
    private final String opa;
    private final String apiUrl;
    private final Boolean debugMode;
    private final Boolean proxyFactsViaPdp;
    private final int factsSyncTimeout;

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
    private PermitContext context;
    public final String version;
    private final static String defaultVersion = "unknown";

    private PermitConfig(Builder builder) {
        this.token = builder.token;
        this.pdp = builder.pdp;
        this.opa = builder.opa;
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
        this.context = builder.context;
        this.proxyFactsViaPdp = builder.proxyFactsViaPdp;
        this.factsSyncTimeout = builder.factsSyncTimeout;
        String runtimeVersion = Permit.class.getPackage().getImplementationVersion();
        this.version = (runtimeVersion == null) ? defaultVersion : runtimeVersion;
    }

    /**
     * Returns the API Key (token) used to authenticate against the Permit API.
     *
     * @return The API Key (token).
     */
    public String getToken() {
        return token;
    }

    /**
     * Returns the configured URL of the Permit REST API.
     * Unless you are an enterprise Permit user, it's unlikely you need to change the default (api.permit.io).
     *
     * @return The URL of the Permit REST API.
     */
    public String getApiUrl() {
        return apiUrl;
    }

    /**
     * Returns the URL of the Policy Decision Point (PDP) used to evaluate authorization queries (i.e: permission checks).
     *
     * @return The PDP URL.
     */
    public String getPdpAddress() {
        return pdp;
    }

    /**
     * Returns the URL of the OPA Inside Policy Decision Point (PDP) used to evaluate authorization queries (i.e: permission checks).
     *
     * @return The OPA URL.
     */
    public String getOpaAddress() {
        return opa;
    }

     /**
     * Returns whether the Permit SDK is in debug mode.
     *
     * @return {@code true} if debug mode is enabled, {@code false} otherwise.
     */
    public Boolean isDebugMode() {
        return debugMode;
    }

    /**
     * Returns whether the facts via the PDP API instead of using the default Permit REST API
     *
     * @return {@code true} if proxying facts via the PDP is enabled, {@code false} otherwise.
     */
    public Boolean isProxyFactsViaPdp() {
        return proxyFactsViaPdp;
    }

    /**
     * Returns the timeout for facts synchronization.
     *
     * @return The facts synchronization timeout.
     */
    public int getFactsSyncTimeout() {
        return factsSyncTimeout;
    }

    /**
     * Returns the log level configured for the Permit SDK Logger.
     *
     * @return The log level.
     */
    public String getLogLevel() {
        return logLevel;
    }

    /**
     * Returns the label configured for logs emitted by the Permit SDK Logger.
     *
     * @return The log label.
     */
    public String getLogLabel() {
        return logLabel;
    }
    
    /**
     * Returns whether the log output should be in JSON format.
     *
     * @return {@code true} if log output should be in JSON format, {@code false} otherwise.
     */
    public Boolean shouldLogAsJson() {
        return logAsJson;
    }

    /**
     * Returns whether auto mapping of urls to resources is enabled in the Permit configuration.
     *
     * @return {@code true} if auto-mapping is enabled, {@code false} otherwise.
     * @deprecated
     */
    public Boolean isAutoMappingEnabled() {
        return autoMappingEnable;
    }

    /**
     * Returns the list of URL prefixes to be ignored during auto-mapping.
     *
     * @return The list of ignored URL prefixes.
     * @deprecated
     */
    public ArrayList<String> getAutoMappingIgnoredUrlPrefixes() {
        return autoMappingIgnoredUrlPrefixes;
    }

    /**
     * Returns whether auto-mapping is in review mode in the Permit configuration.
     *
     * @return {@code true} if auto-mapping is in review mode, {@code false} otherwise.
     * @deprecated
     */
    public Boolean getAutoMappingReviewMode() {
        return autoMappingReviewMode;
    }

    /**
     * Returns the key of the default tenant used for permission checks if a tenant
     * is not specified (or known) for the resource provided to permit.check().
     *
     * @return The key of the tenant configured as the default tenant.
     */
    public String getDefaultTenant() {
        return defaultTenant;
    }

    /**
     * Returns whether the default tenant should be used if the resource tenant is not specified.
     *
     * @return {@code true} if the default tenant should be used as fallback, {@code false} otherwise.
     */
    public Boolean shouldUseDefaultTenantIfEmpty() {
        return useDefaultTenantIfEmpty;
    }

    /**
     * Returns the scope in which API objects are referenced for the SDK API methods.
     * A scope can be: the entire workspace, a specific project, or a specific environment.
     * Most API methods require an environment scope (e.g: in order to create a role you
     * need to know in which environment to place it), but some methods can work in higher
     * scopes (for example: creating a project can be done with a workspace-level scope).
     *
     * @return The default context for permission checks.
     */
    public PermitContext getContext() {
        return context;
    }

    /**
     * Sets the scope context for the API key used by the SDK.
     *
     * You can only set a scope that is larger than the one granted by the API key.
     * E.g: if the API key is a project-level API key - you can select an org-level scope
     * or a project-level scope (because both are known from the API key), but you cannot
     * set an environment-level scope.
     *
     * @param context The Permit context to be set.
     */
    public void setContext(PermitContext context) {
        this.context = context;
    }

    /**
     * The {@code Builder} class provides a builder interface for constructing {@code PermitConfig} objects.
     */
    public static class Builder {
        // main config vars
        private String token;
        private String pdp = "http://localhost:7766";
        private String opa = "http://localhost:8181";
        private String apiUrl = "https://api.permit.io";
        private Boolean debugMode = false;
        private Boolean proxyFactsViaPdp = false;
        private int factsSyncTimeout = 0;

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

        private PermitContext context;

        /**
         * Constructs a new instance of the {@code Builder} class with the specified token (API Key).
         *
         * @param token The token (API Key) for the Permit configuration.
         */
        public Builder(String token) {
            this.token = token;
            this.context = (new PermitContext.Builder()).build();
        }

        /**
         * Configures the Policy Decision Point (PDP) address.
         *
         * @param pdp The PDP address to be set.
         * @return The updated {@code Builder} object.
         */
        public Builder withPdpAddress(String pdp) {
            this.pdp = pdp;
            return this;
        }

          /**
         * Configures the Policy Decision Point (PDP) address.
         *
         * @param opa The PDP address to be set.
         * @return The updated {@code Builder} object.
         */
        public Builder withOpaAddress(String opa) {
            this.opa = opa;
            return this;
        }

        /**
         * Configures the URL of the Permit REST API.
         *
         * @param apiUrl The API URL to be set.
         * @return The updated {@code Builder} object.
         */
        public Builder withApiUrl(String apiUrl) {
            this.apiUrl = apiUrl;
            return this;
        }

        /**
         * Configures whether the SDK should run in debug mode.
         *
         * @param debugMode The debug mode to be set.
         * @return The updated {@code Builder} object.
         */
        public Builder withDebugMode(Boolean debugMode) {
            this.debugMode = debugMode;
            return this;
        }

        /**
         * Configures whether the SDK should proxy facts via the PDP API instead of using the default Permit REST API.
         *
         * @param proxyFactsViaPdp The proxy facts via PDP mode to be set.
         * @return The updated {@code Builder} object.
         */
        public Builder withProxyFactsViaPdp(Boolean proxyFactsViaPdp) {
            this.proxyFactsViaPdp = proxyFactsViaPdp;
            return this;
        }

        /**
         * Configures the timeout for facts synchronization.
         *
         * @param factsSyncTimeout The facts synchronization timeout to be set.
         * @return The updated {@code Builder} object.
         */
        public Builder withFactsSyncTimeout(int factsSyncTimeout) {
            this.factsSyncTimeout = factsSyncTimeout;
            return this;
        }

        /**
         * Sets the log level configured for the Permit SDK Logger.
         *
         * @param logLevel The log level to be set.
         * @return The updated {@code Builder} object.
         */
        public Builder withLogLevel(String logLevel) {
            this.logLevel = logLevel;
            return this;
        }

        /**
         * Sets the label configured for logs emitted by the Permit SDK Logger.
         *
         * @param logLabel The logger label to be set.
         * @return The updated {@code Builder} object.
         */
        public Builder withLogLabel(String logLabel) {
            this.logLabel = logLabel;
            return this;
        }

        /**
         * Sets whether the SDK log output should be in JSON format.
         *
         * @param logAsJson {@code true} if log output should be in JSON format, {@code false} otherwise.
         * @return The updated {@code Builder} object.
         */
        public Builder withLogAsJson(Boolean logAsJson) {
            this.logAsJson = logAsJson;
            return this;
        }

        /**
         * Returns whether auto mapping of urls to resources is enabled in the Permit configuration.
         *
         * @param autoMappingEnable {@code true} if auto-mapping is enabled, {@code false} otherwise.
         * @return The updated {@code Builder} object.
         * @deprecated
         */
        public Builder withAutoMappingEnable(Boolean autoMappingEnable) {
            this.autoMappingEnable = autoMappingEnable;
            return this;
        }

        /**
         * Sets the list of URL prefixes to be ignored during auto-mapping.
         *
         * @param autoMappingIgnoredUrlPrefixes The list of ignored URL prefixes to be set.
         * @return The updated {@code Builder} object.
         * @deprecated
         */
        public Builder withAutoMappingIgnoredUrlPrefixes(ArrayList<String> autoMappingIgnoredUrlPrefixes) {
            this.autoMappingIgnoredUrlPrefixes = autoMappingIgnoredUrlPrefixes;
            return this;
        }

        /**
         * Sets whether auto URL mapping is in review mode.
         *
         * @param autoMappingReviewMode {@code true} if auto-mapping is in review mode, {@code false} otherwise.
         * @return The updated {@code Builder} object.
         * @deprecated
         */
        public Builder withAutoMappingReviewMode(Boolean autoMappingReviewMode) {
            this.autoMappingReviewMode = autoMappingReviewMode;
            return this;
        }

        /**
         * Sets the default tenant that will be used as fallback for permission checks.
         *
         * @param defaultTenant The default tenant to be set.
         * @return The updated {@code Builder} object.
         */
        public Builder withDefaultTenant(String defaultTenant) {
            this.defaultTenant = defaultTenant;
            return this;
        }

        /**
         * Sets whether the default tenant should be used if a tenant
         * is not specified for the permission check resource parameter.
         *
         * @param useDefaultTenantIfEmpty {@code true} if the default tenant should be used if no tenant was specified, {@code false} otherwise.
         * @return The updated {@code Builder} object.
         */
        public Builder withUseDefaultTenantIfEmpty(Boolean useDefaultTenantIfEmpty) {
            this.useDefaultTenantIfEmpty = useDefaultTenantIfEmpty;
            return this;
        }

        /**
         * Sets the scope context for the API key used by the SDK.
         *
         * You can only set a scope that is larger than the one granted by the API key.
         * E.g: if the API key is a project-level API key - you can select an org-level scope
         * or a project-level scope (because both are known from the API key), but you cannot
         * set an environment-level scope.
         *
         * @param context The Permit context to be set.
         * @return The updated {@code Builder} object.
         */
        public Builder withContext(PermitContext context) {
            this.context = context;
            return this;
        }

        /**
         * builds a PermitConfig instance from the builder configuration.
         * 
         * @return The instance of the {@code PermitConfig} object.
         */
        public PermitConfig build() {
            PermitConfig config = new PermitConfig(this);
            return config;
        }
    }
}
