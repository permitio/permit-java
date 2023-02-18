
package io.permit.sdk.openapi.models;

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
     * 
     */
    @SerializedName("actions")
    @Expose
    public Actions__4 actions;
    /**
     * Attributes
     * <p>
     * Attributes that each resource of this type defines, and can be used in your ABAC policies.
     * 
     */
    @SerializedName("attributes")
    @Expose
    public Attributes__8 attributes;

    public ResourceUpdate withName(String name) {
        this.name = name;
        return this;
    }

    public ResourceUpdate withUrn(String urn) {
        this.urn = urn;
        return this;
    }

    public ResourceUpdate withDescription(String description) {
        this.description = description;
        return this;
    }

    public ResourceUpdate withActions(Actions__4 actions) {
        this.actions = actions;
        return this;
    }

    public ResourceUpdate withAttributes(Attributes__8 attributes) {
        this.attributes = attributes;
        return this;
    }

}
