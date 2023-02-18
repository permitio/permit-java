
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
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
    public String key;
    /**
     * Email
     * <p>
     * The email of the user. If synced, will be unique inside the environment.
     * 
     */
    @SerializedName("email")
    @Expose
    public String email;
    /**
     * First Name
     * <p>
     * First name of the user.
     * 
     */
    @SerializedName("first_name")
    @Expose
    public String firstName;
    /**
     * Last Name
     * <p>
     * Last name of the user.
     * 
     */
    @SerializedName("last_name")
    @Expose
    public String lastName;
    /**
     * Attributes
     * <p>
     * Arbitrary user attributes that will be used to enforce attribute-based access control policies.
     * 
     */
    @SerializedName("attributes")
    @Expose
    public Attributes__12 attributes;

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
    public UserCreate(String key) {
        super();
        this.key = key;
    }

    public UserCreate withKey(String key) {
        this.key = key;
        return this;
    }

    public UserCreate withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserCreate withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserCreate withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserCreate withAttributes(Attributes__12 attributes) {
        this.attributes = attributes;
        return this;
    }

}
