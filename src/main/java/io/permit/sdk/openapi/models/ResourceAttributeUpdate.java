
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ResourceAttributeUpdate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ResourceAttributeUpdate {

    /**
     * The type of the attribute, we currently support: `bool`, `number` (ints, floats), `time` (a timestamp), `string`, and `json`.
     * 
     */
    @SerializedName("type")
    @Expose
    public Object type;
    /**
     * Description
     * <p>
     * An optional longer description of what this attribute respresents in your system
     * 
     */
    @SerializedName("description")
    @Expose
    public String description;

    public ResourceAttributeUpdate withType(Object type) {
        this.type = type;
        return this;
    }

    public ResourceAttributeUpdate withDescription(String description) {
        this.description = description;
        return this;
    }

}
