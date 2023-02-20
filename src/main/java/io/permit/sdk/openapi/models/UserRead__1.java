
package io.permit.sdk.openapi.models;

import java.util.HashMap;
import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * UserRead
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class UserRead__1 {

    /**
     * Key
     * <p>
     * A unique id by which Permit will identify the user for permission checks.
     * (Required)
     * 
     */
    @SerializedName("key")
    @Expose
    public java.lang.String key;
    /**
     * Id
     * <p>
     * Unique id of the user
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    public java.lang.String id;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the user belongs to.
     * (Required)
     * 
     */
    @SerializedName("organization_id")
    @Expose
    public java.lang.String organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the user belongs to.
     * (Required)
     * 
     */
    @SerializedName("project_id")
    @Expose
    public java.lang.String projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the user belongs to.
     * (Required)
     * 
     */
    @SerializedName("environment_id")
    @Expose
    public java.lang.String environmentId;
    /**
     * Associated Tenants
     * <p>
     * 
     * 
     */
    @SerializedName("associated_tenants")
    @Expose
    public List<UserInTenant> associatedTenants;
    /**
     * Roles
     * <p>
     * 
     * 
     */
    @SerializedName("roles")
    @Expose
    public List<UserRole> roles;
    /**
     * Email
     * <p>
     * The email of the user. If synced, will be unique inside the environment.
     * 
     */
    @SerializedName("email")
    @Expose
    public java.lang.String email;
    /**
     * First Name
     * <p>
     * First name of the user.
     * 
     */
    @SerializedName("first_name")
    @Expose
    public java.lang.String firstName;
    /**
     * Last Name
     * <p>
     * Last name of the user.
     * 
     */
    @SerializedName("last_name")
    @Expose
    public java.lang.String lastName;
    /**
     * Attributes
     * <p>
     * Arbitrary user attributes that will be used to enforce attribute-based access control policies.
     * 
     */
    @SerializedName("attributes")
    @Expose
    public HashMap<String, Object> attributes;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserRead__1() {
    }

    /**
     * 
     * @param organizationId
     * @param environmentId
     * @param id
     * @param projectId
     * @param key
     */
    public UserRead__1(java.lang.String key, java.lang.String id, java.lang.String organizationId, java.lang.String projectId, java.lang.String environmentId) {
        super();
        this.key = key;
        this.id = id;
        this.organizationId = organizationId;
        this.projectId = projectId;
        this.environmentId = environmentId;
    }

    public UserRead__1 withKey(java.lang.String key) {
        this.key = key;
        return this;
    }

    public UserRead__1 withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    public UserRead__1 withOrganizationId(java.lang.String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public UserRead__1 withProjectId(java.lang.String projectId) {
        this.projectId = projectId;
        return this;
    }

    public UserRead__1 withEnvironmentId(java.lang.String environmentId) {
        this.environmentId = environmentId;
        return this;
    }

    public UserRead__1 withAssociatedTenants(List<UserInTenant> associatedTenants) {
        this.associatedTenants = associatedTenants;
        return this;
    }

    public UserRead__1 withRoles(List<UserRole> roles) {
        this.roles = roles;
        return this;
    }

    public UserRead__1 withEmail(java.lang.String email) {
        this.email = email;
        return this;
    }

    public UserRead__1 withFirstName(java.lang.String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserRead__1 withLastName(java.lang.String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserRead__1 withAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

}
