
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * UserCreate
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "email",
    "first_name",
    "last_name",
    "attributes"
})
@Generated("jsonschema2pojo")
public class UserCreate {

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
