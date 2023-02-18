
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * ResourceCreate
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "name",
    "urn",
    "description",
    "actions",
    "attributes"
})
@Generated("jsonschema2pojo")
public class ResourceCreate {

    /**
     * Key
     * <p>
     * A URL-friendly name of the resource (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the resource.
     * (Required)
     * 
     */
    @JsonProperty("key")
    @JsonPropertyDescription("A URL-friendly name of the resource (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the resource.")
    public String key;
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
    public Actions__1 actions;
    /**
     * Attributes
     * <p>
     * Attributes that each resource of this type defines, and can be used in your ABAC policies.
     * 
     */
    @JsonProperty("attributes")
    @JsonPropertyDescription("Attributes that each resource of this type defines, and can be used in your ABAC policies.")
    public Attributes__2 attributes;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResourceCreate() {
    }

    /**
     * 
     * @param name
     * @param actions
     * @param key
     */
    public ResourceCreate(String key, String name, Actions__1 actions) {
        super();
        this.key = key;
        this.name = name;
        this.actions = actions;
    }

    public ResourceCreate withKey(String key) {
        this.key = key;
        return this;
    }

    public ResourceCreate withName(String name) {
        this.name = name;
        return this;
    }

    public ResourceCreate withUrn(String urn) {
        this.urn = urn;
        return this;
    }

    public ResourceCreate withDescription(String description) {
        this.description = description;
        return this;
    }

    public ResourceCreate withActions(Actions__1 actions) {
        this.actions = actions;
        return this;
    }

    public ResourceCreate withAttributes(Attributes__2 attributes) {
        this.attributes = attributes;
        return this;
    }

}
