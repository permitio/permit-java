package io.permit.sdk.enforcement;

import java.util.HashMap;

/**
 * The {@code UserPermissionsOPA} class represents all the objects a user can access.
 */
final public class UserPermissionsOpa {
    private String decisionId;
    private HashMap<String, ObjectPermissions> result;

    // Getters and setters
    public String getDecisionId() {
        return decisionId;
    }

    public void setDecisionId(String decisionId) {
        this.decisionId = decisionId;
    }

    public HashMap<String, ObjectPermissions> getResult() {
        return result;
    }

    public void setResult(HashMap<String, ObjectPermissions> result) {
        this.result = result;
    }
}
