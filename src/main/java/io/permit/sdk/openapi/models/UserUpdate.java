
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * UserUpdate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class UserUpdate {

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
    public Attributes__14 attributes;

    public UserUpdate withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserUpdate withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserUpdate withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserUpdate withAttributes(Attributes__14 attributes) {
        this.attributes = attributes;
        return this;
    }

}
