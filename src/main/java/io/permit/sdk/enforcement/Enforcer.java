package io.permit.sdk.enforcement;

import io.permit.sdk.PermitConfig;

import io.permit.sdk.util.Context;
import io.permit.sdk.util.ContextStore;

import java.util.ArrayList;

public class Enforcer {
    public final ContextStore contextStore = new ContextStore();


    public Enforcer(PermitConfig config) {

    }

    public Boolean check(User user, String action, Resource resource, Context context) {
        return false;
    }
}