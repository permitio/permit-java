package io.permit.sdk.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.permit.sdk.openapi.models.EmbeddedLoginRequestOutput;

import java.util.HashMap;
import java.util.Map;

public class ElementsLoginResult extends EmbeddedLoginRequestOutput {
    @SerializedName("content")
    @Expose
    public Map<String, String> content;

    public ElementsLoginResult() {
        content = new HashMap<String, String>();
    }

    /**
     * @param redirectUrl The URL to redirect the user to after login
     */
    public ElementsLoginResult(String redirectUrl) {
        super(redirectUrl);
        content = new HashMap<String, String>();
    }

    public ElementsLoginResult withContent(Map<String, String> content) {
        this.content = content;
        return this;
    }
}
