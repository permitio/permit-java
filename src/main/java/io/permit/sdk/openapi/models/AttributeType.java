
package io.permit.sdk.openapi.models;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.SerializedName;


/**
 * AttributeType
 * <p>
 * supported attribute primitives
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
    private final static Map<String, AttributeType> CONSTANTS = new HashMap<String, AttributeType>();

    static {
        for (AttributeType c: values()) {
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

    public static AttributeType fromValue(String value) {
        AttributeType constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }

}
