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
import io.permit.sdk.openapi.model.DecisionLogResponse;
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
 * Abstract base class for generic types.  A generic type is typically declared by inheriting from this class parameterized with one or more type variables. For example, a generic mapping type might be defined as::    class Mapping(Generic[KT, VT]):       def __getitem__(self, key: KT) -&gt; VT:           ...       # Etc.  This class can then be used as follows::    def lookup_name(mapping: Mapping[KT, VT], key: KT, default: VT) -&gt; VT:       try:           return mapping[key]       except KeyError:           return default
 */
@ApiModel(description = "Abstract base class for generic types.  A generic type is typically declared by inheriting from this class parameterized with one or more type variables. For example, a generic mapping type might be defined as::    class Mapping(Generic[KT, VT]):       def __getitem__(self, key: KT) -> VT:           ...       # Etc.  This class can then be used as follows::    def lookup_name(mapping: Mapping[KT, VT], key: KT, default: VT) -> VT:       try:           return mapping[key]       except KeyError:           return default")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-10-13T15:32:07.306280+03:00[Asia/Jerusalem]")
public class DecisionLogResponseList {
  public static final String SERIALIZED_NAME_COUNT_FILTERED = "countFiltered";
  @SerializedName(SERIALIZED_NAME_COUNT_FILTERED)
  private Integer countFiltered;

  public static final String SERIALIZED_NAME_COUNT = "count";
  @SerializedName(SERIALIZED_NAME_COUNT)
  private Integer count;

  public static final String SERIALIZED_NAME_URL = "url";
  @SerializedName(SERIALIZED_NAME_URL)
  private String url;

  public static final String SERIALIZED_NAME_DATA = "data";
  @SerializedName(SERIALIZED_NAME_DATA)
  private List<DecisionLogResponse> data = new ArrayList<>();

  public DecisionLogResponseList() {
  }

  public DecisionLogResponseList countFiltered(Integer countFiltered) {
    
    this.countFiltered = countFiltered;
    return this;
  }

   /**
   * Get countFiltered
   * @return countFiltered
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Integer getCountFiltered() {
    return countFiltered;
  }


  public void setCountFiltered(Integer countFiltered) {
    this.countFiltered = countFiltered;
  }


  public DecisionLogResponseList count(Integer count) {
    
    this.count = count;
    return this;
  }

   /**
   * Get count
   * @return count
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Integer getCount() {
    return count;
  }


  public void setCount(Integer count) {
    this.count = count;
  }


  public DecisionLogResponseList url(String url) {
    
    this.url = url;
    return this;
  }

   /**
   * Get url
   * @return url
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getUrl() {
    return url;
  }


  public void setUrl(String url) {
    this.url = url;
  }


  public DecisionLogResponseList data(List<DecisionLogResponse> data) {
    
    this.data = data;
    return this;
  }

  public DecisionLogResponseList addDataItem(DecisionLogResponse dataItem) {
    this.data.add(dataItem);
    return this;
  }

   /**
   * Get data
   * @return data
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public List<DecisionLogResponse> getData() {
    return data;
  }


  public void setData(List<DecisionLogResponse> data) {
    this.data = data;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DecisionLogResponseList decisionLogResponseList = (DecisionLogResponseList) o;
    return Objects.equals(this.countFiltered, decisionLogResponseList.countFiltered) &&
        Objects.equals(this.count, decisionLogResponseList.count) &&
        Objects.equals(this.url, decisionLogResponseList.url) &&
        Objects.equals(this.data, decisionLogResponseList.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(countFiltered, count, url, data);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DecisionLogResponseList {\n");
    sb.append("    countFiltered: ").append(toIndentedString(countFiltered)).append("\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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
    openapiFields.add("countFiltered");
    openapiFields.add("count");
    openapiFields.add("url");
    openapiFields.add("data");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("url");
    openapiRequiredFields.add("data");
  }

 /**
  * Validates the JSON Object and throws an exception if issues found
  *
  * @param jsonObj JSON Object
  * @throws IOException if the JSON Object is invalid with respect to DecisionLogResponseList
  */
  public static void validateJsonObject(JsonObject jsonObj) throws IOException {
      if (jsonObj == null) {
        if (DecisionLogResponseList.openapiRequiredFields.isEmpty()) {
          return;
        } else { // has required fields
          throw new IllegalArgumentException(String.format("The required field(s) %s in DecisionLogResponseList is not found in the empty JSON string", DecisionLogResponseList.openapiRequiredFields.toString()));
        }
      }

      Set<Entry<String, JsonElement>> entries = jsonObj.entrySet();
      // check to see if the JSON string contains additional fields
      for (Entry<String, JsonElement> entry : entries) {
        if (!DecisionLogResponseList.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `DecisionLogResponseList` properties. JSON: %s", entry.getKey(), jsonObj.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : DecisionLogResponseList.openapiRequiredFields) {
        if (jsonObj.get(requiredField) == null) {
          throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonObj.toString()));
        }
      }
      if ((jsonObj.get("url") != null && !jsonObj.get("url").isJsonNull()) && !jsonObj.get("url").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `url` to be a primitive type in the JSON string but got `%s`", jsonObj.get("url").toString()));
      }
      if (jsonObj.get("data") != null && !jsonObj.get("data").isJsonNull()) {
        JsonArray jsonArraydata = jsonObj.getAsJsonArray("data");
        if (jsonArraydata != null) {
          // ensure the json data is an array
          if (!jsonObj.get("data").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `data` to be an array in the JSON string but got `%s`", jsonObj.get("data").toString()));
          }

          // validate the optional field `data` (array)
          for (int i = 0; i < jsonArraydata.size(); i++) {
            DecisionLogResponse.validateJsonObject(jsonArraydata.get(i).getAsJsonObject());
          };
        }
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!DecisionLogResponseList.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'DecisionLogResponseList' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<DecisionLogResponseList> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(DecisionLogResponseList.class));

       return (TypeAdapter<T>) new TypeAdapter<DecisionLogResponseList>() {
           @Override
           public void write(JsonWriter out, DecisionLogResponseList value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public DecisionLogResponseList read(JsonReader in) throws IOException {
             JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
             validateJsonObject(jsonObj);
             return thisAdapter.fromJsonTree(jsonObj);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of DecisionLogResponseList given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of DecisionLogResponseList
  * @throws IOException if the JSON string is invalid with respect to DecisionLogResponseList
  */
  public static DecisionLogResponseList fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, DecisionLogResponseList.class);
  }

 /**
  * Convert an instance of DecisionLogResponseList to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

