
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * ResourceReplace
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "urn",
    "description",
    "actions",
    "attributes"
})
@Generated("jsonschema2pojo")
public class ResourceReplace {

    /**
     * Name
     * <p>
     * The name of the resource
     * (Required)
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("The name of the resource")
    public String name;
    /**
     * Urn
     * <p>
     * The [URN](https://en.wikipedia.org/wiki/Uniform_Resource_Name) (Uniform Resource Name) of the resource
     * 
     */
    @JsonProperty("urn")
    @JsonPropertyDescription("The [URN](https://en.wikipedia.org/wiki/Uniform_Resource_Name) (Uniform Resource Name) of the resource")
    public String urn;
    /**
     * Description
     * <p>
     * An optional longer description of what this resource respresents in your system
     * 
     */
    @JsonProperty("description")
    @JsonPropertyDescription("An optional longer description of what this resource respresents in your system")
    public String description;
    /**
     * Actions
     * <p>
     * 
     *         A actions definition block, typically contained within a resource type definition block.
     *         The actions represents the ways you can interact with a protected resource.
     *         
     * (Required)
     * 
     */
    @JsonProperty("actions")
    @JsonPropertyDescription("\n        A actions definition block, typically contained within a resource type definition block.\n        The actions represents the ways you can interact with a protected resource.\n        ")
    public Actions__3 actions;
    /**
     * Attributes
     * <p>
     * Attributes that each resource of this type defines, and can be used in your ABAC policies.
     * 
     */
    @JsonProperty("attributes")
    @JsonPropertyDescription("Attributes that each resource of this type defines, and can be used in your ABAC policies.")
    public Attributes__7 attributes;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResourceReplace() {
    }

    /**
     * 
     * @param name
     * @param actions
     */
    public ResourceReplace(String name, Actions__3 actions) {
        super();
        this.name = name;
        this.actions = actions;
    }

    public ResourceReplace withName(String name) {
        this.name = name;
        return this;
    }

    public ResourceReplace withUrn(String urn) {
        this.urn = urn;
        return this;
    }

    public ResourceReplace withDescription(String description) {
        this.description = description;
        return this;
    }

    public ResourceReplace withActions(Actions__3 actions) {
        this.actions = actions;
        return this;
    }

    public ResourceReplace withAttributes(Attributes__7 attributes) {
        this.attributes = attributes;
        return this;
    }

}
