package io.permit.sdk;

import io.permit.sdk.api.PermitContextChangeError;

/**
 * The {@code PermitContext} class represents the context for Permit API calls.
 *
 * Since the Permit API hierarchy is deeply nested, it is less convenient to specify
 * the full object hierarchy in every request.
 *
 * For example, in order to list roles, the user needs to specify the (id or key) of the:
 * - the org
 * - the project
 * - then environment
 * in which the roles are located under.
 *
 * Instead, the SDK can "remember" the current context and "auto-complete" the details
 * from that context.
 *
 * We then get this kind of experience:
 * <pre>{@code
 * permit.api.roles.list();
 * }</pre>
 *
 * We can only run this function if the current context already knows the org, project,
 * and environment that we want to run under, and that is why in this example the api
 * method assumes we are running under an {@link ApiContextLevel#ENVIRONMENT} context.
 */
public class PermitContext {
    private ApiKeyLevel apiKeyLevel;
    private String permittedOrganization;
    private String permittedProject;
    private String permittedEnvironment;

    private ApiContextLevel contextLevel;
    private String org;
    private String project;
    private String environment;

    /**
     * Constructs a new instance of the {@code PermitContext} class with the specified builder.
     *
     * @param builder The builder used to construct the PermitContext.
     */
    public PermitContext(Builder builder) {
        this.saveApiKeyAccessibleScope(builder.org, builder.project, builder.environment);
        this.contextLevel = builder.contextLevel;
        this.org = builder.org;
        this.project = builder.project;
        this.environment = builder.environment;
    }

    public PermitContext() {
        // access level
        this.apiKeyLevel = ApiKeyLevel.WAIT_FOR_INIT;
        this.permittedOrganization = null;
        this.permittedProject = null;
        this.permittedEnvironment = null;

        // known context
        this.contextLevel = ApiContextLevel.WAIT_FOR_INIT;
        this.org = null;
        this.project = null;
        this.environment = null;
    }

    private void saveApiKeyAccessibleScope(String org, String project, String environment) {
        // Do not call this method directly!
        permittedOrganization = org; // cannot be null

        if (project != null && environment != null) {
            permittedProject = project;
            permittedEnvironment = environment;
            apiKeyLevel = ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY;
        } else if (project != null) {
            permittedProject = project;
            permittedEnvironment = null;
            apiKeyLevel = ApiKeyLevel.PROJECT_LEVEL_API_KEY;
        } else {
            permittedProject = null;
            permittedEnvironment = null;
            apiKeyLevel = ApiKeyLevel.ORGANIZATION_LEVEL_API_KEY;
        }
    }

    /**
     * Returns the access level of the API key used by the SDK.
     *
     * @deprecated replaced with {@link PermitContext#getPermittedAccessLevel}
     * @return The API key access level.
     */
    public ApiKeyLevel getApiKeyLevel() {
        return apiKeyLevel;
    }

    /**
     * Returns the access level of the API key used by the SDK.
     *
     * @return The API key access level.
     */
    public ApiKeyLevel getPermittedAccessLevel() {
        return apiKeyLevel;
    }

    /**
     * Returns the current SDK context level.
     *
     * @return the context level.
     */
    public ApiContextLevel getContextLevel() {
        return contextLevel;
    }

    /**
     * Returns the organization (workspace) associated with the Permit context.
     *
     * @return The organization (workspace).
     */
    public String getOrganization() {
        return org;
    }

    /**
     * Returns the project associated with the Permit context, if such is defined.
     *
     * @return The project or null.
     */
    public String getProject() {
        return project;
    }

    /**
     * Returns the environment associated with the Permit context, if such is defined.
     *
     * @return The environment or null.
     */
    public String getEnvironment() {
        return environment;
    }

    /**
     * Sets the current context of the SDK to a specific organization.
     *
     * @param org The organization uuid.
     * @throws PermitContextChangeError If the SDK context cannot be set due to insufficient API Key permissions.
     */
    public void setOrganizationLevelContext(String org) throws PermitContextChangeError {
        verifyCanAccessOrg(org);
        this.contextLevel = ApiContextLevel.ORGANIZATION;
        this.org = org;
        this.project = null;
        this.environment = null;
    }

