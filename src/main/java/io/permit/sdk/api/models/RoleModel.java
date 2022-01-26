package io.permit.sdk.api.models;

import java.util.ArrayList;
import java.util.HashMap;

public class RoleModel {
    public String id = null;
    public String name = null;
    public String description = null;
    public HashMap<String, String> attributes = null;
    public HashMap<String, String> settings = null;
    public Boolean isBuiltIn = null;
    public ArrayList<PermissionModel> permissions = null;
}
