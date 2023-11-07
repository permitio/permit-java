package io.permit.sdk.enforcement;

import com.google.common.primitives.Booleans;
import com.google.gson.Gson;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.api.HttpLoggingInterceptor;
import io.permit.sdk.api.PermitApiError;
import io.permit.sdk.openapi.models.ConditionSetRuleRead;
import io.permit.sdk.util.Context;
import io.permit.sdk.util.ContextStore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
    public final Context context;

    /**
    * Constructs a new instance of the {@code EnforcerInput} class with the specified data.
    *
    * @param user     The user performing the action.
    * @param action   The action to be performed.
    * @param resource The resource on which the action is performed.
    * @param context  The context for the authorization check.
    */
    EnforcerInput(User user, String action, Resource resource, Context context) {
        this.user = user;
        this.action = action;
        this.resource = resource;
        this.context = context;
    }
}

class CheckUrlInput {
    public final User user;
    public final String http_method;
    public final String url;
    public final String tenant;
    public final HashMap<String, Object> context;

    CheckUrlInput(User user, String http_method, String url, String tenant, HashMap<String, Object> context) {
        this.user = user;
        this.http_method = http_method;
        this.url = url;
        this.tenant = tenant;
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
 * The {@code TenantResult} class represents a single tenant returned by the checkInAllTenants query.
 */
class TenantResult {
    public final Boolean allow;

    public final TenantDetails tenant;

    public TenantResult(Boolean allow, TenantDetails tenant) {
        this.allow = allow;
        this.tenant = tenant;
    }
}

/**
 * The {@code AllTenantsResult} class represents the result of the checkInAllTenants query.
 */
class AllTenantsResult {
    public final TenantResult[] allowed_tenants;

    public AllTenantsResult(TenantResult[] allowed_tenants) {
        this.allowed_tenants = allowed_tenants;
    }
}

/**
 * The {@code OpaBulkResult} class represents the result of a Permit bulk enforcement check returned by the policy agent.
 */
class OpaBulkResult {
    public final List<OpaResult> allow;

    /**
     * Constructs a new instance of the {@code OpaResult} class with the specified result.
     *
     * @param allow {@code true} if the action is allowed, {@code false} otherwise.
     */
    OpaBulkResult(List<OpaResult> allow) {
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

    private <T> T callApiAndParseJson(Request request, String requestRepr, Class<T> modelClass) throws IOException, PermitApiError {
        try (Response response = client.newCall(request).execute()) {
            throwIfErrorResponseCode(response, requestRepr);
            String responseString = processResponseBody(response, requestRepr);
            return (new Gson()).fromJson(responseString, modelClass);
        }
    }

    private void throwIfErrorResponseCode(Response response, String requestRepr) throws PermitApiError, IOException {
        if (!response.isSuccessful()) {
            String errorMessage = String.format(
                "Error in %s: got unexpected status code %d",
                requestRepr,
                response.code()
            );
            String responseContent = "";

            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                responseContent = responseBody.string();
                errorMessage = String.format(
                    "%s and error: %s",
                    errorMessage,
                    responseContent
                );
            }

            logger.error(errorMessage);
            throw new PermitApiError(
                    errorMessage,
                    response.code(),
                    responseContent
            );
        }
    }

    private String processResponseBody(Response response, String requestRepr) throws IOException, PermitApiError {
        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            String errorMessage = String.format(
                    "Error in %s: got empty response",
                    requestRepr
            );
            logger.error(errorMessage);
            throw new IOException(errorMessage);
        }
        return responseBody.string();
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
    public boolean check(User user, String action, Resource resource, Context context) throws IOException, PermitApiError {
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
            .addHeader("X-Permit-SDK-Version", String.format("java:%s", this.config.version))
            .addHeader("X-Tenant-ID", normalizedResource.getTenant()) // sharding key
            .build();

        String requestRepr = String.format(
                "permit.check(%s, %s, %s)",
                user.toString(),
                action,
                resource
        );

        OpaResult result = this.callApiAndParseJson(request, requestRepr, OpaResult.class);
        if (this.config.isDebugMode()) {
            logger.info(String.format(
                    "%s = %s",
                    requestRepr,
                    result.allow.toString()
            ));
        }
        return result.allow;
    }

    /**
    * Checks whether the specified user is allowed to perform the given action on the given resource
    * without any additional context.
    *
    * @param user     The user performing the action.
    * @param action   The action to be performed.
    * @param resource The resource on which the action is performed.
    * @return {@code true} if the user is allowed to perform the action, {@code false} otherwise.
    * @throws PermitApiError if an error occurs while sending the authorization request to the PDP.
    * @throws IOException if could not read the content of the returned http response.
    */
    @Override
    public boolean check(User user, String action, Resource resource) throws IOException, PermitApiError {
        return this.check(user, action, resource, new Context());
    }

    @Override
    public boolean checkUrl(User user, String httpMethod, String url, String tenant, Context context) throws IOException, PermitApiError {
        CheckUrlInput input = new CheckUrlInput(
                user,
                httpMethod,
                url,
                tenant,
                context
        );

        // request body
        Gson gson = new Gson();
        String requestBody = gson.toJson(input);
        RequestBody body = RequestBody.create(requestBody, MediaType.parse("application/json"));

        // create the request
        String apiUrl = String.format("%s/allowed_url", this.config.getPdpAddress());
        Request request = new Request.Builder()
                .url(apiUrl)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", String.format("Bearer %s", this.config.getToken()))
                .addHeader("X-Permit-SDK-Version", String.format("java:%s", this.config.version))
                .addHeader("X-Tenant-ID", tenant) // sharding key
                .build();

        String requestRepr = String.format(
                "permit.checkUrl(%s, %s, %s, %s)",
                user.toString(),
                httpMethod,
                url,
                tenant
        );

        OpaResult result = this.callApiAndParseJson(request, requestRepr, OpaResult.class);
        if (this.config.isDebugMode()) {
            logger.info(String.format(
                    "%s = %s",
                    requestRepr,
                    result.allow.toString()
            ));
        }
        return result.allow;
    }

    @Override
    public boolean[] bulkCheck(List<CheckQuery> checks) throws IOException, PermitApiError {
        List<EnforcerInput> inputs = new ArrayList<>();

        for (CheckQuery check: checks) {
            Resource normalizedResource = check.resource.normalize(this.config);
            inputs.add(new EnforcerInput(check.user, check.action, normalizedResource, check.context));
        }

        // request body
        Gson gson = new Gson();
        String requestBody = gson.toJson(inputs);
        RequestBody body = RequestBody.create(requestBody, MediaType.parse("application/json"));

        // create the request
        String url = String.format("%s/allowed/bulk", this.config.getPdpAddress());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", String.format("Bearer %s", this.config.getToken()))
                .addHeader("X-Permit-SDK-Version", String.format("java:%s", this.config.version))
                .build();

        String requestRepr = bulkCheckRepr(inputs);

        OpaBulkResult result = this.callApiAndParseJson(request, requestRepr, OpaBulkResult.class);
        if (this.config.isDebugMode()) {
            for (int i = 0; i < result.allow.size(); i++) {
                logger.info(String.format(
                        "permit.bulkCheck[%d/%d](%s, %s, %s) = %s",
                        i + 1,
                        result.allow.size(),
                        inputs.get(i).user,
                        inputs.get(i).action,
                        inputs.get(i).resource,
                        result.allow.get(i).allow
                ));
            }

        }
        return Booleans.toArray(result.allow.stream().map(r -> r.allow).collect(Collectors.toList()));
    }

    @Override
    public List<TenantDetails> checkInAllTenants(User user, String action, Resource resource, Context context) throws IOException, PermitApiError {
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
        String url = String.format("%s/allowed/all-tenants", this.config.getPdpAddress());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", String.format("Bearer %s", this.config.getToken()))
                .addHeader("X-Permit-SDK-Version", String.format("java:%s", this.config.version))
                .build();

        String requestRepr = String.format(
                "permit.checkInAllTenants(%s, %s, %s)",
                user.toString(),
                action,
                resource
        );

        AllTenantsResult result = this.callApiAndParseJson(request, requestRepr, AllTenantsResult.class);
        List<TenantDetails> tenants = Arrays.stream(result.allowed_tenants).map(r -> r.tenant).collect(Collectors.toList());
        if (this.config.isDebugMode()) {
            logger.info(String.format(
                    "permit.checkInAllTenants(%s, %s, %s) => allowed in: [%s]",
                    user,
                    action,
                    resource,
                    tenants.stream().map(t -> t.key).collect(Collectors.joining(", "))
            ));
        }
        return tenants;
    }

    @Override
    public List<TenantDetails> checkInAllTenants(User user, String action, Resource resource) throws IOException, PermitApiError {
        return checkInAllTenants(user, action, resource, new Context());
    }

    @Override
    public UserPermissions getUserPermissions(GetUserPermissionsQuery input) throws IOException, PermitApiError {
        // request body
        Gson gson = new Gson();
        String requestBody = gson.toJson(input);
        RequestBody body = RequestBody.create(requestBody, MediaType.parse("application/json"));

        // create the request
        String url = String.format("%s/user-permissions", this.config.getPdpAddress());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", String.format("Bearer %s", this.config.getToken()))
                .addHeader("X-Permit-SDK-Version", String.format("java:%s", this.config.version))
                .build();

        String requestRepr = String.format(
                "permit.getUserPermissions(%s, %s, %s, %s)",
                input.user.toString(),
                input.tenants != null ? input.tenants.toString() : "null",
                input.resource_types != null ? input.resource_types.toString() : "null",
                input.resources != null ? input.resources.toString() : "null"
        );

        UserPermissions result = this.callApiAndParseJson(request, requestRepr, UserPermissions.class);
        if (this.config.isDebugMode()) {
            logger.info(String.format(
                    "%s => returned %d permissions on %d objects",
                    requestRepr,
                    result.values().stream().map(obj -> obj.permissions.size()).reduce(0, Integer::sum),
                    result.keySet().size()
            ));
        }
        return result;
    }

    private List<TenantDetails> getUserTenants(GetUserTenantsQuery input) throws IOException, PermitApiError {
        // request body
        Gson gson = new Gson();
        String requestBody = gson.toJson(input);
        RequestBody body = RequestBody.create(requestBody, MediaType.parse("application/json"));

        // create the request
        String url = String.format("%s/user-tenants", this.config.getPdpAddress());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", String.format("Bearer %s", this.config.getToken()))
                .addHeader("X-Permit-SDK-Version", String.format("java:%s", this.config.version))
                .build();

        String requestRepr = String.format(
                "permit.getUserTenants(%s)",
                input.user.toString()
        );

        TenantDetails[] result = this.callApiAndParseJson(request, requestRepr, TenantDetails[].class);
        List<TenantDetails> tenants = Arrays.stream(result).collect(Collectors.toList());
        if (this.config.isDebugMode()) {
            logger.info(String.format(
                    "%s => allowed in: [%s]",
                    requestRepr,
                    tenants.stream().map(t -> t.key).collect(Collectors.joining(", "))
            ));
        }
        return tenants;
    }

    @Override
    public List<TenantDetails> getUserTenants(User user) throws IOException, PermitApiError {
        return this.getUserTenants(new GetUserTenantsQuery(user));
    }

    @Override
    public List<TenantDetails> getUserTenants(User user, Context context) throws IOException, PermitApiError {
        return this.getUserTenants(new GetUserTenantsQuery(user, context));
    }

    @Override
    public boolean checkUrl(User user, String httpMethod, String url, String tenant) throws IOException, PermitApiError {
        return this.checkUrl(user, httpMethod, url, tenant, new Context());
    }

    private String bulkCheckRepr(List<EnforcerInput> inputs) {
        return String.format(
            "permit.bulkCheck(%s)",
            inputs.stream().map(i -> String.format(
                    "%s, %s, %s, %s",
                    i.user,
                    i.action,
                    i.resource,
                    i.context
            )).collect(Collectors.toList())
        );
    }
}