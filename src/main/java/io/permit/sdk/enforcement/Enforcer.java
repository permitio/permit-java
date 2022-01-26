package io.permit.sdk.enforcement;

import com.google.gson.Gson;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.util.Context;
import io.permit.sdk.util.ContextStore;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class EnforcerInput {
    public final String user;
    public final String action;
    public final Resource resource;
    public final HashMap<String, String> context;

    EnforcerInput(String user, String action, Resource resource, HashMap<String, String> context) {
        this.user = user;
        this.action = action;
        this.resource = resource;
        this.context = context;
    }
}

class OpaResult {
    public final Boolean allow;

    OpaResult(Boolean allow) {
        this.allow = allow;
    }
}

public class Enforcer implements IEnforcerApi {
    final static Logger logger = LoggerFactory.getLogger(Enforcer.class);
    public final ContextStore contextStore = new ContextStore();
    private final OkHttpClient client = new OkHttpClient();
    private final PermitConfig config;

    public Enforcer(PermitConfig config) {
        this.config = config;
    }

    @Override
    public boolean check(User user, String action, Resource resource, Context context) throws IOException {
        Resource normalizedResource = resource.normalize(this.config);
        Context queryContext = this.contextStore.getDerivedContext(context);

        EnforcerInput input = new EnforcerInput(
            user.getKey(),
            action,
            normalizedResource,
            queryContext
        );

        // request body
        Gson gson = new Gson();
        String requestBody = gson.toJson(input);
        RequestBody body = RequestBody.create(requestBody, MediaType.parse("application/json"));

        // create the request
        String url = String.format("%s/allowed", this.config.getPdpAddress());
        Request request = new Request.Builder()
            .url(url)
            .post(body)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", String.format("Bearer %s", this.config.getToken()))
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                logger.error(String.format(
                        "Error in permit.check(%s, %s, %s): got unexpected status code %d",
                        user,
                        action,
                        resource,
                        response.code()
                ));
                return false;
            }
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                logger.error(String.format(
                        "Error in permit.check(%s, %s, %s): got empty response",
                        user,
                        action,
                        resource
                ));
                return false;
            }
            String responseString = responseBody.string();
            OpaResult result = gson.fromJson(responseString, OpaResult.class);
            if (this.config.isDebugMode()) {
                logger.info(String.format(
                        "permit.check(%s, %s, %s) = %s",
                        user,
                        action,
                        resource,
                        result.allow.toString()
                ));
            }
            return result.allow;
        }
    }

    @Override
    public boolean check(User user, String action, Resource resource) throws IOException {
        return this.check(user, action, resource, new Context());
    }
}