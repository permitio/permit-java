
package io.permit.sdk.openapi.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * RoleBlock
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class RoleBlock {

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
    public List<String> permissions = new ArrayList<String>();
    /**
     * Extends
     * <p>
     * list of role keys that define what roles this role extends. In other words: this role will automatically inherit all the permissions of the given roles in this list.
     * 
     */
    @SerializedName("extends")
    @Expose
    public List<String> _extends = new ArrayList<String>();

    public RoleBlock withDescription(String description) {
        this.description = description;
        return this;
    }

    public RoleBlock withPermissions(List<String> permissions) {
        this.permissions = permissions;
        return this;
    }

    public RoleBlock withExtends(List<String> _extends) {
        this._extends = _extends;
        return this;
    }

}
