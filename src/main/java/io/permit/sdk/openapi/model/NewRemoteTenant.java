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
 * create user from remote user
 */
@ApiModel(description = "create user from remote user")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-10-13T15:32:07.306280+03:00[Asia/Jerusalem]")
public class NewRemoteTenant {
  public static final String SERIALIZED_NAME_EXTERNAL_ID = "externalId";
  @SerializedName(SERIALIZED_NAME_EXTERNAL_ID)
  private String externalId;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_DESCRIPTION = "description";
  @SerializedName(SERIALIZED_NAME_DESCRIPTION)
  private String description;

  public static final String SERIALIZED_NAME_SETTINGS = "settings";
  @SerializedName(SERIALIZED_NAME_SETTINGS)
  private Object settings;

  public static final String SERIALIZED_NAME_ENVIRONMENT_ID = "environmentId";
  @SerializedName(SERIALIZED_NAME_ENVIRONMENT_ID)
  private UUID environmentId;

  public NewRemoteTenant() {
  }

  public NewRemoteTenant externalId(String externalId) {
    
    this.externalId = externalId;
    return this;
  }

   /**
   * Get externalId
   * @return externalId
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getExternalId() {
    return externalId;
  }


  public void setExternalId(String externalId) {
    this.externalId = externalId;
  }


  public NewRemoteTenant name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public NewRemoteTenant description(String description) {
    
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public NewRemoteTenant settings(Object settings) {
    
    this.settings = settings;
    return this;
  }

   /**
   * Get settings
   * @return settings
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Object getSettings() {
    return settings;
  }


  public void setSettings(Object settings) {
    this.settings = settings;
  }


  public NewRemoteTenant environmentId(UUID environmentId) {
    
    this.environmentId = environmentId;
    return this;
  }

   /**
   * optionally scope tenant to environment
   * @return environmentId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "optionally scope tenant to environment")

  public UUID getEnvironmentId() {
    return environmentId;
  }


  public void setEnvironmentId(UUID environmentId) {
    this.environmentId = environmentId;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewRemoteTenant newRemoteTenant = (NewRemoteTenant) o;
    return Objects.equals(this.externalId, newRemoteTenant.externalId) &&
        Objects.equals(this.name, newRemoteTenant.name) &&
        Objects.equals(this.description, newRemoteTenant.description) &&
        Objects.equals(this.settings, newRemoteTenant.settings) &&
        Objects.equals(this.environmentId, newRemoteTenant.environmentId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(externalId, name, description, settings, environmentId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewRemoteTenant {\n");
    sb.append("    externalId: ").append(toIndentedString(externalId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    settings: ").append(toIndentedString(settings)).append("\n");
    sb.append("    environmentId: ").append(toIndentedString(environmentId)).append("\n");
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
    openapiFields.add("externalId");
    openapiFields.add("name");
    openapiFields.add("description");
    openapiFields.add("settings");
    openapiFields.add("environmentId");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("externalId");
    openapiRequiredFields.add("name");
  }

 /**
  * Validates the JSON Object and throws an exception if issues found
  *
  * @param jsonObj JSON Object
  * @throws IOException if the JSON Object is invalid with respect to NewRemoteTenant
  */
  public static void validateJsonObject(JsonObject jsonObj) throws IOException {
      if (jsonObj == null) {
        if (NewRemoteTenant.openapiRequiredFields.isEmpty()) {
          return;
        } else { // has required fields
          throw new IllegalArgumentException(String.format("The required field(s) %s in NewRemoteTenant is not found in the empty JSON string", NewRemoteTenant.openapiRequiredFields.toString()));
        }
      }

      Set<Entry<String, JsonElement>> entries = jsonObj.entrySet();
      // check to see if the JSON string contains additional fields
      for (Entry<String, JsonElement> entry : entries) {
        if (!NewRemoteTenant.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `NewRemoteTenant` properties. JSON: %s", entry.getKey(), jsonObj.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : NewRemoteTenant.openapiRequiredFields) {
        if (jsonObj.get(requiredField) == null) {
          throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonObj.toString()));
        }
      }
      if ((jsonObj.get("externalId") != null && !jsonObj.get("externalId").isJsonNull()) && !jsonObj.get("externalId").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `externalId` to be a primitive type in the JSON string but got `%s`", jsonObj.get("externalId").toString()));
      }
      if ((jsonObj.get("name") != null && !jsonObj.get("name").isJsonNull()) && !jsonObj.get("name").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `name` to be a primitive type in the JSON string but got `%s`", jsonObj.get("name").toString()));
      }
      if ((jsonObj.get("description") != null && !jsonObj.get("description").isJsonNull()) && !jsonObj.get("description").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `description` to be a primitive type in the JSON string but got `%s`", jsonObj.get("description").toString()));
      }
      if ((jsonObj.get("environmentId") != null && !jsonObj.get("environmentId").isJsonNull()) && !jsonObj.get("environmentId").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `environmentId` to be a primitive type in the JSON string but got `%s`", jsonObj.get("environmentId").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!NewRemoteTenant.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'NewRemoteTenant' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<NewRemoteTenant> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(NewRemoteTenant.class));

       return (TypeAdapter<T>) new TypeAdapter<NewRemoteTenant>() {
           @Override
           public void write(JsonWriter out, NewRemoteTenant value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public NewRemoteTenant read(JsonReader in) throws IOException {
             JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
             validateJsonObject(jsonObj);
             return thisAdapter.fromJsonTree(jsonObj);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of NewRemoteTenant given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of NewRemoteTenant
  * @throws IOException if the JSON string is invalid with respect to NewRemoteTenant
  */
  public static NewRemoteTenant fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, NewRemoteTenant.class);
  }

 /**
  * Convert an instance of NewRemoteTenant to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

