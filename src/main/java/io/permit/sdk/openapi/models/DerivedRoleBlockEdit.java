
package io.permit.sdk.openapi.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * DerivedRoleBlockEdit
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class DerivedRoleBlockEdit {

    /**
     * When
     * <p>
     * the settings of the derived role
     * 
     */
    @SerializedName("when")
    @Expose
    public DerivedRoleSettings when;
    /**
     * Users With Role
     * <p>
     * the rules of the derived role
     * 
     */
    @SerializedName("users_with_role")
    @Expose
    public List<DerivedRoleRuleCreate> usersWithRole;

    public DerivedRoleBlockEdit withWhen(DerivedRoleSettings when) {
        this.when = when;
        return this;
    }

    public DerivedRoleBlockEdit withUsersWithRole(List<DerivedRoleRuleCreate> usersWithRole) {
        this.usersWithRole = usersWithRole;
        return this;
    }

}
