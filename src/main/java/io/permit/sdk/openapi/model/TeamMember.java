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
import io.permit.sdk.openapi.model.OnboardingStep;
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
 * these fields should not be editable once the object is created in db
 */
@ApiModel(description = "these fields should not be editable once the object is created in db")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-10-13T15:32:07.306280+03:00[Asia/Jerusalem]")
public class TeamMember {
  public static final String SERIALIZED_NAME_USER_ID = "userId";
  @SerializedName(SERIALIZED_NAME_USER_ID)
  private String userId;

  public static final String SERIALIZED_NAME_EMAIL = "email";
  @SerializedName(SERIALIZED_NAME_EMAIL)
  private String email;

  public static final String SERIALIZED_NAME_FIRST_NAME = "firstName";
  @SerializedName(SERIALIZED_NAME_FIRST_NAME)
  private String firstName;

  public static final String SERIALIZED_NAME_LAST_NAME = "lastName";
  @SerializedName(SERIALIZED_NAME_LAST_NAME)
  private String lastName;

  public static final String SERIALIZED_NAME_IDP_METADATA = "idpMetadata";
  @SerializedName(SERIALIZED_NAME_IDP_METADATA)
  private Object idpMetadata;

  public static final String SERIALIZED_NAME_SETTINGS = "settings";
  @SerializedName(SERIALIZED_NAME_SETTINGS)
  private Object settings;

  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private UUID id;

  public static final String SERIALIZED_NAME_CREATED_AT = "createdAt";
  @SerializedName(SERIALIZED_NAME_CREATED_AT)
  private OffsetDateTime createdAt;

  public static final String SERIALIZED_NAME_UPDATED_AT = "updatedAt";
  @SerializedName(SERIALIZED_NAME_UPDATED_AT)
  private OffsetDateTime updatedAt;

  public static final String SERIALIZED_NAME_IS_SUPERUSER = "isSuperuser";
  @SerializedName(SERIALIZED_NAME_IS_SUPERUSER)
  private Boolean isSuperuser;

  public static final String SERIALIZED_NAME_LAST_IP = "lastIp";
  @SerializedName(SERIALIZED_NAME_LAST_IP)
  private String lastIp;

  public static final String SERIALIZED_NAME_ORGANIZATION_IDS = "organizationIds";
  @SerializedName(SERIALIZED_NAME_ORGANIZATION_IDS)
  private List<UUID> organizationIds = null;

  public static final String SERIALIZED_NAME_IS_ONBOARDING = "isOnboarding";
  @SerializedName(SERIALIZED_NAME_IS_ONBOARDING)
  private Boolean isOnboarding;

  public static final String SERIALIZED_NAME_ONBOARDING_STEP = "onboardingStep";
  @SerializedName(SERIALIZED_NAME_ONBOARDING_STEP)
  private OnboardingStep onboardingStep;

  public TeamMember() {
  }

  public TeamMember userId(String userId) {
    
    this.userId = userId;
    return this;
  }

