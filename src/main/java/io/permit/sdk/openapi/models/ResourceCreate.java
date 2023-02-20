
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ResourceCreate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ResourceCreate {

    /**
     * Key
     * <p>
     * A URL-friendly name of the resource (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the resource.
     * (Required)
     * 
     */
    @SerializedName("key")
    @Expose
    public String key;
    /**
     * Name
     * <p>
     * The name of the resource
     * (Required)
     * 
     */
    @SerializedName("name")
    @Expose
    public String name;
    /**
     * Urn
     * <p>
     * The [URN](https://en.wikipedia.org/wiki/Uniform_Resource_Name) (Uniform Resource Name) of the resource
     * 
     */
    @SerializedName("urn")
    @Expose
    public String urn;
    /**
     * Description
     * <p>
     * An optional longer description of what this resource respresents in your system
     * 
     */
    @SerializedName("description")
    @Expose
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
    @SerializedName("actions")
    @Expose
    public ActionBlockEditable actions;
    /**
     * Attributes
     * <p>
     * Attributes that each resource of this type defines, and can be used in your ABAC policies.
     * 
     */
    @SerializedName("attributes")
    @Expose
    public AttributeBlockEditable attributes;

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
    public ResourceCreate(String key, String name, ActionBlockEditable actions) {
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

    public ResourceCreate withActions(ActionBlockEditable actions) {
        this.actions = actions;
        return this;
    }

    public ResourceCreate withAttributes(AttributeBlockEditable attributes) {
        this.attributes = attributes;
        return this;
    }

}
