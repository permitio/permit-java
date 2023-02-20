
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
     * 
     * (Required)
     * 
     */
    @SerializedName("type")
    @Expose
    public AttributeType type;
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
    public AttributeBlockEditable(AttributeType type) {
        super();
        this.type = type;
    }

    public AttributeBlockEditable withType(AttributeType type) {
        this.type = type;
        return this;
    }

    public AttributeBlockEditable withDescription(String description) {
        this.description = description;
        return this;
    }

}
