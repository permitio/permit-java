
package io.permit.sdk.openapi.models;

import java.util.HashMap;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ResourceReplace
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ResourceReplace {

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
     * Roles
     * <p>
     * Roles defined on this resource. The key is the role name, and the value contains the role properties such as granted permissions, base roles, etc.
     * 
     */
    @SerializedName("roles")
    @Expose
    public HashMap<String, RoleBlockEditable> roles;
    /**
     * Relations
     * <p>
     * Relations to other resources. The key is the relation key, and the value is the related resource.
     * 
     */
    @SerializedName("relations")
    @Expose
    public HashMap<String, String> relations;

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
    public ResourceReplace(java.lang.String name, HashMap<String, ActionBlockEditable> actions) {
        super();
        this.name = name;
        this.actions = actions;
    }

    public ResourceReplace withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    public ResourceReplace withUrn(java.lang.String urn) {
        this.urn = urn;
        return this;
    }

    public ResourceReplace withDescription(java.lang.String description) {
        this.description = description;
        return this;
    }

    public ResourceReplace withActions(HashMap<String, ActionBlockEditable> actions) {
        this.actions = actions;
        return this;
    }

    public ResourceReplace withAttributes(HashMap<String, AttributeBlockEditable> attributes) {
        this.attributes = attributes;
        return this;
    }

    public ResourceReplace withRoles(HashMap<String, RoleBlockEditable> roles) {
        this.roles = roles;
        return this;
    }

    public ResourceReplace withRelations(HashMap<String, String> relations) {
        this.relations = relations;
        return this;
    }

}
