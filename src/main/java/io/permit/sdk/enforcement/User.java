package io.permit.sdk.enforcement;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String key;
    private String firstName = null;
    private String lastName = null;
    private String email = null;
    private ArrayList<AssignedRole> roles = null;
    private HashMap<String, String> attributes = null;

    public User(Builder builder) {
        this.key = builder.key;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.roles = builder.roles;
        this.attributes = builder.attributes;
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

    public static class Builder {
        private String key;
        private String firstName = null;
        private String lastName = null;
        private String email = null;
        private ArrayList<AssignedRole> roles = null;
        private HashMap<String, String> attributes = null;

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

        public Builder withRoles(ArrayList<AssignedRole> roles) {
            this.roles = roles;
            return this;
        }

        public Builder withAttributes(HashMap<String, String> attributes) {
            this.attributes = attributes;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
