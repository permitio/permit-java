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
import io.permit.sdk.openapi.model.ActionBlockEditable;
import io.permit.sdk.openapi.model.AttributeBlock;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
 * ResourceCreate
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-10-13T15:32:07.306280+03:00[Asia/Jerusalem]")
public class ResourceCreate {
  public static final String SERIALIZED_NAME_KEY = "key";
  @SerializedName(SERIALIZED_NAME_KEY)
  private String key;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_URN = "urn";
  @SerializedName(SERIALIZED_NAME_URN)
  private String urn;

  public static final String SERIALIZED_NAME_DESCRIPTION = "description";
  @SerializedName(SERIALIZED_NAME_DESCRIPTION)
  private String description;

  public static final String SERIALIZED_NAME_ACTIONS = "actions";
  @SerializedName(SERIALIZED_NAME_ACTIONS)
  private Map<String, ActionBlockEditable> actions = new HashMap<>();

  public static final String SERIALIZED_NAME_ATTRIBUTES = "attributes";
  @SerializedName(SERIALIZED_NAME_ATTRIBUTES)
  private Map<String, AttributeBlock> attributes = null;

  public static final String SERIALIZED_NAME_ROLES = "roles";
  @SerializedName(SERIALIZED_NAME_ROLES)
  private Object roles;

  public static final String SERIALIZED_NAME_RELATIONS = "relations";
  @SerializedName(SERIALIZED_NAME_RELATIONS)
  private Object relations;

  public ResourceCreate() {
  }

  public ResourceCreate key(String key) {
    
    this.key = key;
    return this;
  }

