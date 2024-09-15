package io.permit.sdk.enforcement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.permit.sdk.openapi.models.UserRoleCreate;

public class User {
    private String key;
    private String firstName = null;
    private String lastName = null;
    private String email = null;
    private HashMap<String, Object> attributes = null;
    private List<UserRoleCreate> roleAssignments = null;

    public User(Builder builder) {
        this.key = builder.key;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.attributes = builder.attributes;
        this.roleAssignments = builder.roleAssignments;
    }

    public String getKey() {
        return this.key;
    }

    public String toString() {
        return this.key;
    }

    public static User fromString(String userKey) {
        return new User(new User.Builder(userKey));
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public HashMap<String, Object> getAttributes() {
        return attributes;
    }

    public static class Builder {
        private final String key;
        private String firstName = null;
        private String lastName = null;
        private String email = null;
        private HashMap<String, Object> attributes = null;
        private List<UserRoleCreate> roleAssignments;

        public Builder(String userKey) {
            this.key = userKey;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withAttributes(HashMap<String, Object> attributes) {
            this.attributes = attributes;
            return this;
        }

        public Builder withRoleAssignments(List<UserRoleCreate> roleAssignments) {
            this.roleAssignments = roleAssignments;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
