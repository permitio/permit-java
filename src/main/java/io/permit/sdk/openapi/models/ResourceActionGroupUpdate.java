
package io.permit.sdk.openapi.models;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ResourceActionGroupUpdate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ResourceActionGroupUpdate {

    /**
     * Name
     * <p>
     * The name of the action group
     * 
     */
    @SerializedName("name")
    @Expose
    public String name;
    /**
     * Description
     * <p>
     * An optional longer description of what this action group represents in your system
     * 
     */
    @SerializedName("description")
    @Expose
    public String description;
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
    public List<String> actions;

    public ResourceActionGroupUpdate withName(String name) {
        this.name = name;
        return this;
    }

    public ResourceActionGroupUpdate withDescription(String description) {
        this.description = description;
        return this;
    }

    public ResourceActionGroupUpdate withAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    public ResourceActionGroupUpdate withActions(List<String> actions) {
        this.actions = actions;
        return this;
    }

}
