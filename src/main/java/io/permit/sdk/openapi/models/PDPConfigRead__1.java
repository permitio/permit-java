
package io.permit.sdk.openapi.models;

import java.util.UUID;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * PDPConfigRead
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "organization_id",
    "project_id",
    "environment_id",
    "client_secret"
})
@Generated("jsonschema2pojo")
public class PDPConfigRead__1 {

    /**
     * Id
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("id")
    public UUID id;
    /**
     * Name
     * <p>
     * 
     * 
     */
    @JsonProperty("name")
    public String name;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the pdp_config belongs to.
     * (Required)
     * 
     */
    @JsonProperty("organization_id")
    @JsonPropertyDescription("Unique id of the organization that the pdp_config belongs to.")
    public UUID organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the pdp_config belongs to.
     * (Required)
     * 
     */
    @JsonProperty("project_id")
    @JsonPropertyDescription("Unique id of the project that the pdp_config belongs to.")
    public UUID projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the pdp_config belongs to.
     * (Required)
     * 
     */
    @JsonProperty("environment_id")
    @JsonPropertyDescription("Unique id of the environment that the pdp_config belongs to.")
    public UUID environmentId;
    /**
     * Client Secret
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("client_secret")
    public String clientSecret;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PDPConfigRead__1() {
    }

    /**
     * 
     * @param organizationId
     * @param environmentId
     * @param clientSecret
     * @param id
     * @param projectId
     */
    public PDPConfigRead__1(UUID id, UUID organizationId, UUID projectId, UUID environmentId, String clientSecret) {
        super();
        this.id = id;
        this.organizationId = organizationId;
        this.projectId = projectId;
        this.environmentId = environmentId;
        this.clientSecret = clientSecret;
    }

    public PDPConfigRead__1 withId(UUID id) {
        this.id = id;
        return this;
    }

    public PDPConfigRead__1 withName(String name) {
        this.name = name;
        return this;
    }

    public PDPConfigRead__1 withOrganizationId(UUID organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public PDPConfigRead__1 withProjectId(UUID projectId) {
        this.projectId = projectId;
        return this;
    }

    public PDPConfigRead__1 withEnvironmentId(UUID environmentId) {
        this.environmentId = environmentId;
        return this;
    }

    public PDPConfigRead__1 withClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

}
