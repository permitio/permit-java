
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * AttributeBlockEditable
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class AttributeBlockEditable {

    /**
     * The type of the attribute, we currently support: `bool`, `number` (ints, floats), `time` (a timestamp), `string`, and `json`.
     * (Required)
     * 
     */
    @SerializedName("type")
    @Expose
    public Object type;
    /**
     * Description
     * <p>
     * optional description string explaining what data this attribute will store
     * 
     */
    @SerializedName("description")
    @Expose
    public String description;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AttributeBlockEditable() {
    }

    /**
     * 
     * @param type
     */
    public AttributeBlockEditable(Object type) {
        super();
        this.type = type;
    }

    public AttributeBlockEditable withType(Object type) {
        this.type = type;
        return this;
    }

    public AttributeBlockEditable withDescription(String description) {
        this.description = description;
        return this;
    }

}
