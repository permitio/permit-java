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
 * PdpValues
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-10-13T15:32:07.306280+03:00[Asia/Jerusalem]")
public class PdpValues {
  public static final String SERIALIZED_NAME_B_A_C_K_E_N_D_S_E_R_V_I_C_E_U_R_L = "BACKEND_SERVICE_URL";
  @SerializedName(SERIALIZED_NAME_B_A_C_K_E_N_D_S_E_R_V_I_C_E_U_R_L)
  private String BACKEND_SERVICE_URL;

  public static final String SERIALIZED_NAME_O_P_A_D_E_C_I_S_I_O_N_L_O_G_I_N_G_R_E_S_S_R_O_U_T_E = "OPA_DECISION_LOG_INGRESS_ROUTE";
  @SerializedName(SERIALIZED_NAME_O_P_A_D_E_C_I_S_I_O_N_L_O_G_I_N_G_R_E_S_S_R_O_U_T_E)
  private String OPA_DECISION_LOG_INGRESS_ROUTE;

  public PdpValues() {
  }

  public PdpValues BACKEND_SERVICE_URL(String BACKEND_SERVICE_URL) {
    
    this.BACKEND_SERVICE_URL = BACKEND_SERVICE_URL;
    return this;
  }

   /**
   * Get BACKEND_SERVICE_URL
   * @return BACKEND_SERVICE_URL
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getBACKENDSERVICEURL() {
    return BACKEND_SERVICE_URL;
  }


  public void setBACKENDSERVICEURL(String BACKEND_SERVICE_URL) {
    this.BACKEND_SERVICE_URL = BACKEND_SERVICE_URL;
  }


  public PdpValues OPA_DECISION_LOG_INGRESS_ROUTE(String OPA_DECISION_LOG_INGRESS_ROUTE) {
    
    this.OPA_DECISION_LOG_INGRESS_ROUTE = OPA_DECISION_LOG_INGRESS_ROUTE;
    return this;
  }

   /**
   * Get OPA_DECISION_LOG_INGRESS_ROUTE
   * @return OPA_DECISION_LOG_INGRESS_ROUTE
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getOPADECISIONLOGINGRESSROUTE() {
    return OPA_DECISION_LOG_INGRESS_ROUTE;
  }


  public void setOPADECISIONLOGINGRESSROUTE(String OPA_DECISION_LOG_INGRESS_ROUTE) {
    this.OPA_DECISION_LOG_INGRESS_ROUTE = OPA_DECISION_LOG_INGRESS_ROUTE;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PdpValues pdpValues = (PdpValues) o;
    return Objects.equals(this.BACKEND_SERVICE_URL, pdpValues.BACKEND_SERVICE_URL) &&
        Objects.equals(this.OPA_DECISION_LOG_INGRESS_ROUTE, pdpValues.OPA_DECISION_LOG_INGRESS_ROUTE);
  }

  @Override
  public int hashCode() {
    return Objects.hash(BACKEND_SERVICE_URL, OPA_DECISION_LOG_INGRESS_ROUTE);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PdpValues {\n");
    sb.append("    BACKEND_SERVICE_URL: ").append(toIndentedString(BACKEND_SERVICE_URL)).append("\n");
    sb.append("    OPA_DECISION_LOG_INGRESS_ROUTE: ").append(toIndentedString(OPA_DECISION_LOG_INGRESS_ROUTE)).append("\n");
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
    openapiFields.add("BACKEND_SERVICE_URL");
    openapiFields.add("OPA_DECISION_LOG_INGRESS_ROUTE");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("BACKEND_SERVICE_URL");
    openapiRequiredFields.add("OPA_DECISION_LOG_INGRESS_ROUTE");
  }

 /**
  * Validates the JSON Object and throws an exception if issues found
  *
  * @param jsonObj JSON Object
  * @throws IOException if the JSON Object is invalid with respect to PdpValues
  */
  public static void validateJsonObject(JsonObject jsonObj) throws IOException {
      if (jsonObj == null) {
        if (PdpValues.openapiRequiredFields.isEmpty()) {
          return;
        } else { // has required fields
          throw new IllegalArgumentException(String.format("The required field(s) %s in PdpValues is not found in the empty JSON string", PdpValues.openapiRequiredFields.toString()));
        }
      }

      Set<Entry<String, JsonElement>> entries = jsonObj.entrySet();
      // check to see if the JSON string contains additional fields
      for (Entry<String, JsonElement> entry : entries) {
        if (!PdpValues.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `PdpValues` properties. JSON: %s", entry.getKey(), jsonObj.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : PdpValues.openapiRequiredFields) {
        if (jsonObj.get(requiredField) == null) {
          throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonObj.toString()));
        }
      }
      if ((jsonObj.get("BACKEND_SERVICE_URL") != null && !jsonObj.get("BACKEND_SERVICE_URL").isJsonNull()) && !jsonObj.get("BACKEND_SERVICE_URL").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `BACKEND_SERVICE_URL` to be a primitive type in the JSON string but got `%s`", jsonObj.get("BACKEND_SERVICE_URL").toString()));
      }
      if ((jsonObj.get("OPA_DECISION_LOG_INGRESS_ROUTE") != null && !jsonObj.get("OPA_DECISION_LOG_INGRESS_ROUTE").isJsonNull()) && !jsonObj.get("OPA_DECISION_LOG_INGRESS_ROUTE").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `OPA_DECISION_LOG_INGRESS_ROUTE` to be a primitive type in the JSON string but got `%s`", jsonObj.get("OPA_DECISION_LOG_INGRESS_ROUTE").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!PdpValues.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'PdpValues' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<PdpValues> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(PdpValues.class));

       return (TypeAdapter<T>) new TypeAdapter<PdpValues>() {
           @Override
           public void write(JsonWriter out, PdpValues value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public PdpValues read(JsonReader in) throws IOException {
             JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
             validateJsonObject(jsonObj);
             return thisAdapter.fromJsonTree(jsonObj);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of PdpValues given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of PdpValues
  * @throws IOException if the JSON string is invalid with respect to PdpValues
  */
  public static PdpValues fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, PdpValues.class);
  }

 /**
  * Convert an instance of PdpValues to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

