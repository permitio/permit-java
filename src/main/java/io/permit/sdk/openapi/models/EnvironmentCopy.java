
package io.permit.sdk.openapi.models;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * EnvironmentCopy
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class EnvironmentCopy {

    /**
     * Target Env
     * <p>
     * If copying a new environment, the environment configuration. If copying to an existing environment, the environment identifier
     * (Required)
     * 
     */
    @SerializedName("target_env")
    @Expose
    public EnvironmentCopyTarget targetEnv;
    /**
     * Conflict Strategy
     * <p>
     * Action to take when detecting a conflict when copying. Only applies to copying into an existing environment
     * 
     */
    @SerializedName("conflict_strategy")
    @Expose
    public EnvironmentCopy.ConflictStrategy conflictStrategy = EnvironmentCopy.ConflictStrategy.fromValue("fail");
    /**
     * Scope
     * <p>
     * Filters to include and exclude copied objects
     * 
     */
    @SerializedName("scope")
    @Expose
    public EnvironmentCopyScope scope;

    /**
     * No args constructor for use in serialization
     * 
     */
    public EnvironmentCopy() {
    }

    /**
     * 
     * @param targetEnv
     */
    public EnvironmentCopy(EnvironmentCopyTarget targetEnv) {
        super();
        this.targetEnv = targetEnv;
    }

    public EnvironmentCopy withTargetEnv(EnvironmentCopyTarget targetEnv) {
        this.targetEnv = targetEnv;
        return this;
    }

    public EnvironmentCopy withConflictStrategy(EnvironmentCopy.ConflictStrategy conflictStrategy) {
        this.conflictStrategy = conflictStrategy;
        return this;
    }

    public EnvironmentCopy withScope(EnvironmentCopyScope scope) {
        this.scope = scope;
        return this;
    }


    /**
     * Conflict Strategy
     * <p>
     * Action to take when detecting a conflict when copying. Only applies to copying into an existing environment
     * 
     */
    @Generated("jsonschema2pojo")
    public enum ConflictStrategy {

        @SerializedName("fail")
        FAIL("fail"),
        @SerializedName("overwrite")
        OVERWRITE("overwrite");
        private final String value;
        private final static Map<String, EnvironmentCopy.ConflictStrategy> CONSTANTS = new HashMap<String, EnvironmentCopy.ConflictStrategy>();

        static {
            for (EnvironmentCopy.ConflictStrategy c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        ConflictStrategy(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static EnvironmentCopy.ConflictStrategy fromValue(String value) {
            EnvironmentCopy.ConflictStrategy constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
