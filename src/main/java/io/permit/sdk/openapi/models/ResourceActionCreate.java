
package io.permit.sdk.openapi.models;

import java.util.HashMap;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ResourceActionCreate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ResourceActionCreate {

    /**
     * Key
     * <p>
     * A URL-friendly name of the action (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the action.
     * (Required)
     * 
     */
    @SerializedName("key")
    @Expose
    public String key;
    /**
     * Name
     * <p>
     * The name of the action
     * (Required)
     * 
     */
    @SerializedName("name")
    @Expose
    public String name;
    /**
     * Description
     * <p>
     * An optional longer description of what this action respresents in your system
     * 
     */
    @SerializedName("description")
    @Expose
    public String description;
    /**
     * Attributes
     * <p>
     * optional dictionary of key-value pairs that can be used to store arbitrary metadata about this action. This metadata can be used to filter actions using query parameters with attr_ prefix
     * 
     */
    @SerializedName("attributes")
    @Expose
    public HashMap<String, Object> attributes;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResourceActionCreate() {
    }

    /**
     * 
     * @param name
     * @param key
     */
    public ResourceActionCreate(String key, String name) {
        super();
        this.key = key;
        this.name = name;
    }

    public ResourceActionCreate withKey(String key) {
        this.key = key;
        return this;
    }

    public ResourceActionCreate withName(String name) {
        this.name = name;
        return this;
    }

    public ResourceActionCreate withDescription(String description) {
        this.description = description;
        return this;
    }

    public ResourceActionCreate withAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

}
