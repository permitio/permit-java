
package io.permit.sdk.openapi.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * AddRolePermissions
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class AddRolePermissions {

    /**
     * Permissions
     * <p>
     * List of permissions to assign to the role. If a permission is already granted to the role it is skipped. Each permission can be either a resource action id, or `{resource_key}:{action_key}`, i.e: the "permission name".
     * (Required)
     * 
     */
    @SerializedName("permissions")
    @Expose
    public List<String> permissions;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AddRolePermissions() {
    }

    /**
     * 
     * @param permissions
     */
    public AddRolePermissions(List<String> permissions) {
        super();
        this.permissions = permissions;
    }

    public AddRolePermissions withPermissions(List<String> permissions) {
        this.permissions = permissions;
        return this;
    }

}
