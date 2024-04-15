package io.permit.sdk.api.models;

import java.util.ArrayList;
import java.util.HashMap;

public class UserModel {
    public String id = null;
    public String customId = null;
    public String firstName = null;
    public String lastName = null;
    public String email = null;
    public HashMap<String, Object> attributes = null;
    public String organizationId = null;
    public ArrayList<String> tenantIds = null;
}
