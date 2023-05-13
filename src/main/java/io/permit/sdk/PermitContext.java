package io.permit.sdk;

/**
 * The {@code PermitContext} class represents the context for Permit API calls.
 * 
 * A context can be: the entire workspace, a specific project, or a specific environment.
 * Most API methods require an environment scope (e.g: in order to create a role you
 * need to know in which environment to place it), but some methods can work in higher
 * scopes (for example: creating a project can be done with a workspace-level scope).
 */
public class PermitContext {
    private final ApiKeyLevel apiKeyLevel;
    private final String org;
    private final String project;
    private final String environment;

    /**
     * Constructs a new instance of the {@code PermitContext} class with the specified builder.
     *
     * @param builder The builder used to construct the PermitContext.
     */
    public PermitContext(Builder builder) {
        this.apiKeyLevel = builder.apiKeyLevel;
        this.org = builder.org;
        this.project = builder.project;
        this.environment = builder.environment;
    }

    /**
     * Returns the API key level associated with the Permit context.
     *
     * @return The API key level.
     */
    public ApiKeyLevel getApiKeyLevel() {
        return apiKeyLevel;
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
     * The {@code Builder} class provides a builder interface for constructing {@code PermitContext} objects.
     */
    public static class Builder {
        private ApiKeyLevel apiKeyLevel;
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
            this.apiKeyLevel = ApiKeyLevel.WAIT_FOR_INIT;
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
            this.org = org;
            this.project = null;
            this.environment = null;
            this.apiKeyLevel = ApiKeyLevel.ORGANIZATION_LEVEL_API_KEY;
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
            this.org = org;
            this.project = project;
            this.environment = null;
            this.apiKeyLevel = ApiKeyLevel.PROJECT_LEVEL_API_KEY;
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
            this.org = org;
            this.project = project;
            this.environment = environment;
            this.apiKeyLevel = ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY;
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