    /**
     * Sets the current context of the SDK to a specific project within an organization.
     *
     * @param org     The organization uuid.
     * @param project The project uuid.
     * @throws PermitContextChangeError If the SDK context cannot be set due to insufficient API Key permissions.
     */
    public void setProjectLevelContext(String org, String project) throws PermitContextChangeError {
        verifyCanAccessProject(org, project);
        this.contextLevel = ApiContextLevel.PROJECT;
        this.org = org;
        this.project = project;
        this.environment = null;
    }

    /**
     * Sets the current context of the SDK to a specific environment within an organization and project.
     *
     * @param org         The organization uuid.
     * @param project     The project uuid.
     * @param environment The environment uuid.
     * @throws PermitContextChangeError If the SDK context cannot be set due to insufficient API Key permissions.
     */
    public void setEnvironmentLevelContext(String org, String project, String environment) throws PermitContextChangeError {
        verifyCanAccessEnvironment(org, project, environment);
        this.contextLevel = ApiContextLevel.ENVIRONMENT;
        this.org = org;
        this.project = project;
        this.environment = environment;
    }

    private void verifyCanAccessOrg(String org) throws PermitContextChangeError {
        if (!org.equals(permittedOrganization)) {
            throw new PermitContextChangeError(
                    "You cannot set an SDK context with org '" + org +
                            "' due to insufficient API Key permissions"
            );
        }
    }

    private void verifyCanAccessProject(String org, String project) throws PermitContextChangeError {
        verifyCanAccessOrg(org);
        if (!project.equals(permittedProject)) {
            throw new PermitContextChangeError(
                    "You cannot set an SDK context with project '" + project +
                            "' due to insufficient API Key permissions"
            );
        }
    }

    private void verifyCanAccessEnvironment(String org, String project, String environment) throws PermitContextChangeError {
        verifyCanAccessProject(org, project);
        if (!environment.equals(permittedEnvironment)) {
            throw new PermitContextChangeError(
                    "You cannot set an SDK context with environment '" + environment +
                            "' due to insufficient API Key permissions"
            );
        }
    }

    /**
     * The {@code Builder} class provides a builder interface for constructing {@code PermitContext} objects.
     */
    public static class Builder {

        private ApiContextLevel contextLevel;
        private String org;
        private String project;
        private String environment;

        /**
         * Constructs a new instance of the {@code Builder} class.
         * The initial API key level is set to the placeholder value {@link ApiKeyLevel#WAIT_FOR_INIT}.
         * The first API call that will be invoked will trigger a context check for the API key and this
         * context will be stored in the SDK configuration for future API calls.
         */
        public Builder() {
            this.contextLevel = ApiContextLevel.WAIT_FOR_INIT;
            this.org = null;
            this.project = null;
            this.environment = null;
        }

        /**
         * Sets the organization (workspace) for the Permit context.
         * The project and environment will be reset to {@code null},
         * and the API key level will be set to {@link ApiKeyLevel#ORGANIZATION_LEVEL_API_KEY}.
         * 
         * @param org The organization to be set.
         * @return The updated {@code Builder} object.
         */
        public Builder withOrganization(String org) {
            this.contextLevel = ApiContextLevel.ORGANIZATION;
            this.org = org;
            this.project = null;
            this.environment = null;
            return this;
        }

        /**
         * Sets the project for the Permit context.
         * The environment will be reset to {@code null},
         * and the API key level will be set to {@link ApiKeyLevel#PROJECT_LEVEL_API_KEY}.
         *
         * @param org     The parent organization of the project.
         * @param project The project to be set.
         * @return The updated {@code Builder} object.
         */
        public Builder withProject(String org, String project) {
            this.contextLevel = ApiContextLevel.PROJECT;
            this.org = org;
            this.project = project;
            this.environment = null;
            return this;
        }

        /**
         * Sets the environment for the Permit context.
         * The API key level will be set to {@link ApiKeyLevel#ENVIRONMENT_LEVEL_API_KEY}.
         *
         * @param org         The parent organization of the environment.
         * @param project     The parent project of the environment.
         * @param environment The environment to be set.
         * @return The updated {@code Builder} object.
         */
        public Builder withEnvironment(String org, String project, String environment) {
            this.contextLevel = ApiContextLevel.ENVIRONMENT;
            this.org = org;
            this.project = project;
            this.environment = environment;
            return this;
        }

        /**
         * builds a PermitContext instance from the builder configuration.
         * 
         * @return The instance of the {@code PermitContext} object.
         */
        public PermitContext build() {
            PermitContext context = new PermitContext(this);
            return context;
        }
    }
}
