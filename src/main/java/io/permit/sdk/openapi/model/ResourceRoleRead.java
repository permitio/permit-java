/*
 * Permit.io API
 *  Authorization as a service 
 *
 * The version of the OpenAPI document: 2.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package io.permit.sdk.openapi.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import io.permit.sdk.openapi.JSON;

/**
 * ResourceRoleRead
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-10-13T15:32:07.306280+03:00[Asia/Jerusalem]")
public class ResourceRoleRead {
  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_DESCRIPTION = "description";
  @SerializedName(SERIALIZED_NAME_DESCRIPTION)
  private String description;

  public static final String SERIALIZED_NAME_PERMISSIONS = "permissions";
  @SerializedName(SERIALIZED_NAME_PERMISSIONS)
  private List<String> permissions = null;

  public static final String SERIALIZED_NAME_EXTENDS = "extends";
  @SerializedName(SERIALIZED_NAME_EXTENDS)
  private List<String> _extends = null;

  public static final String SERIALIZED_NAME_KEY = "key";
  @SerializedName(SERIALIZED_NAME_KEY)
  private String key;

  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private UUID id;

  public static final String SERIALIZED_NAME_ORGANIZATION_ID = "organization_id";
  @SerializedName(SERIALIZED_NAME_ORGANIZATION_ID)
  private UUID organizationId;

  public static final String SERIALIZED_NAME_PROJECT_ID = "project_id";
  @SerializedName(SERIALIZED_NAME_PROJECT_ID)
  private UUID projectId;

  public static final String SERIALIZED_NAME_ENVIRONMENT_ID = "environment_id";
  @SerializedName(SERIALIZED_NAME_ENVIRONMENT_ID)
  private UUID environmentId;

  public static final String SERIALIZED_NAME_RESOURCE_ID = "resource_id";
  @SerializedName(SERIALIZED_NAME_RESOURCE_ID)
  private UUID resourceId;

  public static final String SERIALIZED_NAME_CREATED_AT = "created_at";
  @SerializedName(SERIALIZED_NAME_CREATED_AT)
  private OffsetDateTime createdAt;

  public static final String SERIALIZED_NAME_UPDATED_AT = "updated_at";
  @SerializedName(SERIALIZED_NAME_UPDATED_AT)
  private OffsetDateTime updatedAt;

  public ResourceRoleRead() {
  }

  public ResourceRoleRead name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * The name of the role
   * @return name
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "The name of the role")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public ResourceRoleRead description(String description) {
    
    this.description = description;
    return this;
  }

   /**
   * optional description string explaining what this role represents, or what permissions are granted to it.
   * @return description
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "optional description string explaining what this role represents, or what permissions are granted to it.")

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public ResourceRoleRead permissions(List<String> permissions) {
    
    this.permissions = permissions;
    return this;
  }

  public ResourceRoleRead addPermissionsItem(String permissionsItem) {
    if (this.permissions == null) {
      this.permissions = new ArrayList<>();
    }
    this.permissions.add(permissionsItem);
    return this;
  }

   /**
   * list of action keys that define what actions this resource role is permitted to do
   * @return permissions
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "list of action keys that define what actions this resource role is permitted to do")

  public List<String> getPermissions() {
    return permissions;
  }


  public void setPermissions(List<String> permissions) {
    this.permissions = permissions;
  }


  public ResourceRoleRead _extends(List<String> _extends) {
    
    this._extends = _extends;
    return this;
  }

  public ResourceRoleRead addExtendsItem(String _extendsItem) {
    if (this._extends == null) {
      this._extends = new ArrayList<>();
    }
    this._extends.add(_extendsItem);
    return this;
  }

   /**
   * list of role keys that define what roles this role extends. In other words: this role will automatically inherit all the permissions of the given roles in this list.
   * @return _extends
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "list of role keys that define what roles this role extends. In other words: this role will automatically inherit all the permissions of the given roles in this list.")

  public List<String> getExtends() {
    return _extends;
  }


  public void setExtends(List<String> _extends) {
    this._extends = _extends;
  }


  public ResourceRoleRead key(String key) {
    
    this.key = key;
    return this;
  }

   /**
   * A URL-friendly name of the role (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the role.
   * @return key
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "A URL-friendly name of the role (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the role.")

  public String getKey() {
    return key;
  }


  public void setKey(String key) {
    this.key = key;
  }


  public ResourceRoleRead id(UUID id) {
    
    this.id = id;
    return this;
  }

   /**
   * Unique id of the role
   * @return id
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Unique id of the role")

  public UUID getId() {
    return id;
  }


  public void setId(UUID id) {
    this.id = id;
  }


  public ResourceRoleRead organizationId(UUID organizationId) {
    
    this.organizationId = organizationId;
    return this;
  }

   /**
   * Unique id of the organization that the role belongs to.
   * @return organizationId
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Unique id of the organization that the role belongs to.")

  public UUID getOrganizationId() {
    return organizationId;
  }


  public void setOrganizationId(UUID organizationId) {
    this.organizationId = organizationId;
  }


  public ResourceRoleRead projectId(UUID projectId) {
    
    this.projectId = projectId;
    return this;
  }

   /**
   * Unique id of the project that the role belongs to.
   * @return projectId
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Unique id of the project that the role belongs to.")

  public UUID getProjectId() {
    return projectId;
  }


  public void setProjectId(UUID projectId) {
    this.projectId = projectId;
  }


  public ResourceRoleRead environmentId(UUID environmentId) {
    
    this.environmentId = environmentId;
    return this;
  }

   /**
   * Unique id of the environment that the role belongs to.
   * @return environmentId
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Unique id of the environment that the role belongs to.")

  public UUID getEnvironmentId() {
    return environmentId;
  }


  public void setEnvironmentId(UUID environmentId) {
    this.environmentId = environmentId;
  }


  public ResourceRoleRead resourceId(UUID resourceId) {
    
    this.resourceId = resourceId;
    return this;
  }

   /**
   * Unique id of the resource that the role belongs to.
   * @return resourceId
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Unique id of the resource that the role belongs to.")

  public UUID getResourceId() {
    return resourceId;
  }


  public void setResourceId(UUID resourceId) {
    this.resourceId = resourceId;
  }


  public ResourceRoleRead createdAt(OffsetDateTime createdAt) {
    
    this.createdAt = createdAt;
    return this;
  }

   /**
   * Date and time when the role was created (ISO_8601 format).
   * @return createdAt
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Date and time when the role was created (ISO_8601 format).")

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }


  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }


  public ResourceRoleRead updatedAt(OffsetDateTime updatedAt) {
    
    this.updatedAt = updatedAt;
    return this;
  }

   /**
   * Date and time when the role was last updated/modified (ISO_8601 format).
   * @return updatedAt
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "Date and time when the role was last updated/modified (ISO_8601 format).")

  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }


  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResourceRoleRead resourceRoleRead = (ResourceRoleRead) o;
    return Objects.equals(this.name, resourceRoleRead.name) &&
        Objects.equals(this.description, resourceRoleRead.description) &&
        Objects.equals(this.permissions, resourceRoleRead.permissions) &&
        Objects.equals(this._extends, resourceRoleRead._extends) &&
        Objects.equals(this.key, resourceRoleRead.key) &&
        Objects.equals(this.id, resourceRoleRead.id) &&
        Objects.equals(this.organizationId, resourceRoleRead.organizationId) &&
        Objects.equals(this.projectId, resourceRoleRead.projectId) &&
        Objects.equals(this.environmentId, resourceRoleRead.environmentId) &&
        Objects.equals(this.resourceId, resourceRoleRead.resourceId) &&
        Objects.equals(this.createdAt, resourceRoleRead.createdAt) &&
        Objects.equals(this.updatedAt, resourceRoleRead.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, permissions, _extends, key, id, organizationId, projectId, environmentId, resourceId, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResourceRoleRead {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    permissions: ").append(toIndentedString(permissions)).append("\n");
    sb.append("    _extends: ").append(toIndentedString(_extends)).append("\n");
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    organizationId: ").append(toIndentedString(organizationId)).append("\n");
    sb.append("    projectId: ").append(toIndentedString(projectId)).append("\n");
    sb.append("    environmentId: ").append(toIndentedString(environmentId)).append("\n");
    sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("name");
    openapiFields.add("description");
    openapiFields.add("permissions");
    openapiFields.add("extends");
    openapiFields.add("key");
    openapiFields.add("id");
    openapiFields.add("organization_id");
    openapiFields.add("project_id");
    openapiFields.add("environment_id");
    openapiFields.add("resource_id");
    openapiFields.add("created_at");
    openapiFields.add("updated_at");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("name");
    openapiRequiredFields.add("key");
    openapiRequiredFields.add("id");
    openapiRequiredFields.add("organization_id");
    openapiRequiredFields.add("project_id");
    openapiRequiredFields.add("environment_id");
    openapiRequiredFields.add("resource_id");
    openapiRequiredFields.add("created_at");
    openapiRequiredFields.add("updated_at");
  }

 /**
  * Validates the JSON Object and throws an exception if issues found
  *
  * @param jsonObj JSON Object
  * @throws IOException if the JSON Object is invalid with respect to ResourceRoleRead
  */
  public static void validateJsonObject(JsonObject jsonObj) throws IOException {
      if (jsonObj == null) {
        if (ResourceRoleRead.openapiRequiredFields.isEmpty()) {
          return;
        } else { // has required fields
          throw new IllegalArgumentException(String.format("The required field(s) %s in ResourceRoleRead is not found in the empty JSON string", ResourceRoleRead.openapiRequiredFields.toString()));
        }
      }

      Set<Entry<String, JsonElement>> entries = jsonObj.entrySet();
      // check to see if the JSON string contains additional fields
      for (Entry<String, JsonElement> entry : entries) {
        if (!ResourceRoleRead.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `ResourceRoleRead` properties. JSON: %s", entry.getKey(), jsonObj.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : ResourceRoleRead.openapiRequiredFields) {
        if (jsonObj.get(requiredField) == null) {
          throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonObj.toString()));
        }
      }
      if ((jsonObj.get("name") != null && !jsonObj.get("name").isJsonNull()) && !jsonObj.get("name").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `name` to be a primitive type in the JSON string but got `%s`", jsonObj.get("name").toString()));
      }
      if ((jsonObj.get("description") != null && !jsonObj.get("description").isJsonNull()) && !jsonObj.get("description").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `description` to be a primitive type in the JSON string but got `%s`", jsonObj.get("description").toString()));
      }
      // ensure the json data is an array
      if ((jsonObj.get("permissions") != null && !jsonObj.get("permissions").isJsonNull()) && !jsonObj.get("permissions").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `permissions` to be an array in the JSON string but got `%s`", jsonObj.get("permissions").toString()));
      }
      // ensure the json data is an array
      if ((jsonObj.get("extends") != null && !jsonObj.get("extends").isJsonNull()) && !jsonObj.get("extends").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `extends` to be an array in the JSON string but got `%s`", jsonObj.get("extends").toString()));
      }
      if ((jsonObj.get("key") != null && !jsonObj.get("key").isJsonNull()) && !jsonObj.get("key").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `key` to be a primitive type in the JSON string but got `%s`", jsonObj.get("key").toString()));
      }
      if ((jsonObj.get("id") != null && !jsonObj.get("id").isJsonNull()) && !jsonObj.get("id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("id").toString()));
      }
      if ((jsonObj.get("organization_id") != null && !jsonObj.get("organization_id").isJsonNull()) && !jsonObj.get("organization_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `organization_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("organization_id").toString()));
      }
      if ((jsonObj.get("project_id") != null && !jsonObj.get("project_id").isJsonNull()) && !jsonObj.get("project_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `project_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("project_id").toString()));
      }
      if ((jsonObj.get("environment_id") != null && !jsonObj.get("environment_id").isJsonNull()) && !jsonObj.get("environment_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `environment_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("environment_id").toString()));
      }
      if ((jsonObj.get("resource_id") != null && !jsonObj.get("resource_id").isJsonNull()) && !jsonObj.get("resource_id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `resource_id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("resource_id").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ResourceRoleRead.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ResourceRoleRead' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ResourceRoleRead> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ResourceRoleRead.class));

       return (TypeAdapter<T>) new TypeAdapter<ResourceRoleRead>() {
           @Override
           public void write(JsonWriter out, ResourceRoleRead value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ResourceRoleRead read(JsonReader in) throws IOException {
             JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
             validateJsonObject(jsonObj);
             return thisAdapter.fromJsonTree(jsonObj);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of ResourceRoleRead given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ResourceRoleRead
  * @throws IOException if the JSON string is invalid with respect to ResourceRoleRead
  */
  public static ResourceRoleRead fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ResourceRoleRead.class);
  }

 /**
  * Convert an instance of ResourceRoleRead to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

