package io.permit.sdk;

/**
 * The {@code FactsSyncTimeoutPolicy} enum represents the policy options for
 * handling facts synchronization timeouts when proxy_facts_via_pdp is enabled.
 */
public enum FactsSyncTimeoutPolicy {
    /**
     * Continue the request without waiting for facts sync to complete
     */
    IGNORE("ignore"),
    
    /**
     * Fail the request if facts sync does not complete within the timeout
     */
    FAIL("fail");
    
    private final String value;
    
    FactsSyncTimeoutPolicy(String value) {
        this.value = value;
    }
    
    /**
     * Returns the string value of the enum.
     *
     * @return The string value.
     */
    public String getValue() {
        return value;
    }
    
    /**
     * Returns the enum constant of this type with the specified string value.
     *
     * @param value The string value to convert.
     * @return The enum constant with the specified string value.
     * @throws IllegalArgumentException If the given value is not a valid policy value.
     */
    public static FactsSyncTimeoutPolicy fromString(String value) {
        for (FactsSyncTimeoutPolicy policy : FactsSyncTimeoutPolicy.values()) {
            if (policy.value.equalsIgnoreCase(value)) {
                return policy;
            }
        }
        throw new IllegalArgumentException("Unknown policy value: " + value);
    }
} 