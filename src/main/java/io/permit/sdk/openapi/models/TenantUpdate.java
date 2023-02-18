
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
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
    public String name;
    /**
     * Description
     * <p>
     * an optional longer description of the tenant
     * 
     */
    @SerializedName("description")
    @Expose
    public String description;
    /**
     * Attributes
     * <p>
     * Arbitraty tenant attributes that will be used to enforce attribute-based access control policies.
     * 
     */
    @SerializedName("attributes")
    @Expose
    public Attributes__11 attributes;

    public TenantUpdate withName(String name) {
        this.name = name;
        return this;
    }

    public TenantUpdate withDescription(String description) {
        this.description = description;
        return this;
    }

    public TenantUpdate withAttributes(Attributes__11 attributes) {
        this.attributes = attributes;
        return this;
    }

}
