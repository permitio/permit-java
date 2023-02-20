
package io.permit.sdk.openapi.models;

import java.util.HashMap;
import java.util.Map;
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
     * AttributeType
     * <p>
     * The type of the attribute, we currently support: `bool`, `number` (ints, floats), `time` (a timestamp), `string`, and `json`.
     * (Required)
     * 
     */
    @SerializedName("type")
    @Expose
    public AttributeBlockEditable.AttributeType type;
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
    public AttributeBlockEditable(AttributeBlockEditable.AttributeType type) {
        super();
        this.type = type;
    }

    public AttributeBlockEditable withType(AttributeBlockEditable.AttributeType type) {
        this.type = type;
        return this;
    }

    public AttributeBlockEditable withDescription(String description) {
        this.description = description;
        return this;
    }


    /**
     * AttributeType
     * <p>
     * The type of the attribute, we currently support: `bool`, `number` (ints, floats), `time` (a timestamp), `string`, and `json`.
     * 
     */
    @Generated("jsonschema2pojo")
    public enum AttributeType {

        @SerializedName("bool")
        BOOL("bool"),
        @SerializedName("number")
        NUMBER("number"),
        @SerializedName("string")
        STRING("string"),
        @SerializedName("time")
        TIME("time"),
        @SerializedName("array")
        ARRAY("array"),
        @SerializedName("json")
        JSON("json");
        private final String value;
        private final static Map<String, AttributeBlockEditable.AttributeType> CONSTANTS = new HashMap<String, AttributeBlockEditable.AttributeType>();

        static {
            for (AttributeBlockEditable.AttributeType c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        AttributeType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static AttributeBlockEditable.AttributeType fromValue(String value) {
            AttributeBlockEditable.AttributeType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
