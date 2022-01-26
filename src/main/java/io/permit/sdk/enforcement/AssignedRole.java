package io.permit.sdk.enforcement;

public class AssignedRole {
    private String role;
    private String tenant;

    public AssignedRole(String role, String tenant) {
        this.role = role;
        this.tenant = tenant;
    }

    public String getTenant() {
        return tenant;
    }

    public String getRole() {
        return role;
    }
}
