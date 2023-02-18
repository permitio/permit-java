
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * TenantCreate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class TenantCreate {

    /**
     * Key
     * <p>
     * A unique id by which Permit will identify the tenant. The tenant key must be url-friendly (slugified).
     * (Required)
     * 
     */
    @SerializedName("key")
    @Expose
    public String key;
    /**
     * Name
     * <p>
     * A descriptive name for the tenant
     * (Required)
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
    public Attributes__9 attributes;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TenantCreate() {
    }

    /**
     * 
     * @param name
     * @param key
     */
    public TenantCreate(String key, String name) {
        super();
        this.key = key;
        this.name = name;
    }

    public TenantCreate withKey(String key) {
        this.key = key;
        return this;
    }

    public TenantCreate withName(String name) {
        this.name = name;
        return this;
    }

    public TenantCreate withDescription(String description) {
        this.description = description;
        return this;
    }

    public TenantCreate withAttributes(Attributes__9 attributes) {
        this.attributes = attributes;
        return this;
    }

}
