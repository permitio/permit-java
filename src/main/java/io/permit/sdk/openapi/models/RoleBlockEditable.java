
package io.permit.sdk.openapi.models;

import java.util.List;
import java.util.HashMap;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * RoleBlockEditable
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class RoleBlockEditable {

    /**
     * Name
     * <p>
     * The name of the role
     * (Required)
     * 
     */
    @SerializedName("name")
    @Expose
    public String name;
    /**
     * Description
     * <p>
     * optional description string explaining what this role represents, or what permissions are granted to it.
     * 
     */
    @SerializedName("description")
    @Expose
    public String description;
    /**
     * Permissions
     * <p>
     * list of action keys that define what actions this resource role is permitted to do
     * 
     */
    @SerializedName("permissions")
    @Expose
    public List<String> permissions;
    /**
     * Attributes
     * <p>
     * optional dictionary of key-value pairs that can be used to store arbitrary metadata about this role. This metadata can be used to filter role using query parameters with attr_ prefix, currently supports only 'equals' operator
     * 
     */
    @SerializedName("attributes")
    @Expose
    public HashMap<String, Object> attributes;
    /**
     * Granted To
     * <p>
     * Derived role that inherit will be applied on this role
     * 
     */
    @SerializedName("granted_to")
    @Expose
    public DerivedRoleBlockEdit grantedTo;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RoleBlockEditable() {
    }

    /**
     * 
     * @param name
     */
    public RoleBlockEditable(String name) {
        super();
        this.name = name;
    }

    public RoleBlockEditable withName(String name) {
        this.name = name;
        return this;
    }

    public RoleBlockEditable withDescription(String description) {
        this.description = description;
        return this;
    }

    public RoleBlockEditable withPermissions(List<String> permissions) {
        this.permissions = permissions;
        return this;
    }

    public RoleBlockEditable withAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    public RoleBlockEditable withGrantedTo(DerivedRoleBlockEdit grantedTo) {
        this.grantedTo = grantedTo;
        return this;
    }

}
