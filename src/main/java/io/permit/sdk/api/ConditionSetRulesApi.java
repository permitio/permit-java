package io.permit.sdk.api;

import com.google.gson.Gson;
import io.permit.sdk.ApiKeyLevel;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.openapi.models.*;
import okhttp3.*;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * The {@code IConditionSetRulesApi} interface defines the contract for the Condition Set Rules API.
 * It provides methods for managing condition set rules.
 */
interface IConditionSetRulesApi {
    /**
     * Retrieves a list of condition set rules based on the specified filters.
     *
     * @param userSetKey     The user set key.
     * @param permissionKey  The permission key.
     * @param resourceSetKey The resource set key.
     * @param page           The page number.
     * @param perPage        The number of items per page.
     * @return An array of {@link ConditionSetRuleRead} objects.
     * @throws IOException          If an I/O error occurs while sending the request.
     * @throws PermitApiError       If the Permit API returns a response with an error status code.
     * @throws PermitContextError   If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    ConditionSetRuleRead[] list(String userSetKey, String permissionKey, String resourceSetKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError;

    /**
     * Retrieves a list of condition set rules based on the specified filters.
     *
     * @param userSetKey     The user set key.
     * @param permissionKey  The permission key.
     * @param resourceSetKey The resource set key.
     * @param page           The page number.
     * @return An array of {@link ConditionSetRuleRead} objects.
     * @throws IOException          If an I/O error occurs while sending the request.
     * @throws PermitApiError       If the Permit API returns a response with an error status code.
     * @throws PermitContextError   If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    ConditionSetRuleRead[] list(String userSetKey, String permissionKey, String resourceSetKey, int page) throws IOException, PermitApiError, PermitContextError;

    /**
     * Retrieves a list of condition set rules based on the specified filters.
     *
     * @param userSetKey     The user set key.
     * @param permissionKey  The permission key.
     * @param resourceSetKey The resource set key.
     * @return An array of {@link ConditionSetRuleRead} objects.
     * @throws IOException          If an I/O error occurs while sending the request.
     * @throws PermitApiError       If the Permit API returns a response with an error status code.
     * @throws PermitContextError   If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    ConditionSetRuleRead[] list(String userSetKey, String permissionKey, String resourceSetKey) throws IOException, PermitApiError, PermitContextError;

    /**
     * Retrieves all the condition set rules (no filters) with default pagination
     *
     * @return An array of {@link ConditionSetRuleRead} objects.
     * @throws IOException          If an I/O error occurs while sending the request.
     * @throws PermitApiError       If the Permit API returns a response with an error status code.
     * @throws PermitContextError   If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    ConditionSetRuleRead[] list() throws IOException, PermitApiError, PermitContextError;

    /**
     * Creates a new condition set rule.
     *
     * @param rule The condition set rule to create.
     * @return The created {@link ConditionSetRuleRead} object.
     * @throws IOException          If an I/O error occurs while sending the request.
     * @throws PermitApiError       If the Permit API returns a response with an error status code.
     * @throws PermitContextError   If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    ConditionSetRuleRead create(ConditionSetRuleCreate rule) throws IOException, PermitApiError, PermitContextError;

    /**
     * Deletes a condition set rule.
     *
     * @param rule The condition set rule to delete.
     * @throws IOException          If an I/O error occurs while sending the request.
     * @throws PermitApiError       If the Permit API returns a response with an error status code.
     * @throws PermitContextError   If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    void delete(ConditionSetRuleRemove rule) throws IOException, PermitApiError, PermitContextError;
}

/**
 * The {@code ConditionSetRulesApi} class is an implementation of the {@code IConditionSetRulesApi} interface.
 * It provides methods for managing condition set rules via the Permit API.
 */
public class ConditionSetRulesApi extends BaseApi implements IConditionSetRulesApi {

