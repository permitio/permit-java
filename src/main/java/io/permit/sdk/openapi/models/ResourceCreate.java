
package io.permit.sdk.openapi.models;

import java.util.HashMap;
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
    public java.lang.String key;
    /**
     * Name
     * <p>
     * The name of the resource
     * (Required)
     * 
     */
    @SerializedName("name")
    @Expose
    public java.lang.String name;
    /**
     * Urn
     * <p>
     * The [URN](https://en.wikipedia.org/wiki/Uniform_Resource_Name) (Uniform Resource Name) of the resource
     * 
     */
    @SerializedName("urn")
    @Expose
    public java.lang.String urn;
    /**
     * Description
     * <p>
     * An optional longer description of what this resource respresents in your system
     * 
     */
    @SerializedName("description")
    @Expose
    public java.lang.String description;
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
    public HashMap<String, ActionBlockEditable> actions;
    /**
     * Attributes
     * <p>
     * Attributes that each resource of this type defines, and can be used in your ABAC policies.
     * 
     */
    @SerializedName("attributes")
    @Expose
    public HashMap<String, AttributeBlockEditable> attributes;

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
    public ResourceCreate(java.lang.String key, java.lang.String name, HashMap<String, ActionBlockEditable> actions) {
        super();
        this.key = key;
        this.name = name;
        this.actions = actions;
    }

    public ResourceCreate withKey(java.lang.String key) {
        this.key = key;
        return this;
    }

    public ResourceCreate withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    public ResourceCreate withUrn(java.lang.String urn) {
        this.urn = urn;
        return this;
    }

    public ResourceCreate withDescription(java.lang.String description) {
        this.description = description;
        return this;
    }

    public ResourceCreate withActions(HashMap<String, ActionBlockEditable> actions) {
        this.actions = actions;
        return this;
    }

    public ResourceCreate withAttributes(HashMap<String, AttributeBlockEditable> attributes) {
        this.attributes = attributes;
        return this;
    }

}
