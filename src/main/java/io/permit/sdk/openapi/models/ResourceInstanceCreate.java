
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * ResourceInstanceCreate
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "tenant",
    "resource",
    "attributes"
})
@Generated("jsonschema2pojo")
public class ResourceInstanceCreate {

    /**
     * Key
     * <p>
     * A unique identifier by which Permit will identify the resource instance for permission checks. You will later pass this identifier to the `permit.check()` API. A key can be anything: for example the resource db id, a url slug, a UUID or anything else as long as it's unique on your end. The resource instance key must be url-friendly.
     * (Required)
     * 
     */
    @JsonProperty("key")
    @JsonPropertyDescription("A unique identifier by which Permit will identify the resource instance for permission checks. You will later pass this identifier to the `permit.check()` API. A key can be anything: for example the resource db id, a url slug, a UUID or anything else as long as it's unique on your end. The resource instance key must be url-friendly.")
    public String key;
    /**
     * Tenant
     * <p>
     * the *key* of the tenant that this resource belongs to, used to enforce tenant boundaries in multi-tenant apps.
     * 
     */
    @JsonProperty("tenant")
    @JsonPropertyDescription("the *key* of the tenant that this resource belongs to, used to enforce tenant boundaries in multi-tenant apps.")
    public String tenant;
    /**
     * Resource
     * <p>
     * the *key* of the resource (type) of this resource instance. For example: if this resource instance is the annual budget document, the key of the resource might be `document`.
     * (Required)
     * 
     */
    @JsonProperty("resource")
    @JsonPropertyDescription("the *key* of the resource (type) of this resource instance. For example: if this resource instance is the annual budget document, the key of the resource might be `document`.")
    public String resource;
    /**
     * Attributes
     * <p>
     * Arbitraty resource attributes that will be used to enforce attribute-based access control policies.
     * 
     */
    @JsonProperty("attributes")
    @JsonPropertyDescription("Arbitraty resource attributes that will be used to enforce attribute-based access control policies.")
    public Attributes__3 attributes;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResourceInstanceCreate() {
    }

    /**
     * 
     * @param resource
     * @param key
     */
    public ResourceInstanceCreate(String key, String resource) {
        super();
        this.key = key;
        this.resource = resource;
    }

    public ResourceInstanceCreate withKey(String key) {
        this.key = key;
        return this;
    }

    public ResourceInstanceCreate withTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    public ResourceInstanceCreate withResource(String resource) {
        this.resource = resource;
        return this;
    }

    public ResourceInstanceCreate withAttributes(Attributes__3 attributes) {
        this.attributes = attributes;
        return this;
    }

}
