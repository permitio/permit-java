
package io.permit.sdk.openapi.models;

import java.util.HashMap;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * TenantUpdate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class TenantUpdate {

    /**
     * Name
     * <p>
     * A descriptive name for the tenant
     * 
     */
    @SerializedName("name")
    @Expose
    public java.lang.String name;
    /**
     * Description
     * <p>
     * an optional longer description of the tenant
     * 
     */
    @SerializedName("description")
    @Expose
    public java.lang.String description;
    /**
     * Attributes
     * <p>
     * Arbitraty tenant attributes that will be used to enforce attribute-based access control policies.
     * 
     */
    @SerializedName("attributes")
    @Expose
    public HashMap<String, Object> attributes;

    public TenantUpdate withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    public TenantUpdate withDescription(java.lang.String description) {
        this.description = description;
        return this;
    }

    public TenantUpdate withAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

}
