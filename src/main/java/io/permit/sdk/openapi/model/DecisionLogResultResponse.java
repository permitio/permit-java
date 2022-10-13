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
import io.permit.sdk.openapi.model.DecisionLogGrantPermissions;
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
 * DecisionLogResultResponse
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-10-13T15:32:07.306280+03:00[Asia/Jerusalem]")
public class DecisionLogResultResponse {
  public static final String SERIALIZED_NAME_ALLOW = "allow";
  @SerializedName(SERIALIZED_NAME_ALLOW)
  private Boolean allow;

  public static final String SERIALIZED_NAME_GRANTING_PERMISSION = "grantingPermission";
  @SerializedName(SERIALIZED_NAME_GRANTING_PERMISSION)
  private List<DecisionLogGrantPermissions> grantingPermission = null;

  public DecisionLogResultResponse() {
  }

  public DecisionLogResultResponse allow(Boolean allow) {
    
    this.allow = allow;
    return this;
  }

   /**
   * Get allow
   * @return allow
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getAllow() {
    return allow;
  }


  public void setAllow(Boolean allow) {
    this.allow = allow;
  }


  public DecisionLogResultResponse grantingPermission(List<DecisionLogGrantPermissions> grantingPermission) {
    
    this.grantingPermission = grantingPermission;
    return this;
  }

  public DecisionLogResultResponse addGrantingPermissionItem(DecisionLogGrantPermissions grantingPermissionItem) {
    if (this.grantingPermission == null) {
      this.grantingPermission = new ArrayList<>();
    }
    this.grantingPermission.add(grantingPermissionItem);
    return this;
  }

   /**
   * Get grantingPermission
   * @return grantingPermission
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<DecisionLogGrantPermissions> getGrantingPermission() {
    return grantingPermission;
  }


  public void setGrantingPermission(List<DecisionLogGrantPermissions> grantingPermission) {
    this.grantingPermission = grantingPermission;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DecisionLogResultResponse decisionLogResultResponse = (DecisionLogResultResponse) o;
    return Objects.equals(this.allow, decisionLogResultResponse.allow) &&
        Objects.equals(this.grantingPermission, decisionLogResultResponse.grantingPermission);
  }

  @Override
  public int hashCode() {
    return Objects.hash(allow, grantingPermission);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DecisionLogResultResponse {\n");
    sb.append("    allow: ").append(toIndentedString(allow)).append("\n");
    sb.append("    grantingPermission: ").append(toIndentedString(grantingPermission)).append("\n");
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
    openapiFields.add("allow");
    openapiFields.add("grantingPermission");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Object and throws an exception if issues found
  *
  * @param jsonObj JSON Object
  * @throws IOException if the JSON Object is invalid with respect to DecisionLogResultResponse
  */
  public static void validateJsonObject(JsonObject jsonObj) throws IOException {
      if (jsonObj == null) {
        if (DecisionLogResultResponse.openapiRequiredFields.isEmpty()) {
          return;
        } else { // has required fields
          throw new IllegalArgumentException(String.format("The required field(s) %s in DecisionLogResultResponse is not found in the empty JSON string", DecisionLogResultResponse.openapiRequiredFields.toString()));
        }
      }

      Set<Entry<String, JsonElement>> entries = jsonObj.entrySet();
      // check to see if the JSON string contains additional fields
      for (Entry<String, JsonElement> entry : entries) {
        if (!DecisionLogResultResponse.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `DecisionLogResultResponse` properties. JSON: %s", entry.getKey(), jsonObj.toString()));
        }
      }
      if (jsonObj.get("grantingPermission") != null && !jsonObj.get("grantingPermission").isJsonNull()) {
        JsonArray jsonArraygrantingPermission = jsonObj.getAsJsonArray("grantingPermission");
        if (jsonArraygrantingPermission != null) {
          // ensure the json data is an array
          if (!jsonObj.get("grantingPermission").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `grantingPermission` to be an array in the JSON string but got `%s`", jsonObj.get("grantingPermission").toString()));
          }

          // validate the optional field `grantingPermission` (array)
          for (int i = 0; i < jsonArraygrantingPermission.size(); i++) {
            DecisionLogGrantPermissions.validateJsonObject(jsonArraygrantingPermission.get(i).getAsJsonObject());
          };
        }
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!DecisionLogResultResponse.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'DecisionLogResultResponse' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<DecisionLogResultResponse> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(DecisionLogResultResponse.class));

       return (TypeAdapter<T>) new TypeAdapter<DecisionLogResultResponse>() {
           @Override
           public void write(JsonWriter out, DecisionLogResultResponse value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public DecisionLogResultResponse read(JsonReader in) throws IOException {
             JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
             validateJsonObject(jsonObj);
             return thisAdapter.fromJsonTree(jsonObj);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of DecisionLogResultResponse given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of DecisionLogResultResponse
  * @throws IOException if the JSON string is invalid with respect to DecisionLogResultResponse
  */
  public static DecisionLogResultResponse fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, DecisionLogResultResponse.class);
  }

 /**
  * Convert an instance of DecisionLogResultResponse to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

