
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
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
    public Attributes__5 attributes;

    public ResourceInstanceUpdate withAttributes(Attributes__5 attributes) {
        this.attributes = attributes;
        return this;
    }

}