   /**
   * A URL-friendly name of the resource (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the resource.
   * @return key
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "A URL-friendly name of the resource (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the resource.")

  public String getKey() {
    return key;
  }


  public void setKey(String key) {
    this.key = key;
  }


  public ResourceCreate name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * The name of the resource
   * @return name
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "The name of the resource")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public ResourceCreate urn(String urn) {
    
    this.urn = urn;
    return this;
  }

   /**
   * The [URN](https://en.wikipedia.org/wiki/Uniform_Resource_Name) (Uniform Resource Name) of the resource
   * @return urn
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The [URN](https://en.wikipedia.org/wiki/Uniform_Resource_Name) (Uniform Resource Name) of the resource")

  public String getUrn() {
    return urn;
  }


  public void setUrn(String urn) {
    this.urn = urn;
  }


  public ResourceCreate description(String description) {
    
    this.description = description;
    return this;
  }

   /**
   * An optional longer description of what this resource respresents in your system
   * @return description
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "An optional longer description of what this resource respresents in your system")

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public ResourceCreate actions(Map<String, ActionBlockEditable> actions) {
    
    this.actions = actions;
    return this;
  }

  public ResourceCreate putActionsItem(String key, ActionBlockEditable actionsItem) {
    this.actions.put(key, actionsItem);
    return this;
  }

   /**
   *      A actions definition block, typically contained within a resource type definition block.     The actions represents the ways you can interact with a protected resource.     
   * @return actions
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "     A actions definition block, typically contained within a resource type definition block.     The actions represents the ways you can interact with a protected resource.     ")

  public Map<String, ActionBlockEditable> getActions() {
    return actions;
  }


  public void setActions(Map<String, ActionBlockEditable> actions) {
    this.actions = actions;
  }


  public ResourceCreate attributes(Map<String, AttributeBlock> attributes) {
    
    this.attributes = attributes;
    return this;
  }

  public ResourceCreate putAttributesItem(String key, AttributeBlock attributesItem) {
    if (this.attributes == null) {
      this.attributes = new HashMap<>();
    }
    this.attributes.put(key, attributesItem);
    return this;
  }

   /**
   * Attributes that each resource of this type defines, and can be used in your ABAC policies.
   * @return attributes
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Attributes that each resource of this type defines, and can be used in your ABAC policies.")

  public Map<String, AttributeBlock> getAttributes() {
    return attributes;
  }


  public void setAttributes(Map<String, AttributeBlock> attributes) {
    this.attributes = attributes;
  }


  public ResourceCreate roles(Object roles) {
    
    this.roles = roles;
    return this;
  }

   /**
   * Roles defined on this resource. The key is the role name, and the value contains the role properties such as granted permissions, base roles, etc.
   * @return roles
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Roles defined on this resource. The key is the role name, and the value contains the role properties such as granted permissions, base roles, etc.")

  public Object getRoles() {
    return roles;
  }


  public void setRoles(Object roles) {
    this.roles = roles;
  }


  public ResourceCreate relations(Object relations) {
    
    this.relations = relations;
    return this;
  }

   /**
   * Relations to other resources. The key is the relation name, and the value is the destination resource.
   * @return relations
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Relations to other resources. The key is the relation name, and the value is the destination resource.")

  public Object getRelations() {
    return relations;
  }


  public void setRelations(Object relations) {
    this.relations = relations;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResourceCreate resourceCreate = (ResourceCreate) o;
    return Objects.equals(this.key, resourceCreate.key) &&
        Objects.equals(this.name, resourceCreate.name) &&
        Objects.equals(this.urn, resourceCreate.urn) &&
        Objects.equals(this.description, resourceCreate.description) &&
        Objects.equals(this.actions, resourceCreate.actions) &&
        Objects.equals(this.attributes, resourceCreate.attributes) &&
        Objects.equals(this.roles, resourceCreate.roles) &&
        Objects.equals(this.relations, resourceCreate.relations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, name, urn, description, actions, attributes, roles, relations);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResourceCreate {\n");
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    urn: ").append(toIndentedString(urn)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    actions: ").append(toIndentedString(actions)).append("\n");
    sb.append("    attributes: ").append(toIndentedString(attributes)).append("\n");
    sb.append("    roles: ").append(toIndentedString(roles)).append("\n");
    sb.append("    relations: ").append(toIndentedString(relations)).append("\n");
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
    openapiFields.add("key");
    openapiFields.add("name");
    openapiFields.add("urn");
    openapiFields.add("description");
    openapiFields.add("actions");
    openapiFields.add("attributes");
    openapiFields.add("roles");
    openapiFields.add("relations");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("key");
    openapiRequiredFields.add("name");
    openapiRequiredFields.add("actions");
  }

 /**
  * Validates the JSON Object and throws an exception if issues found
  *
  * @param jsonObj JSON Object
  * @throws IOException if the JSON Object is invalid with respect to ResourceCreate
  */
  public static void validateJsonObject(JsonObject jsonObj) throws IOException {
      if (jsonObj == null) {
        if (ResourceCreate.openapiRequiredFields.isEmpty()) {
          return;
        } else { // has required fields
          throw new IllegalArgumentException(String.format("The required field(s) %s in ResourceCreate is not found in the empty JSON string", ResourceCreate.openapiRequiredFields.toString()));
        }
      }

      Set<Entry<String, JsonElement>> entries = jsonObj.entrySet();
      // check to see if the JSON string contains additional fields
      for (Entry<String, JsonElement> entry : entries) {
        if (!ResourceCreate.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `ResourceCreate` properties. JSON: %s", entry.getKey(), jsonObj.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : ResourceCreate.openapiRequiredFields) {
        if (jsonObj.get(requiredField) == null) {
          throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonObj.toString()));
        }
      }
      if ((jsonObj.get("key") != null && !jsonObj.get("key").isJsonNull()) && !jsonObj.get("key").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `key` to be a primitive type in the JSON string but got `%s`", jsonObj.get("key").toString()));
      }
      if ((jsonObj.get("name") != null && !jsonObj.get("name").isJsonNull()) && !jsonObj.get("name").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `name` to be a primitive type in the JSON string but got `%s`", jsonObj.get("name").toString()));
      }
      if ((jsonObj.get("urn") != null && !jsonObj.get("urn").isJsonNull()) && !jsonObj.get("urn").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `urn` to be a primitive type in the JSON string but got `%s`", jsonObj.get("urn").toString()));
      }
      if ((jsonObj.get("description") != null && !jsonObj.get("description").isJsonNull()) && !jsonObj.get("description").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `description` to be a primitive type in the JSON string but got `%s`", jsonObj.get("description").toString()));
      }
      // validate the optional field `roles`
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ResourceCreate.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ResourceCreate' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ResourceCreate> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ResourceCreate.class));

       return (TypeAdapter<T>) new TypeAdapter<ResourceCreate>() {
           @Override
           public void write(JsonWriter out, ResourceCreate value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ResourceCreate read(JsonReader in) throws IOException {
             JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
             validateJsonObject(jsonObj);
             return thisAdapter.fromJsonTree(jsonObj);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of ResourceCreate given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ResourceCreate
  * @throws IOException if the JSON string is invalid with respect to ResourceCreate
  */
  public static ResourceCreate fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ResourceCreate.class);
  }

 /**
  * Convert an instance of ResourceCreate to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

