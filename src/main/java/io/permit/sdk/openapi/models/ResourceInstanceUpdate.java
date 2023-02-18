
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * ResourceInstanceUpdate
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "attributes"
})
@Generated("jsonschema2pojo")
public class ResourceInstanceUpdate {

    /**
     * Attributes
     * <p>
     * Arbitraty resource attributes that will be used to enforce attribute-based access control policies.
     * 
     */
    @JsonProperty("attributes")
    @JsonPropertyDescription("Arbitraty resource attributes that will be used to enforce attribute-based access control policies.")
    public Attributes__5 attributes;

    public ResourceInstanceUpdate withAttributes(Attributes__5 attributes) {
        this.attributes = attributes;
        return this;
    }

}
