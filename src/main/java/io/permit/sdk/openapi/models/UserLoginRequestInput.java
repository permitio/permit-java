
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * UserLoginRequestInput
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class UserLoginRequestInput {

    /**
     * User Id
     * <p>
     * ID or key of the user for whom to generate a token
     * (Required)
     * 
     */
    @SerializedName("user_id")
    @Expose
    public String userId;
    /**
     * Tenant Id
     * <p>
     * ID or key of the tenant to which access is requested
     * (Required)
     * 
     */
    @SerializedName("tenant_id")
    @Expose
    public String tenantId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserLoginRequestInput() {
    }

    /**
     * 
     * @param tenantId
     * @param userId
     */
    public UserLoginRequestInput(String userId, String tenantId) {
        super();
        this.userId = userId;
        this.tenantId = tenantId;
    }

    public UserLoginRequestInput withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public UserLoginRequestInput withTenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

}
