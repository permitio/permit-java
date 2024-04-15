
package io.permit.sdk.openapi.models;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ResourceActionGroupCreate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ResourceActionGroupCreate {

    /**
     * Key
     * <p>
     * A URL-friendly name of the action group (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the action group.
     * (Required)
     * 
     */
    @SerializedName("key")
    @Expose
    public java.lang.String key;
    /**
     * Name
     * <p>
     * The name of the action group
     * (Required)
     * 
     */
    @SerializedName("name")
    @Expose
    public java.lang.String name;
    /**
     * Description
     * <p>
     * An optional longer description of what this action group represents in your system
     * 
     */
    @SerializedName("description")
    @Expose
    public java.lang.String description;
    /**
     * Attributes
     * <p>
     * optional dictionary of key-value pairs that can be used to store arbitrary metadata about this action group. This metadata can be used to filter action groups using query parameters with attr_ prefix
     * 
     */
    @SerializedName("attributes")
    @Expose
    public HashMap<String, Object> attributes;
    /**
     * Actions
     * <p>
     * 
     * 
     */
    @SerializedName("actions")
    @Expose
    public List<java.lang.String> actions;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResourceActionGroupCreate() {
    }

    /**
     * 
     * @param name
     * @param key
     */
    public ResourceActionGroupCreate(java.lang.String key, java.lang.String name) {
        super();
        this.key = key;
        this.name = name;
    }

    public ResourceActionGroupCreate withKey(java.lang.String key) {
        this.key = key;
        return this;
    }

    public ResourceActionGroupCreate withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    public ResourceActionGroupCreate withDescription(java.lang.String description) {
        this.description = description;
        return this;
    }

    public ResourceActionGroupCreate withAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    public ResourceActionGroupCreate withActions(List<java.lang.String> actions) {
        this.actions = actions;
        return this;
    }

}
