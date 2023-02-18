
package io.permit.sdk.openapi.models;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * ConditionSetType
 * <p>
 * An enumeration.
 * 
 */
@Generated("jsonschema2pojo")
public enum ConditionSetType {

    USERSET("userset"),
    RESOURCESET("resourceset");
    private final String value;
    private final static Map<String, ConditionSetType> CONSTANTS = new HashMap<String, ConditionSetType>();

    static {
        for (ConditionSetType c: values()) {
            CONSTANTS.put(c.value, c);
        }
    }

    ConditionSetType(String value) {
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
    public static ConditionSetType fromValue(String value) {
        ConditionSetType constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }

}
