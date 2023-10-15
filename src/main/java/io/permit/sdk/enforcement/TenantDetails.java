package io.permit.sdk.enforcement;

import io.permit.sdk.util.Context;

import java.util.HashMap;

/**
 * The {@code TenantDetails} class represents a single tenant information fetched from the PDP (key and attributes).
 */
public final class TenantDetails {
    public final String key;
    public final HashMap<String, Object> attributes;
    public TenantDetails(String key, HashMap<String, Object> attributes) {
        this.key = key;
        this.attributes = attributes;
    }
}