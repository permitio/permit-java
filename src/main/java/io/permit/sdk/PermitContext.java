package io.permit.sdk;

public class PermitContext {
    private final ApiKeyLevel apiKeyLevel;
    private final String org;
    private final String project;
    private final String environment;

    public PermitContext(Builder builder) {
        this.apiKeyLevel = builder.apiKeyLevel;
        this.org = builder.org;
        this.project = builder.project;
        this.environment = builder.environment;
    }

    public ApiKeyLevel getApiKeyLevel() {
        return apiKeyLevel;
    }

    public String getOrganization() {
        return org;
    }

    public String getProject() {
        return project;
    }

    public String getEnvironment() {
        return environment;
    }

    public static class Builder {
        private ApiKeyLevel apiKeyLevel;
        private String org;
        private String project;
        private String environment;

        public Builder() {
            this.apiKeyLevel = ApiKeyLevel.WAIT_FOR_INIT;
            this.org = null;
            this.project = null;
            this.environment = null;
        }

        public Builder withOrganization(String org) {
            this.org = org;
            this.project = null;
            this.environment = null;
            this.apiKeyLevel = ApiKeyLevel.ORGANIZATION_LEVEL_API_KEY;
            return this;
        }

        public Builder withProject(String org, String project) {
            this.org = org;
            this.project = project;
            this.environment = null;
            this.apiKeyLevel = ApiKeyLevel.PROJECT_LEVEL_API_KEY;
            return this;
        }

        public Builder withEnvironment(String org, String project, String environment) {
            this.org = org;
            this.project = project;
            this.environment = environment;
            this.apiKeyLevel = ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY;
            return this;
        }

        public PermitContext build() {
            PermitContext context = new PermitContext(this);
            return context;
        }
    }
}
