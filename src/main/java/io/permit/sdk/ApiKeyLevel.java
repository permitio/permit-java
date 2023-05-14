package io.permit.sdk;

/**
 * The {@code ApiKeyLevel} enum represents the different levels (or scopes) of API keys in the Permit SDK.
 * These levels determine the scope of permissions granted by the API key: is the API granting permission
 * to the entire workspace (i.e: organization), to a specific project within the workspace, or to a specific
 * project and environment within the workspace.
 */
public enum ApiKeyLevel {
    WAIT_FOR_INIT,
    ORGANIZATION_LEVEL_API_KEY,
    PROJECT_LEVEL_API_KEY,
    ENVIRONMENT_LEVEL_API_KEY,
}
