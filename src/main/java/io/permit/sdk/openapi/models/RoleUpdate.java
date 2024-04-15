
package io.permit.sdk.openapi.models;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * RoleUpdate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class RoleUpdate {

    /**
     * Name
     * <p>
     * The name of the role
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
     * Extends
     * <p>
     * list of role keys that define what roles this role extends. In other words: this role will automatically inherit all the permissions of the given roles in this list.
     * 
     */
    @SerializedName("extends")
    @Expose
    public List<String> _extends;
    /**
     * Granted To
     * <p>
     * Derived role that inherit will be applied on this role
     * 
     */
    @SerializedName("granted_to")
    @Expose
    public DerivedRoleBlockEdit grantedTo;

    public RoleUpdate withName(String name) {
        this.name = name;
        return this;
    }

    public RoleUpdate withDescription(String description) {
        this.description = description;
        return this;
    }

    public RoleUpdate withPermissions(List<String> permissions) {
        this.permissions = permissions;
        return this;
    }

    public RoleUpdate withAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    public RoleUpdate withExtends(List<String> _extends) {
        this._extends = _extends;
        return this;
    }

    public RoleUpdate withGrantedTo(DerivedRoleBlockEdit grantedTo) {
        this.grantedTo = grantedTo;
        return this;
    }

}
