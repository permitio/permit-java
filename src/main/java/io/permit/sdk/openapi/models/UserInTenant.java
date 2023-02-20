
package io.permit.sdk.openapi.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * UserInTenant
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class UserInTenant {

    /**
     * Tenant
     * <p>
     * The tenant key which the user is associated with
     * (Required)
     * 
     */
    @SerializedName("tenant")
    @Expose
    public String tenant;
    /**
     * Roles
     * <p>
     * List of roles assigned to the user in that tenant
     * (Required)
     * 
     */
    @SerializedName("roles")
    @Expose
    public List<String> roles;
    /**
     * UserStatus
     * <p>
     * Whether the user has signed in or not
     * (Required)
     * 
     */
    @SerializedName("status")
    @Expose
    public UserInTenant.UserStatus status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserInTenant() {
    }

    /**
     * 
     * @param roles
     * @param tenant
     * @param status
     */
    public UserInTenant(String tenant, List<String> roles, UserInTenant.UserStatus status) {
        super();
        this.tenant = tenant;
        this.roles = roles;
        this.status = status;
    }

    public UserInTenant withTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    public UserInTenant withRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public UserInTenant withStatus(UserInTenant.UserStatus status) {
        this.status = status;
        return this;
    }


    /**
     * UserStatus
     * <p>
     * Whether the user has signed in or not
     * 
     */
    @Generated("jsonschema2pojo")
    public enum UserStatus {

        @SerializedName("active")
        ACTIVE("active"),
        @SerializedName("pending")
        PENDING("pending");
        private final String value;
        private final static Map<String, UserInTenant.UserStatus> CONSTANTS = new HashMap<String, UserInTenant.UserStatus>();

        static {
            for (UserInTenant.UserStatus c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        UserStatus(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static UserInTenant.UserStatus fromValue(String value) {
            UserInTenant.UserStatus constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