   /**
   * Get userId
   * @return userId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getUserId() {
    return userId;
  }


  public void setUserId(String userId) {
    this.userId = userId;
  }


  public TeamMember email(String email) {
    
    this.email = email;
    return this;
  }

   /**
   * Get email
   * @return email
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public TeamMember firstName(String firstName) {
    
    this.firstName = firstName;
    return this;
  }

   /**
   * Get firstName
   * @return firstName
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getFirstName() {
    return firstName;
  }


  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }


  public TeamMember lastName(String lastName) {
    
    this.lastName = lastName;
    return this;
  }

   /**
   * Get lastName
   * @return lastName
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public String getLastName() {
    return lastName;
  }


  public void setLastName(String lastName) {
    this.lastName = lastName;
  }


  public TeamMember idpMetadata(Object idpMetadata) {
    
    this.idpMetadata = idpMetadata;
    return this;
  }

   /**
   * Get idpMetadata
   * @return idpMetadata
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Object getIdpMetadata() {
    return idpMetadata;
  }


  public void setIdpMetadata(Object idpMetadata) {
    this.idpMetadata = idpMetadata;
  }


  public TeamMember settings(Object settings) {
    
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


  public TeamMember id(UUID id) {
    
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public UUID getId() {
    return id;
  }


  public void setId(UUID id) {
    this.id = id;
  }


  public TeamMember createdAt(OffsetDateTime createdAt) {
    
    this.createdAt = createdAt;
    return this;
  }

   /**
   * Get createdAt
   * @return createdAt
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }


  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }


  public TeamMember updatedAt(OffsetDateTime updatedAt) {
    
    this.updatedAt = updatedAt;
    return this;
  }

   /**
   * Get updatedAt
   * @return updatedAt
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }


  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }


  public TeamMember isSuperuser(Boolean isSuperuser) {
    
    this.isSuperuser = isSuperuser;
    return this;
  }

   /**
   * Get isSuperuser
   * @return isSuperuser
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public Boolean getIsSuperuser() {
    return isSuperuser;
  }


  public void setIsSuperuser(Boolean isSuperuser) {
    this.isSuperuser = isSuperuser;
  }


  public TeamMember lastIp(String lastIp) {
    
    this.lastIp = lastIp;
    return this;
  }

   /**
   * Get lastIp
   * @return lastIp
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getLastIp() {
    return lastIp;
  }


  public void setLastIp(String lastIp) {
    this.lastIp = lastIp;
  }


  public TeamMember organizationIds(List<UUID> organizationIds) {
    
    this.organizationIds = organizationIds;
    return this;
  }

  public TeamMember addOrganizationIdsItem(UUID organizationIdsItem) {
    if (this.organizationIds == null) {
      this.organizationIds = new ArrayList<>();
    }
    this.organizationIds.add(organizationIdsItem);
    return this;
  }

   /**
   * Get organizationIds
   * @return organizationIds
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<UUID> getOrganizationIds() {
    return organizationIds;
  }


  public void setOrganizationIds(List<UUID> organizationIds) {
    this.organizationIds = organizationIds;
  }


  public TeamMember isOnboarding(Boolean isOnboarding) {
    
    this.isOnboarding = isOnboarding;
    return this;
  }

   /**
   * Get isOnboarding
   * @return isOnboarding
  **/
  @javax.annotation.Nonnull
  @ApiModelProperty(required = true, value = "")

  public Boolean getIsOnboarding() {
    return isOnboarding;
  }


  public void setIsOnboarding(Boolean isOnboarding) {
    this.isOnboarding = isOnboarding;
  }


  public TeamMember onboardingStep(OnboardingStep onboardingStep) {
    
    this.onboardingStep = onboardingStep;
    return this;
  }

