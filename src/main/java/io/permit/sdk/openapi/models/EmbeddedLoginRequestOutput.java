
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * EmbeddedLoginRequestOutput
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class EmbeddedLoginRequestOutput {

    /**
     * Error
     * <p>
     * If the login request failed, this field will contain the error message
     * 
     */
    @SerializedName("error")
    @Expose
    public String error;
    /**
     * Error Code
     * <p>
     * If the login request failed, this field will contain the error code
     * 
     */
    @SerializedName("error_code")
    @Expose
    public Integer errorCode;
    /**
     * Token
     * <p>
     * The auth token that lets your users login into permit elements
     * 
     */
    @SerializedName("token")
    @Expose
    public String token;
    /**
     * Extra
     * <p>
     * Extra data that you can pass to the login request
     * 
     */
    @SerializedName("extra")
    @Expose
    public String extra;
    /**
     * Redirect Url
     * <p>
     * The full URL to which the user should be redirected in order to complete the login process
     * (Required)
     * 
     */
    @SerializedName("redirect_url")
    @Expose
    public String redirectUrl;

    /**
     * No args constructor for use in serialization
     * 
     */
    public EmbeddedLoginRequestOutput() {
    }

    /**
     * 
     * @param redirectUrl
     */
    public EmbeddedLoginRequestOutput(String redirectUrl) {
        super();
        this.redirectUrl = redirectUrl;
    }

    public EmbeddedLoginRequestOutput withError(String error) {
        this.error = error;
        return this;
    }

    public EmbeddedLoginRequestOutput withErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public EmbeddedLoginRequestOutput withToken(String token) {
        this.token = token;
        return this;
    }

    public EmbeddedLoginRequestOutput withExtra(String extra) {
        this.extra = extra;
        return this;
    }

    public EmbeddedLoginRequestOutput withRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
        return this;
    }

}
