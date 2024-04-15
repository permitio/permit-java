
package io.permit.sdk.openapi.models;

import java.util.HashMap;
import javax.annotation.Generated;
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

    /**
     * Attributes
     * <p>
     * Arbitrary action attributes that can be used for filtering or enforcement of attribute-based access control policies.
     * 
     */
    @SerializedName("attributes")
    @Expose
    public HashMap<String, Object> attributes;

    public ActionBlockEditable withName(String name) {
        this.name = name;
        return this;
    }

    public ActionBlockEditable withDescription(String description) {
        this.description = description;
        return this;
    }

    public ActionBlockEditable withAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

}
