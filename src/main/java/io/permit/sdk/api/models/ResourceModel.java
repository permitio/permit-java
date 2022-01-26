package io.permit.sdk.api.models;

import java.util.ArrayList;
import java.util.HashMap;

public class ResourceModel {
    public String id = null;
    public String type = null;
    public String name = null;
    public String path = null;
    public String description = null;
    public HashMap<String, String> attributes = null;
    public Boolean isBuiltIn = null;
    public ArrayList<ActionModel> actions = null;
}
