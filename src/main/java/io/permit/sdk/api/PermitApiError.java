package io.permit.sdk.api;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

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

    public LinkedTreeMap getErrorObject () {
        return (new Gson()).fromJson(rawResponse, LinkedTreeMap.class);
    }
}
