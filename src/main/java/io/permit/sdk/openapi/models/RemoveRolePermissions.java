
package io.permit.sdk.openapi.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * RemoveRolePermissions
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class RemoveRolePermissions {

    /**
     * Permissions
     * <p>
     * List of permissions to remove from the role. If a permission is not found it is skipped. Each permission can be either a resource action id, or `{resource_key}:{action_key}`,i.e: the "permission name".
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
    public RemoveRolePermissions() {
    }

    /**
     * 
     * @param permissions
     */
    public RemoveRolePermissions(List<String> permissions) {
        super();
        this.permissions = permissions;
    }

    public RemoveRolePermissions withPermissions(List<String> permissions) {
        this.permissions = permissions;
        return this;
    }

}
