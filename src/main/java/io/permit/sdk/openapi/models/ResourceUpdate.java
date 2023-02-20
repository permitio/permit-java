
package io.permit.sdk.openapi.models;

import java.util.HashMap;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ResourceUpdate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ResourceUpdate {

    /**
     * Name
     * <p>
     * The name of the resource
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

    public ResourceUpdate withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    public ResourceUpdate withUrn(java.lang.String urn) {
        this.urn = urn;
        return this;
    }

    public ResourceUpdate withDescription(java.lang.String description) {
        this.description = description;
        return this;
    }

    public ResourceUpdate withActions(HashMap<String, ActionBlockEditable> actions) {
        this.actions = actions;
        return this;
    }

    public ResourceUpdate withAttributes(HashMap<String, AttributeBlockEditable> attributes) {
        this.attributes = attributes;
        return this;
    }

}
