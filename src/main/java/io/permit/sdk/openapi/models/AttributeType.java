
package io.permit.sdk.openapi.models;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * AttributeType
 * <p>
 * supported attribute primitives
 * 
 */
@Generated("jsonschema2pojo")
public enum AttributeType {

    BOOL("bool"),
    NUMBER("number"),
    STRING("string"),
    TIME("time"),
    ARRAY("array"),
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

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static AttributeType fromValue(String value) {
        AttributeType constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }

}
