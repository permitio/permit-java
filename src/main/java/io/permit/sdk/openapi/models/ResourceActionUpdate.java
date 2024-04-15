
package io.permit.sdk.openapi.models;

import java.util.HashMap;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ResourceActionUpdate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ResourceActionUpdate {

    /**
     * Name
     * <p>
     * The name of the action
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

    public ResourceActionUpdate withName(String name) {
        this.name = name;
        return this;
    }

    public ResourceActionUpdate withDescription(String description) {
        this.description = description;
        return this;
    }

    public ResourceActionUpdate withAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

}
