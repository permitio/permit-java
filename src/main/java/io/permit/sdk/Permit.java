package io.permit.sdk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.permit.sdk.api.ApiClient;
import io.permit.sdk.api.ElementsClient;
import io.permit.sdk.enforcement.Enforcer;
import io.permit.sdk.enforcement.IEnforcerApi;
import io.permit.sdk.enforcement.Resource;
import io.permit.sdk.enforcement.User;
import io.permit.sdk.util.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Permit implements IEnforcerApi {
    final static Logger logger = LoggerFactory.getLogger(Permit.class);
    private final Enforcer enforcer;
    public final PermitConfig config;
    public final ApiClient api;
    public final ElementsClient elements;

    public Permit(PermitConfig config) {
        this.config = config;
        this.api = new ApiClient(this.config);
        this.elements = new ElementsClient(this.config);
        this.enforcer = new Enforcer(this.config);

        if (this.config.isDebugMode()) {
            Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
            logger.info(String.format("Permit.io SDK initialized with config:\n%s", gson.toJson(this.config)));
        }
    }

    @Override
    public boolean check(User user, String action, Resource resource, Context context) throws IOException {
        return this.enforcer.check(user, action, resource, context);
    }

    @Override
    public boolean check(User user, String action, Resource resource) throws IOException {
        return this.enforcer.check(user, action, resource);
    }
}
