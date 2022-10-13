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
import java.util.ArrayList;
import java.util.List;

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
 * RoleCreate
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-10-13T15:32:07.306280+03:00[Asia/Jerusalem]")
public class RoleCreate {
  public static final String SERIALIZED_NAME_KEY = "key";
  @SerializedName(SERIALIZED_NAME_KEY)
  private String key;

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

  public RoleCreate() {
  }

  public RoleCreate key(String key) {
    
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


  public RoleCreate name(String name) {
    
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


  public RoleCreate description(String description) {
    
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


  public RoleCreate permissions(List<String> permissions) {
    
    this.permissions = permissions;
    return this;
  }

  public RoleCreate addPermissionsItem(String permissionsItem) {
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


  public RoleCreate _extends(List<String> _extends) {
    
    this._extends = _extends;
    return this;
  }

  public RoleCreate addExtendsItem(String _extendsItem) {
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



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoleCreate roleCreate = (RoleCreate) o;
    return Objects.equals(this.key, roleCreate.key) &&
        Objects.equals(this.name, roleCreate.name) &&
        Objects.equals(this.description, roleCreate.description) &&
        Objects.equals(this.permissions, roleCreate.permissions) &&
        Objects.equals(this._extends, roleCreate._extends);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, name, description, permissions, _extends);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoleCreate {\n");
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    permissions: ").append(toIndentedString(permissions)).append("\n");
    sb.append("    _extends: ").append(toIndentedString(_extends)).append("\n");
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
    openapiFields.add("description");
    openapiFields.add("permissions");
    openapiFields.add("extends");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("key");
    openapiRequiredFields.add("name");
  }

 /**
  * Validates the JSON Object and throws an exception if issues found
  *
  * @param jsonObj JSON Object
  * @throws IOException if the JSON Object is invalid with respect to RoleCreate
  */
  public static void validateJsonObject(JsonObject jsonObj) throws IOException {
      if (jsonObj == null) {
        if (RoleCreate.openapiRequiredFields.isEmpty()) {
          return;
        } else { // has required fields
          throw new IllegalArgumentException(String.format("The required field(s) %s in RoleCreate is not found in the empty JSON string", RoleCreate.openapiRequiredFields.toString()));
        }
      }

      Set<Entry<String, JsonElement>> entries = jsonObj.entrySet();
      // check to see if the JSON string contains additional fields
      for (Entry<String, JsonElement> entry : entries) {
        if (!RoleCreate.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `RoleCreate` properties. JSON: %s", entry.getKey(), jsonObj.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : RoleCreate.openapiRequiredFields) {
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
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!RoleCreate.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'RoleCreate' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<RoleCreate> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(RoleCreate.class));

       return (TypeAdapter<T>) new TypeAdapter<RoleCreate>() {
           @Override
           public void write(JsonWriter out, RoleCreate value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public RoleCreate read(JsonReader in) throws IOException {
             JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
             validateJsonObject(jsonObj);
             return thisAdapter.fromJsonTree(jsonObj);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of RoleCreate given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of RoleCreate
  * @throws IOException if the JSON string is invalid with respect to RoleCreate
  */
  public static RoleCreate fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, RoleCreate.class);
  }

 /**
  * Convert an instance of RoleCreate to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

