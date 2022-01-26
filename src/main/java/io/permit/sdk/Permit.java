package io.permit.sdk;

import io.permit.sdk.api.ApiClient;
import io.permit.sdk.enforcement.Enforcer;
import io.permit.sdk.enforcement.IEnforcerApi;
import io.permit.sdk.enforcement.Resource;
import io.permit.sdk.enforcement.User;
import io.permit.sdk.util.Context;

public class Permit implements IEnforcerApi {
    private final Enforcer enforcer;
    public final PermitConfig config;
    public final ApiClient api;

    public Permit(PermitConfig config) {
        this.config = config;
        this.api = new ApiClient(this.config);
        this.enforcer = new Enforcer(this.config);
    }

    @Override
    public boolean check(User user, String action, Resource resource, Context context) {
        return this.enforcer.check(user, action, resource, context);
    }

    @Override
    public boolean check(User user, String action, Resource resource) {
        return this.enforcer.check(user, action, resource);
    }
}
