
package io.permit.sdk.openapi.models;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ResourceRoleCreate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ResourceRoleCreate {

    /**
     * Key
     * <p>
     * A URL-friendly name of the role (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the role.
     * (Required)
     * 
     */
    @SerializedName("key")
    @Expose
    public String key;
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
     * Extends
     * <p>
     * list of role keys that define what roles this role extends. In other words: this role will automatically inherit all the permissions of the given roles in this list.
     * 
     */
    @SerializedName("extends")
    @Expose
    public List<String> _extends;
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
    public ResourceRoleCreate() {
    }

    /**
     * 
     * @param name
     * @param key
     */
    public ResourceRoleCreate(String key, String name) {
        super();
        this.key = key;
        this.name = name;
    }

    public ResourceRoleCreate withKey(String key) {
        this.key = key;
        return this;
    }

    public ResourceRoleCreate withName(String name) {
        this.name = name;
        return this;
    }

    public ResourceRoleCreate withDescription(String description) {
        this.description = description;
        return this;
    }

    public ResourceRoleCreate withPermissions(List<String> permissions) {
        this.permissions = permissions;
        return this;
    }

    public ResourceRoleCreate withExtends(List<String> _extends) {
        this._extends = _extends;
        return this;
    }

    public ResourceRoleCreate withAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    public ResourceRoleCreate withGrantedTo(DerivedRoleBlockEdit grantedTo) {
        this.grantedTo = grantedTo;
        return this;
    }

}
