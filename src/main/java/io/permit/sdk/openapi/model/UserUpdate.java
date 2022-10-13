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
 * UserUpdate
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-10-13T15:32:07.306280+03:00[Asia/Jerusalem]")
public class UserUpdate {
  public static final String SERIALIZED_NAME_EMAIL = "email";
  @SerializedName(SERIALIZED_NAME_EMAIL)
  private String email;

  public static final String SERIALIZED_NAME_FIRST_NAME = "first_name";
  @SerializedName(SERIALIZED_NAME_FIRST_NAME)
  private String firstName;

  public static final String SERIALIZED_NAME_LAST_NAME = "last_name";
  @SerializedName(SERIALIZED_NAME_LAST_NAME)
  private String lastName;

  public static final String SERIALIZED_NAME_ATTRIBUTES = "attributes";
  @SerializedName(SERIALIZED_NAME_ATTRIBUTES)
  private Object attributes = {};

  public UserUpdate() {
  }

  public UserUpdate email(String email) {
    
    this.email = email;
    return this;
  }

   /**
   * The email of the user. If synced, will be unique inside the environment.
   * @return email
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "The email of the user. If synced, will be unique inside the environment.")

  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public UserUpdate firstName(String firstName) {
    
    this.firstName = firstName;
    return this;
  }

   /**
   * First name of the user.
   * @return firstName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "First name of the user.")

  public String getFirstName() {
    return firstName;
  }


  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }


  public UserUpdate lastName(String lastName) {
    
    this.lastName = lastName;
    return this;
  }

   /**
   * Last name of the user.
   * @return lastName
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Last name of the user.")

  public String getLastName() {
    return lastName;
  }


  public void setLastName(String lastName) {
    this.lastName = lastName;
  }


  public UserUpdate attributes(Object attributes) {
    
    this.attributes = attributes;
    return this;
  }

   /**
   * Arbitraty user attributes that will be used to enforce attribute-based access control policies.
   * @return attributes
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Arbitraty user attributes that will be used to enforce attribute-based access control policies.")

  public Object getAttributes() {
    return attributes;
  }


  public void setAttributes(Object attributes) {
    this.attributes = attributes;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserUpdate userUpdate = (UserUpdate) o;
    return Objects.equals(this.email, userUpdate.email) &&
        Objects.equals(this.firstName, userUpdate.firstName) &&
        Objects.equals(this.lastName, userUpdate.lastName) &&
        Objects.equals(this.attributes, userUpdate.attributes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, firstName, lastName, attributes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserUpdate {\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    attributes: ").append(toIndentedString(attributes)).append("\n");
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
    openapiFields.add("email");
    openapiFields.add("first_name");
    openapiFields.add("last_name");
    openapiFields.add("attributes");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Object and throws an exception if issues found
  *
  * @param jsonObj JSON Object
  * @throws IOException if the JSON Object is invalid with respect to UserUpdate
  */
  public static void validateJsonObject(JsonObject jsonObj) throws IOException {
      if (jsonObj == null) {
        if (UserUpdate.openapiRequiredFields.isEmpty()) {
          return;
        } else { // has required fields
          throw new IllegalArgumentException(String.format("The required field(s) %s in UserUpdate is not found in the empty JSON string", UserUpdate.openapiRequiredFields.toString()));
        }
      }

      Set<Entry<String, JsonElement>> entries = jsonObj.entrySet();
      // check to see if the JSON string contains additional fields
      for (Entry<String, JsonElement> entry : entries) {
        if (!UserUpdate.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `UserUpdate` properties. JSON: %s", entry.getKey(), jsonObj.toString()));
        }
      }
      if ((jsonObj.get("email") != null && !jsonObj.get("email").isJsonNull()) && !jsonObj.get("email").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `email` to be a primitive type in the JSON string but got `%s`", jsonObj.get("email").toString()));
      }
      if ((jsonObj.get("first_name") != null && !jsonObj.get("first_name").isJsonNull()) && !jsonObj.get("first_name").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `first_name` to be a primitive type in the JSON string but got `%s`", jsonObj.get("first_name").toString()));
      }
      if ((jsonObj.get("last_name") != null && !jsonObj.get("last_name").isJsonNull()) && !jsonObj.get("last_name").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `last_name` to be a primitive type in the JSON string but got `%s`", jsonObj.get("last_name").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!UserUpdate.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'UserUpdate' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<UserUpdate> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(UserUpdate.class));

       return (TypeAdapter<T>) new TypeAdapter<UserUpdate>() {
           @Override
           public void write(JsonWriter out, UserUpdate value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public UserUpdate read(JsonReader in) throws IOException {
             JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
             validateJsonObject(jsonObj);
             return thisAdapter.fromJsonTree(jsonObj);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of UserUpdate given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of UserUpdate
  * @throws IOException if the JSON string is invalid with respect to UserUpdate
  */
  public static UserUpdate fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, UserUpdate.class);
  }

 /**
  * Convert an instance of UserUpdate to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

