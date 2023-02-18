
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * TenantUpdate
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "description",
    "attributes"
})
@Generated("jsonschema2pojo")
public class TenantUpdate {

    /**
     * Name
     * <p>
     * A descriptive name for the tenant
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("A descriptive name for the tenant")
    public String name;
    /**
     * Description
     * <p>
     * an optional longer description of the tenant
     * 
     */
    @JsonProperty("description")
    @JsonPropertyDescription("an optional longer description of the tenant")
    public String description;
    /**
     * Attributes
     * <p>
     * Arbitraty tenant attributes that will be used to enforce attribute-based access control policies.
     * 
     */
    @JsonProperty("attributes")
    @JsonPropertyDescription("Arbitraty tenant attributes that will be used to enforce attribute-based access control policies.")
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
