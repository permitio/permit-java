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
 * RoleAssignmentRemove
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-10-13T15:32:07.306280+03:00[Asia/Jerusalem]")
public class RoleAssignmentRemove {
  public static final String SERIALIZED_NAME_ROLE = "role";
  @SerializedName(SERIALIZED_NAME_ROLE)
  private String role;

  public static final String SERIALIZED_NAME_TENANT = "tenant";
  @SerializedName(SERIALIZED_NAME_TENANT)
  private String tenant;

  public static final String SERIALIZED_NAME_USER = "user";
  @SerializedName(SERIALIZED_NAME_USER)
  private String user;

  public RoleAssignmentRemove() {
  }

  public RoleAssignmentRemove role(String role) {
    
    this.role = role;
    return this;
  }

   /**
   * the role that will be unassigned (accepts either the role id or the role key)
   * @return role
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "the role that will be unassigned (accepts either the role id or the role key)")

  public String getRole() {
    return role;
  }


  public void setRole(String role) {
    this.role = role;
  }


  public RoleAssignmentRemove tenant(String tenant) {
    
    this.tenant = tenant;
    return this;
  }

   /**
   * the tenant the role is associated with (accepts either the tenant id or the tenant key)
   * @return tenant
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "the tenant the role is associated with (accepts either the tenant id or the tenant key)")

  public String getTenant() {
    return tenant;
  }


  public void setTenant(String tenant) {
    this.tenant = tenant;
  }


  public RoleAssignmentRemove user(String user) {
    
    this.user = user;
    return this;
  }

   /**
   * the user the role will be unassigned from (accepts either the user id or the user key)
   * @return user
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "the user the role will be unassigned from (accepts either the user id or the user key)")

  public String getUser() {
    return user;
  }


  public void setUser(String user) {
    this.user = user;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoleAssignmentRemove roleAssignmentRemove = (RoleAssignmentRemove) o;
    return Objects.equals(this.role, roleAssignmentRemove.role) &&
        Objects.equals(this.tenant, roleAssignmentRemove.tenant) &&
        Objects.equals(this.user, roleAssignmentRemove.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(role, tenant, user);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoleAssignmentRemove {\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    tenant: ").append(toIndentedString(tenant)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
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
    openapiFields.add("role");
    openapiFields.add("tenant");
    openapiFields.add("user");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("role");
    openapiRequiredFields.add("tenant");
    openapiRequiredFields.add("user");
  }

 /**
  * Validates the JSON Object and throws an exception if issues found
  *
  * @param jsonObj JSON Object
  * @throws IOException if the JSON Object is invalid with respect to RoleAssignmentRemove
  */
  public static void validateJsonObject(JsonObject jsonObj) throws IOException {
      if (jsonObj == null) {
        if (RoleAssignmentRemove.openapiRequiredFields.isEmpty()) {
          return;
        } else { // has required fields
          throw new IllegalArgumentException(String.format("The required field(s) %s in RoleAssignmentRemove is not found in the empty JSON string", RoleAssignmentRemove.openapiRequiredFields.toString()));
        }
      }

      Set<Entry<String, JsonElement>> entries = jsonObj.entrySet();
      // check to see if the JSON string contains additional fields
      for (Entry<String, JsonElement> entry : entries) {
        if (!RoleAssignmentRemove.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `RoleAssignmentRemove` properties. JSON: %s", entry.getKey(), jsonObj.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : RoleAssignmentRemove.openapiRequiredFields) {
        if (jsonObj.get(requiredField) == null) {
          throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonObj.toString()));
        }
      }
      if ((jsonObj.get("role") != null && !jsonObj.get("role").isJsonNull()) && !jsonObj.get("role").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `role` to be a primitive type in the JSON string but got `%s`", jsonObj.get("role").toString()));
      }
      if ((jsonObj.get("tenant") != null && !jsonObj.get("tenant").isJsonNull()) && !jsonObj.get("tenant").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `tenant` to be a primitive type in the JSON string but got `%s`", jsonObj.get("tenant").toString()));
      }
      if ((jsonObj.get("user") != null && !jsonObj.get("user").isJsonNull()) && !jsonObj.get("user").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `user` to be a primitive type in the JSON string but got `%s`", jsonObj.get("user").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!RoleAssignmentRemove.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'RoleAssignmentRemove' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<RoleAssignmentRemove> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(RoleAssignmentRemove.class));

       return (TypeAdapter<T>) new TypeAdapter<RoleAssignmentRemove>() {
           @Override
           public void write(JsonWriter out, RoleAssignmentRemove value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public RoleAssignmentRemove read(JsonReader in) throws IOException {
             JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
             validateJsonObject(jsonObj);
             return thisAdapter.fromJsonTree(jsonObj);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of RoleAssignmentRemove given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of RoleAssignmentRemove
  * @throws IOException if the JSON string is invalid with respect to RoleAssignmentRemove
  */
  public static RoleAssignmentRemove fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, RoleAssignmentRemove.class);
  }

 /**
  * Convert an instance of RoleAssignmentRemove to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

