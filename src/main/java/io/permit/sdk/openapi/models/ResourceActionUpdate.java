
package io.permit.sdk.openapi.models;

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

    public ResourceActionUpdate withName(String name) {
        this.name = name;
        return this;
    }

    public ResourceActionUpdate withDescription(String description) {
        this.description = description;
        return this;
    }

}
