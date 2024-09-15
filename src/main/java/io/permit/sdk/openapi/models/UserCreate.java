
package io.permit.sdk.openapi.models;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * UserCreate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class UserCreate {

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
     * Role Assignments
     * <p>
     * List of roles to assign to the user in the environment.
     * 
     */
    @SerializedName("role_assignments")
    @Expose
    public List<UserRoleCreate> roleAssignments;
    
    /**
     * No args constructor for use in serialization
     * 
     */
    public UserCreate() {
    }

    /**
     * 
     * @param key
     */
    public UserCreate(java.lang.String key) {
        super();
        this.key = key;
    }

    public UserCreate withKey(java.lang.String key) {
        this.key = key;
        return this;
    }

    public UserCreate withEmail(java.lang.String email) {
        this.email = email;
        return this;
    }

    public UserCreate withFirstName(java.lang.String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserCreate withLastName(java.lang.String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserCreate withAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    public UserCreate withRoleAssignments(List<UserRoleCreate> roleAssignments) {
        this.roleAssignments = roleAssignments;
        return this;
    }

}