   /**
   * Get onboardingStep
   * @return onboardingStep
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public OnboardingStep getOnboardingStep() {
    return onboardingStep;
  }


  public void setOnboardingStep(OnboardingStep onboardingStep) {
    this.onboardingStep = onboardingStep;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TeamMember teamMember = (TeamMember) o;
    return Objects.equals(this.userId, teamMember.userId) &&
        Objects.equals(this.email, teamMember.email) &&
        Objects.equals(this.firstName, teamMember.firstName) &&
        Objects.equals(this.lastName, teamMember.lastName) &&
        Objects.equals(this.idpMetadata, teamMember.idpMetadata) &&
        Objects.equals(this.settings, teamMember.settings) &&
        Objects.equals(this.id, teamMember.id) &&
        Objects.equals(this.createdAt, teamMember.createdAt) &&
        Objects.equals(this.updatedAt, teamMember.updatedAt) &&
        Objects.equals(this.isSuperuser, teamMember.isSuperuser) &&
        Objects.equals(this.lastIp, teamMember.lastIp) &&
        Objects.equals(this.organizationIds, teamMember.organizationIds) &&
        Objects.equals(this.isOnboarding, teamMember.isOnboarding) &&
        Objects.equals(this.onboardingStep, teamMember.onboardingStep);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, email, firstName, lastName, idpMetadata, settings, id, createdAt, updatedAt, isSuperuser, lastIp, organizationIds, isOnboarding, onboardingStep);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TeamMember {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    idpMetadata: ").append(toIndentedString(idpMetadata)).append("\n");
    sb.append("    settings: ").append(toIndentedString(settings)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("    isSuperuser: ").append(toIndentedString(isSuperuser)).append("\n");
    sb.append("    lastIp: ").append(toIndentedString(lastIp)).append("\n");
    sb.append("    organizationIds: ").append(toIndentedString(organizationIds)).append("\n");
    sb.append("    isOnboarding: ").append(toIndentedString(isOnboarding)).append("\n");
    sb.append("    onboardingStep: ").append(toIndentedString(onboardingStep)).append("\n");
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
    openapiFields.add("userId");
    openapiFields.add("email");
    openapiFields.add("firstName");
    openapiFields.add("lastName");
    openapiFields.add("idpMetadata");
    openapiFields.add("settings");
    openapiFields.add("id");
    openapiFields.add("createdAt");
    openapiFields.add("updatedAt");
    openapiFields.add("isSuperuser");
    openapiFields.add("lastIp");
    openapiFields.add("organizationIds");
    openapiFields.add("isOnboarding");
    openapiFields.add("onboardingStep");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("email");
    openapiRequiredFields.add("firstName");
    openapiRequiredFields.add("lastName");
    openapiRequiredFields.add("id");
    openapiRequiredFields.add("createdAt");
    openapiRequiredFields.add("updatedAt");
    openapiRequiredFields.add("isSuperuser");
    openapiRequiredFields.add("isOnboarding");
  }

 /**
  * Validates the JSON Object and throws an exception if issues found
  *
  * @param jsonObj JSON Object
  * @throws IOException if the JSON Object is invalid with respect to TeamMember
  */
  public static void validateJsonObject(JsonObject jsonObj) throws IOException {
      if (jsonObj == null) {
        if (TeamMember.openapiRequiredFields.isEmpty()) {
          return;
        } else { // has required fields
          throw new IllegalArgumentException(String.format("The required field(s) %s in TeamMember is not found in the empty JSON string", TeamMember.openapiRequiredFields.toString()));
        }
      }

      Set<Entry<String, JsonElement>> entries = jsonObj.entrySet();
      // check to see if the JSON string contains additional fields
      for (Entry<String, JsonElement> entry : entries) {
        if (!TeamMember.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `TeamMember` properties. JSON: %s", entry.getKey(), jsonObj.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : TeamMember.openapiRequiredFields) {
        if (jsonObj.get(requiredField) == null) {
          throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonObj.toString()));
        }
      }
      if ((jsonObj.get("userId") != null && !jsonObj.get("userId").isJsonNull()) && !jsonObj.get("userId").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `userId` to be a primitive type in the JSON string but got `%s`", jsonObj.get("userId").toString()));
      }
      if ((jsonObj.get("email") != null && !jsonObj.get("email").isJsonNull()) && !jsonObj.get("email").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `email` to be a primitive type in the JSON string but got `%s`", jsonObj.get("email").toString()));
      }
      if ((jsonObj.get("firstName") != null && !jsonObj.get("firstName").isJsonNull()) && !jsonObj.get("firstName").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `firstName` to be a primitive type in the JSON string but got `%s`", jsonObj.get("firstName").toString()));
      }
      if ((jsonObj.get("lastName") != null && !jsonObj.get("lastName").isJsonNull()) && !jsonObj.get("lastName").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `lastName` to be a primitive type in the JSON string but got `%s`", jsonObj.get("lastName").toString()));
      }
      if ((jsonObj.get("id") != null && !jsonObj.get("id").isJsonNull()) && !jsonObj.get("id").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `id` to be a primitive type in the JSON string but got `%s`", jsonObj.get("id").toString()));
      }
      if ((jsonObj.get("lastIp") != null && !jsonObj.get("lastIp").isJsonNull()) && !jsonObj.get("lastIp").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `lastIp` to be a primitive type in the JSON string but got `%s`", jsonObj.get("lastIp").toString()));
      }
      // ensure the json data is an array
      if ((jsonObj.get("organizationIds") != null && !jsonObj.get("organizationIds").isJsonNull()) && !jsonObj.get("organizationIds").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `organizationIds` to be an array in the JSON string but got `%s`", jsonObj.get("organizationIds").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!TeamMember.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'TeamMember' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<TeamMember> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(TeamMember.class));

       return (TypeAdapter<T>) new TypeAdapter<TeamMember>() {
           @Override
           public void write(JsonWriter out, TeamMember value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public TeamMember read(JsonReader in) throws IOException {
             JsonObject jsonObj = elementAdapter.read(in).getAsJsonObject();
             validateJsonObject(jsonObj);
             return thisAdapter.fromJsonTree(jsonObj);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of TeamMember given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of TeamMember
  * @throws IOException if the JSON string is invalid with respect to TeamMember
  */
  public static TeamMember fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, TeamMember.class);
  }

 /**
  * Convert an instance of TeamMember to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

