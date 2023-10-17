package io.permit.sdk.enforcement;

import io.permit.sdk.util.Context;

import java.util.HashMap;

/**
 * The {@code ResourceDetails} class represents a single resource instance information fetched from the PDP (key and attributes).
 */
public final class ResourceDetails extends TenantDetails {
    public final String type;
    public ResourceDetails(String type, String key, HashMap<String, Object> attributes) {
        super(key, attributes);
        this.type = type;
    }
}