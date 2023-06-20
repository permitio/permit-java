package io.permit.sdk;

/**
 * The {@code ApiKeyLevel} enum represents the granted access level of an API key used by the Permit SDK.
 * The access level determine the scope of permissions granted by the API key: is the API granting permission
 * to the entire workspace (i.e: organization), to a specific project within the workspace, or to a specific
 * project and environment within the workspace.
 *
 * NOTE: Access levels are enforced on the backend using the API key (you cannot override this value).
 * this enum is intended as a read-only representation to help the user understand his access level from code.
 */
public enum ApiKeyLevel {
    WAIT_FOR_INIT(0),
    ORGANIZATION_LEVEL_API_KEY(1),
    PROJECT_LEVEL_API_KEY(2),
    ENVIRONMENT_LEVEL_API_KEY(3);

    private final int value;

    ApiKeyLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
