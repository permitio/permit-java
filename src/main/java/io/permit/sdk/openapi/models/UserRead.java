
package io.permit.sdk.openapi.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * UserRead
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "id",
    "organization_id",
    "project_id",
    "environment_id",
    "associated_tenants",
    "roles",
    "email",
    "first_name",
    "last_name",
    "attributes"
})
@Generated("jsonschema2pojo")
public class UserRead {

    /**
     * Key
     * <p>
     * A unique id by which Permit will identify the user for permission checks.
     * (Required)
     * 
     */
    @JsonProperty("key")
    @JsonPropertyDescription("A unique id by which Permit will identify the user for permission checks.")
    public String key;
    /**
     * Id
     * <p>
     * Unique id of the user
     * (Required)
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("Unique id of the user")
    public UUID id;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the user belongs to.
     * (Required)
     * 
     */
    @JsonProperty("organization_id")
    @JsonPropertyDescription("Unique id of the organization that the user belongs to.")
    public UUID organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the user belongs to.
     * (Required)
     * 
     */
    @JsonProperty("project_id")
    @JsonPropertyDescription("Unique id of the project that the user belongs to.")
    public UUID projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the user belongs to.
     * (Required)
     * 
     */
    @JsonProperty("environment_id")
    @JsonPropertyDescription("Unique id of the environment that the user belongs to.")
    public UUID environmentId;
    /**
     * Associated Tenants
     * <p>
     * 
     * 
     */
    @JsonProperty("associated_tenants")
    public List<UserInTenant> associatedTenants = new ArrayList<UserInTenant>();
    /**
     * Roles
     * <p>
     * 
     * 
     */
    @JsonProperty("roles")
    public List<UserRole> roles = new ArrayList<UserRole>();
    /**
     * Email
     * <p>
     * The email of the user. If synced, will be unique inside the environment.
     * 
     */
    @JsonProperty("email")
    @JsonPropertyDescription("The email of the user. If synced, will be unique inside the environment.")
    public String email;
    /**
     * First Name
     * <p>
     * First name of the user.
     * 
     */
    @JsonProperty("first_name")
    @JsonPropertyDescription("First name of the user.")
    public String firstName;
    /**
     * Last Name
     * <p>
     * Last name of the user.
     * 
     */
    @JsonProperty("last_name")
    @JsonPropertyDescription("Last name of the user.")
    public String lastName;
    /**
     * Attributes
     * <p>
     * Arbitrary user attributes that will be used to enforce attribute-based access control policies.
     * 
     */
    @JsonProperty("attributes")
    @JsonPropertyDescription("Arbitrary user attributes that will be used to enforce attribute-based access control policies.")
    public Attributes__1 attributes;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserRead() {
    }

    /**
     * 
     * @param organizationId
     * @param environmentId
     * @param id
     * @param projectId
     * @param key
     */
    public UserRead(String key, UUID id, UUID organizationId, UUID projectId, UUID environmentId) {
        super();
        this.key = key;
        this.id = id;
        this.organizationId = organizationId;
        this.projectId = projectId;
        this.environmentId = environmentId;
    }

    public UserRead withKey(String key) {
        this.key = key;
        return this;
    }

    public UserRead withId(UUID id) {
        this.id = id;
        return this;
    }

    public UserRead withOrganizationId(UUID organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public UserRead withProjectId(UUID projectId) {
        this.projectId = projectId;
        return this;
    }

    public UserRead withEnvironmentId(UUID environmentId) {
        this.environmentId = environmentId;
        return this;
    }

    public UserRead withAssociatedTenants(List<UserInTenant> associatedTenants) {
        this.associatedTenants = associatedTenants;
        return this;
    }

    public UserRead withRoles(List<UserRole> roles) {
        this.roles = roles;
        return this;
    }

    public UserRead withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserRead withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserRead withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserRead withAttributes(Attributes__1 attributes) {
        this.attributes = attributes;
        return this;
    }

}
