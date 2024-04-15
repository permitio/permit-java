
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ResourceInstanceUpdate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ResourceInstanceUpdate {

    /**
     * Attributes
     * <p>
     * Arbitraty resource attributes that will be used to enforce attribute-based access control policies.
     * 
     */
    @SerializedName("attributes")
    @Expose
    public Attributes attributes;

    public ResourceInstanceUpdate withAttributes(Attributes attributes) {
        this.attributes = attributes;
        return this;
    }

}
