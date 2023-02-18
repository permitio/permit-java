
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * AttributeBlockRead
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class AttributeBlockRead {

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
     * Id
     * <p>
     * Unique id of the attribute
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    public String id;
    /**
     * Key
     * <p>
     * action key
     * 
     */
    @SerializedName("key")
    @Expose
    public String key;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AttributeBlockRead() {
    }

    /**
     * 
     * @param id
     * @param type
     */
    public AttributeBlockRead(Object type, String id) {
        super();
        this.type = type;
        this.id = id;
    }

    public AttributeBlockRead withType(Object type) {
        this.type = type;
        return this;
    }

    public AttributeBlockRead withDescription(String description) {
        this.description = description;
        return this;
    }

    public AttributeBlockRead withId(String id) {
        this.id = id;
        return this;
    }

    public AttributeBlockRead withKey(String key) {
        this.key = key;
        return this;
    }

}