    /**
     * Constructs a new {@code ConditionSetRulesApi} instance with the specified HTTP client and Permit configuration.
     *
     * @param client The {@link OkHttpClient} instance to be used for HTTP requests.
     * @param config The {@link PermitConfig} instance containing the Permit configuration.
     */
    public ConditionSetRulesApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(ConditionSetRulesApi.class));
    }

    /**
     * Constructs the URL for condition set rules based on the specified URL fragment.
     *
     * @param url The URL fragment to be appended to the base URL.
     * @return The complete URL for condition set rules API.
     */
    private String getConditionSetRulesUrl(String url) {
        return buildUrl(
                String.format(
                        "/v2/facts/%s/%s/set_rules%s",
                        config.getContext().getProject(),
                        config.getContext().getEnvironment(),
                        url
                )
        );
    }

    /**
     * Retrieves a list of condition set rules based on the specified filters.
     *
     * @param userSetKey     The user set key.
     * @param permissionKey  The permission key.
     * @param resourceSetKey The resource set key.
     * @param page           The page number.
     * @param perPage        The number of items per page.
     * @return An array of {@link ConditionSetRuleRead} objects.
     * @throws IOException          If an I/O error occurs while sending the request.
     * @throws PermitApiError       If the Permit API returns a response with an error status code.
     * @throws PermitContextError   If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ConditionSetRuleRead[] list(String userSetKey, String permissionKey, String resourceSetKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getConditionSetRulesUrl("");
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        if (userSetKey != null) {
            urlBuilder.addQueryParameter("user_set", userSetKey);
        }
        if (permissionKey != null) {
            urlBuilder.addQueryParameter("permission", permissionKey);
        }
        if (resourceSetKey != null) {
            urlBuilder.addQueryParameter("resource_set", resourceSetKey);
        }
        Request request = buildRequest(
                new Request.Builder()
                        .url(
                                urlBuilder
                                        .addQueryParameter("page", Integer.toString(page))
                                        .addQueryParameter("per_page", Integer.toString(perPage))
                                        .build()
                        )
                        .get()
        );

        try (Response response = client.newCall(request).execute()) {
            String responseString = processResponseBody(response);
            return (new Gson()).fromJson(responseString, ConditionSetRuleRead[].class);
        }
    }

    /**
     * Retrieves a list of condition set rules based on the specified filters.
     * By default, requests a page of 100 items.
     *
     * @param userSetKey     The user set key.
     * @param permissionKey  The permission key.
     * @param resourceSetKey The resource set key.
     * @param page           The page number.
     * @return An array of {@link ConditionSetRuleRead} objects.
     * @throws IOException          If an I/O error occurs while sending the request.
     * @throws PermitApiError       If the Permit API returns a response with an error status code.
     * @throws PermitContextError   If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ConditionSetRuleRead[] list(String userSetKey, String permissionKey, String resourceSetKey, int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(userSetKey, permissionKey, resourceSetKey, page, 100);
    }

    /**
     * Retrieves a list of condition set rules based on the specified filters.
     * By default, requests the first page, where each page contains 100 items.
     *
     * @param userSetKey     The user set key.
     * @param permissionKey  The permission key.
     * @param resourceSetKey The resource set key.
     * @return An array of {@link ConditionSetRuleRead} objects.
     * @throws IOException          If an I/O error occurs while sending the request.
     * @throws PermitApiError       If the Permit API returns a response with an error status code.
     * @throws PermitContextError   If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ConditionSetRuleRead[] list(String userSetKey, String permissionKey, String resourceSetKey) throws IOException, PermitApiError, PermitContextError {
        return this.list(userSetKey, permissionKey, resourceSetKey, 1);
    }

    /**
     * Retrieves all the condition set rules (no filters) with default pagination
     *
     * @return An array of {@link ConditionSetRuleRead} objects.
     * @throws IOException          If an I/O error occurs while sending the request.
     * @throws PermitApiError       If the Permit API returns a response with an error status code.
     * @throws PermitContextError   If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    @Override
    public ConditionSetRuleRead[] list() throws IOException, PermitApiError, PermitContextError {
        return this.list(null, null, null);
    }

    /**
     * Creates a new condition set rule.
     *
     * @param rule The condition set rule to create.
     * @return The created {@link ConditionSetRuleRead} object.
     * @throws IOException          If an I/O error occurs while sending the request.
     * @throws PermitApiError       If the Permit API returns a response with an error status code.
     * @throws PermitContextError   If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ConditionSetRuleRead create(ConditionSetRuleCreate rule) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getConditionSetRulesUrl("");
        RequestBody jsonBody = getJsonRequestBody(rule);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .post(jsonBody)
        );

        ConditionSetRuleRead[] createdRuleArray = this.<ConditionSetRuleRead[]>callApiAndParseJson(request, ConditionSetRuleRead[].class);
        return createdRuleArray[0];
    }

    /**
     * Deletes a condition set rule.
     *
     * @param rule The condition set rule to delete.
     * @throws IOException          If an I/O error occurs while sending the request.
     * @throws PermitApiError       If the Permit API returns a response with an error status code.
     * @throws PermitContextError   If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public void delete(ConditionSetRuleRemove rule) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getConditionSetRulesUrl("");
        RequestBody jsonBody = getJsonRequestBody(rule);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .delete(jsonBody)
        );

        try (Response response = client.newCall(request).execute()) {
            processResponseBody(response, false);
        }
    }
    
}
