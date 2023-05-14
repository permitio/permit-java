package io.permit.sdk.api;

import com.google.gson.Gson;
import io.permit.sdk.ApiKeyLevel;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.openapi.models.*;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

interface IConditionSetsApi {
    ConditionSetRead[] list(int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    ConditionSetRead[] list(int page) throws IOException, PermitApiError, PermitContextError;
    ConditionSetRead[] list() throws IOException, PermitApiError, PermitContextError;
    ConditionSetRead get(String conditionSetKey) throws IOException, PermitApiError, PermitContextError;
    ConditionSetRead getByKey(String conditionSetKey) throws IOException, PermitApiError, PermitContextError;
    ConditionSetRead getById(UUID conditionSetId) throws IOException, PermitApiError, PermitContextError;
    ConditionSetRead create(ConditionSetCreate conditionSetData) throws IOException, PermitApiError, PermitContextError;
    ConditionSetRead update(String conditionSetKey, ConditionSetUpdate conditionSetData) throws IOException, PermitApiError, PermitContextError;
    void delete(String conditionSetKey) throws IOException, PermitApiError, PermitContextError;
}

/**
 * The ConditionSetsApi class provides methods for interacting with condition sets using the Permit REST API.
 */
public class ConditionSetsApi extends BaseApi implements IConditionSetsApi {
    /**
     * Creates a new instance of the ConditionSetsApi class.
     *
     * @param client The OkHttpClient instance to use for HTTP requests.
     * @param config The PermitConfig instance containing the SDK configuration.
     */
    public ConditionSetsApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(ConditionSetsApi.class));
    }

    /**
     * Constructs the URL for condition sets based on the specified URL fragment.
     *
     * @param url The URL fragment to be appended to the base URL.
     * @return The complete URL for condition sets API.
     */
    private String getConditionSetsUrl(String url) {
        return buildUrl(
                String.format(
                        "/v2/schema/%s/%s/condition_sets%s",
                        config.getContext().getProject(),
                        config.getContext().getEnvironment(),
                        url
                )
        );
    }

    /**
     * Retrieves a list of condition sets with pagination support.
     *
     * @param page    The page number.
     * @param perPage The number of items per page.
     * @return An array of {@link ConditionSetRead} objects.
     * @throws IOException          If an I/O error occurs while sending the request.
     * @throws PermitApiError       If an error occurs in the Permit API.
     * @throws PermitContextError   If the Permit context is not properly configured.
     */
    public ConditionSetRead[] list(int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getConditionSetsUrl("");
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
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
            return (new Gson()).fromJson(responseString, ConditionSetRead[].class);
        }
    }

    /**
     * Retrieves a list of condition sets with the specified page number and a default number of items per page (100).
     *
     * @param page The page number.
     * @return An array of {@link ConditionSetRead} objects.
     * @throws IOException          If an I/O error occurs while sending the request.
     * @throws PermitApiError       If an error occurs in the Permit API.
     * @throws PermitContextError   If the Permit context is not properly configured.
     */
    public ConditionSetRead[] list(int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(page, 100);
    }

    /**
     * Retrieves a list of condition sets with the default page number (1) and a default number of items per page (100).
     *
     * @return An array of {@link ConditionSetRead} objects.
     * @throws IOException          If an I/O error occurs while sending the request.
     * @throws PermitApiError       If an error occurs in the Permit API.
     * @throws PermitContextError   If the Permit context is not properly configured.
     */
    public ConditionSetRead[] list() throws IOException, PermitApiError, PermitContextError {
        return this.list(1);
    }

    /**
     * Retrieves a specific condition set by its key.
     *
     * @param conditionSetKey The key of the condition set.
     * @return The {@link ConditionSetRead} object representing the condition set.
     * @throws IOException          If an I/O error occurs while sending the request.
     * @throws PermitApiError       If an error occurs in the Permit API.
     * @throws PermitContextError   If the Permit context is not properly configured.
     */
    public ConditionSetRead get(String conditionSetKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getConditionSetsUrl(String.format("/%s", conditionSetKey));
        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .get()
        );

        return this.<ConditionSetRead>callApiAndParseJson(request, ConditionSetRead.class);
    }

    /**
     * Retrieves a specific condition set by its key.
     *
     * @param conditionSetKey The key of the condition set.
     * @return The {@link ConditionSetRead} object representing the condition set.
     * @throws IOException          If an I/O error occurs while sending the request.
     * @throws PermitApiError       If an error occurs in the Permit API.
     * @throws PermitContextError   If the Permit context is not properly configured.
     */
    public ConditionSetRead getByKey(String conditionSetKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(conditionSetKey);
    }

    /**
     * Retrieves a specific condition set by its ID.
     *
     * @param conditionSetId The ID of the condition set.
     * @return The {@link ConditionSetRead} object representing the condition set.
     * @throws IOException          If an I/O error occurs while sending the request.
     * @throws PermitApiError       If an error occurs in the Permit API.
     * @throws PermitContextError   If the Permit context is not properly configured.
     */
    public ConditionSetRead getById(UUID conditionSetId) throws IOException, PermitApiError, PermitContextError {
        return this.get(conditionSetId.toString());
    }

    /**
     * Creates a new condition set.
     *
     * @param conditionSetData The data for creating the condition set.
     * @return The {@link ConditionSetRead} object representing the created condition set.
     * @throws IOException          If an I/O error occurs while sending the request.
     * @throws PermitApiError       If an error occurs in the Permit API.
     * @throws PermitContextError   If the Permit context is not properly configured.
     */
    public ConditionSetRead create(ConditionSetCreate conditionSetData) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getConditionSetsUrl("");
        RequestBody jsonBody = getJsonRequestBody(conditionSetData);

        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .post(jsonBody)
        );

        return this.<ConditionSetRead>callApiAndParseJson(request, ConditionSetRead.class);
    }

    /**
     * Updates an existing condition set.
     *
     * @param conditionSetKey  The key of the condition set to update.
     * @param conditionSetData The updated data for the condition set.
     * @return The {@link ConditionSetRead} object representing the updated condition set.
     * @throws IOException          If an I/O error occurs while sending the request.
     * @throws PermitApiError       If an error occurs in the Permit API.
     * @throws PermitContextError   If the Permit context is not properly configured.
     */
    public ConditionSetRead update(String conditionSetKey, ConditionSetUpdate conditionSetData) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getConditionSetsUrl(String.format("/%s", conditionSetKey));
        RequestBody jsonBody = getJsonRequestBody(conditionSetData);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .patch(jsonBody)
        );

        return this.<ConditionSetRead>callApiAndParseJson(request, ConditionSetRead.class);
    }

    /**
     * Deletes a condition set.
     *
     * @param conditionSetKey The key of the condition set to delete.
     * @throws IOException          If an I/O error occurs while sending the request.
     * @throws PermitApiError       If an error occurs in the Permit API.
     * @throws PermitContextError   If the Permit context is not properly configured.
     */
    public void delete(String conditionSetKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getConditionSetsUrl(String.format("/%s", conditionSetKey));
        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .delete()
        );

        try (Response response = client.newCall(request).execute()) {
            processResponseBody(response, false);
        }
    }
}
