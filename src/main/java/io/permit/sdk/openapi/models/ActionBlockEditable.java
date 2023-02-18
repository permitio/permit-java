
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ActionBlockEditable
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ActionBlockEditable {

    /**
     * Name
     * <p>
     * a more descriptive name for the action
     * 
     */
    @SerializedName("name")
    @Expose
    public String name;
    /**
     * Description
     * <p>
     * optional description string explaining what this action represents in your system
     * 
     */
    @SerializedName("description")
    @Expose
    public String description;

    public ActionBlockEditable withName(String name) {
        this.name = name;
        return this;
    }

    public ActionBlockEditable withDescription(String description) {
        this.description = description;
        return this;
    }

}
