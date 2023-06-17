
package io.permit.sdk.openapi.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * DerivedRoleBlockRead
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class DerivedRoleBlockRead {

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
     * Id
     * <p>
     * The unique id of the derived_role
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    public String id;
    /**
     * Users With Role
     * <p>
     * the rules of the derived role
     * 
     */
    @SerializedName("users_with_role")
    @Expose
    public List<DerivedRoleRuleRead> usersWithRole;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DerivedRoleBlockRead() {
    }

    /**
     * 
     * @param id
     */
    public DerivedRoleBlockRead(String id) {
        super();
        this.id = id;
    }

    public DerivedRoleBlockRead withWhen(DerivedRoleSettings when) {
        this.when = when;
        return this;
    }

    public DerivedRoleBlockRead withId(String id) {
        this.id = id;
        return this;
    }

    public DerivedRoleBlockRead withUsersWithRole(List<DerivedRoleRuleRead> usersWithRole) {
        this.usersWithRole = usersWithRole;
        return this;
    }

}
