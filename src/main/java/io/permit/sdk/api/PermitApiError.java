package io.permit.sdk.api;

import com.google.gson.Gson;

public class PermitApiError extends Throwable {
    final private int responseCode;
    final private String rawResponse;

    public PermitApiError(String s, int responseCode, String responseString) {
        super(s);
        this.responseCode = responseCode;
        this.rawResponse = responseString;
    }

    public int getResponseCode() { return responseCode; }

    public String getRawResponse() { return rawResponse; }

    public Object getErrorObject () {
        return (new Gson()).fromJson(rawResponse, Object.class);
    }
}
