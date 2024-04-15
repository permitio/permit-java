package io.permit.sdk.api.models;

import java.util.Map;

public class UserLoginResponse {
    public String error = null;
    public String token = null;
    public String extra = null;
    public String redirectUrl = null;
    public Map<String, String> content = null;
}
