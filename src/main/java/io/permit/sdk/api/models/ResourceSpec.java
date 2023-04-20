package io.permit.sdk.api.models;

import java.util.HashMap;

public class ResourceSpec {
    private String type;
    private String description;
    private HashMap<String, Object> attributes;
    private HashMap<String, ActionSpec> actions = new HashMap<>();

    public ResourceSpec(String type, String description, HashMap<String, Object> attributes, HashMap<String, ActionSpec> actions) {
        this.type = type;
        this.description = description;
        this.attributes = attributes;
        this.actions = actions;
    }

    public ResourceSpec(String type, String description, HashMap<String, Object> attributes) {
        this(type, description, attributes, new HashMap<>());
    }

    public ResourceSpec(String type, HashMap<String, ActionSpec> actions) {
        this(type, null, null, actions);
    }
}