package io.permit.sdk.enforcement;

import com.google.gson.Gson;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.api.HttpLoggingInterceptor;
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

/**
* The {@code EnforcerInput} class represents the input data for the Permit PDP enforcement API.
* It is serialized as the HTTP JSON body of the permit.check request.
*/
class EnforcerInput {
    public final User user;
    public final String action;
    public final Resource resource;
    public final HashMap<String, Object> context;

    /**
    * Constructs a new instance of the {@code EnforcerInput} class with the specified data.
    *
    * @param user     The user performing the action.
    * @param action   The action to be performed.
    * @param resource The resource on which the action is performed.
    * @param context  The context for the authorization check.
    */
    EnforcerInput(User user, String action, Resource resource, HashMap<String, Object> context) {
        this.user = user;
        this.action = action;
        this.resource = resource;
        this.context = context;
    }
}

/**
* The {@code OpaResult} class represents the result of a Permit enforcement check returned by the policy agent.
*/
class OpaResult {
    public final Boolean allow;

    /**
    * Constructs a new instance of the {@code OpaResult} class with the specified result.
    *
    * @param allow {@code true} if the action is allowed, {@code false} otherwise.
    */
    OpaResult(Boolean allow) {
        this.allow = allow;
    }
}

/**
 * The {@code Enforcer} class is responsible for performing permission checks against the PDP.
 * It implements the {@link IEnforcerApi} interface.
 */
public class Enforcer implements IEnforcerApi {
    final static Logger logger = LoggerFactory.getLogger(Enforcer.class);
    public final ContextStore contextStore = new ContextStore();
    private final OkHttpClient client;
    private final PermitConfig config;

    /**
     * Constructs a new instance of the {@code Enforcer} class with the specified configuration.
     *
     * @param config The Permit SDK configuration.
     */
    public Enforcer(PermitConfig config) {
        this.config = config;
        this.client = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor(logger, config))
            .build();
    }

    /**
     * Checks whether the specified user is allowed to perform the given action on the given resource
     * with the provided context.
     *
     * @param user     The user performing the action.
     * @param action   The action to be performed.
     * @param resource The resource on which the action is performed.
     * @param context  The context for the authorization check.
     * @return {@code true} if the user is allowed to perform the action, {@code false} otherwise.
     * @throws IOException If an error occurs during the authorization check.
     */
    @Override
    public boolean check(User user, String action, Resource resource, Context context) throws IOException {
        Resource normalizedResource = resource.normalize(this.config);
        Context queryContext = this.contextStore.getDerivedContext(context);

        EnforcerInput input = new EnforcerInput(
            user,
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
                String errorMessage = String.format(
                    "Error in permit.check(%s, %s, %s): got unexpected status code %d",
                    user.toString(),
                    action,
                    resource,
                    response.code()
                );
                logger.error(errorMessage);
                throw new IOException(errorMessage);
            }
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                String errorMessage = String.format(
                    "Error in permit.check(%s, %s, %s): got empty response",
                    user,
                    action,
                    resource
                );
                logger.error(errorMessage);
                throw new IOException(errorMessage);
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

    /**
    * Checks whether the specified user is allowed to perform the given action on the given resource
    * without any additional context.
    *
    * @param user     The user performing the action.
    * @param action   The action to be performed.
    * @param resource The resource on which the action is performed.
    * @return {@code true} if the user is allowed to perform the action, {@code false} otherwise.
    * @throws IOException If an error occurs during the authorization check.
    */
    @Override
    public boolean check(User user, String action, Resource resource) throws IOException {
        return this.check(user, action, resource, new Context());
    }
}