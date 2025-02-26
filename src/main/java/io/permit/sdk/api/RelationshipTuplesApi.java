package io.permit.sdk.api;

import com.google.gson.Gson;
import io.permit.sdk.ApiContextLevel;
import io.permit.sdk.ApiKeyLevel;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.openapi.models.RelationshipTupleCreate;
import io.permit.sdk.openapi.models.RelationshipTupleDelete;
import io.permit.sdk.openapi.models.RelationshipTupleRead;
import okhttp3.*;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.Objects;

interface IRelationshipTuplesApi {
    RelationshipTupleRead[] list(String subject, String relation, String object, int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    RelationshipTupleRead[] list(String subject, String relation, String object, int page) throws IOException, PermitApiError, PermitContextError;
    RelationshipTupleRead[] list(String subject, String relation, String object) throws IOException, PermitApiError, PermitContextError;
    RelationshipTupleRead[] list() throws IOException, PermitApiError, PermitContextError;
    RelationshipTupleRead create(RelationshipTupleCreate tuple) throws IOException, PermitApiError, PermitContextError;
    void delete(RelationshipTupleDelete tuple) throws IOException, PermitApiError, PermitContextError;
}

/**
 * The RelationshipTuplesApi class provides methods for managing relationship tuples in the Permit API.
 */
public class RelationshipTuplesApi extends BaseApi implements IRelationshipTuplesApi {
    /**
     * Constructs a new RelationshipTuplesApi instance with the specified OkHttpClient and PermitConfig.
     *
     * @param client The OkHttpClient instance to use for API requests.
     * @param config The PermitConfig instance that contains the SDK configuration.
     */
    public RelationshipTuplesApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(RelationshipTuplesApi.class));
    }

    /**
     * Constructs the URL for relationship tuples based on the project and environment in the PermitConfig.
     *
     * @param url The URL string to append to the base URL.
     * @return The complete URL for relationship tuples.
     */
    private String getRelationshipTuplesUrl(String url) {
        if (Boolean.TRUE.equals(config.isProxyFactsViaPdp())) {
            return buildPdpUrl(
                    String.format(
                            "/facts/relationship_tuples%s",
                            url
                    )
            );
        } else {
            return buildUrl(
                    String.format(
                            "/v2/facts/%s/%s/relationship_tuples%s",
                            config.getContext().getProject(),
                            config.getContext().getEnvironment(),
                            url
                    )
            );
        }
    }

    /**
     * Returns a paginated list of relationship tuples filtered by the optional subject, relation and object.
     * To mark "all subjects" or an empty subjet filter - pass `null` instead of the subject key (same for relation and object).
     *
     * @param subject   The subject of the relationship (a resource instance key).
     * @param relation  The relation between the two resource instances.
     * @param object    The object of the relationship (a resource instance key).
     * @param page      The page number of the results.
     * @param perPage   The number of results per page.
     * @return An array of RelationshipTupleRead objects representing the relationship tuples.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RelationshipTupleRead[] list(String subject, String relation, String object, int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getRelationshipTuplesUrl("");
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        if (subject != null) {
            urlBuilder.addQueryParameter("subject", subject);
        }
        if (relation != null) {
            urlBuilder.addQueryParameter("relation", relation);
        }
        if (object != null) {
            urlBuilder.addQueryParameter("object", object);
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
            return (new Gson()).fromJson(responseString, RelationshipTupleRead[].class);
        }
    }

    /**
     * Lists relationship tuples based on the specified subject, relation, object, and page parameters with the default number of items per page.
     *
     * @param subject   The subject of the relationship (a resource instance key).
     * @param relation  The relation between the two resource instances.
     * @param object    The object of the relationship (a resource instance key).
     * @param page      The page number of the results.
     * @return An array of RelationshipTupleRead objects representing the relationship tuples.
     * @throws IOException           If an I/Oerror occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RelationshipTupleRead[] list(String subject, String relation, String object, int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(subject, relation, object, page, 100);
    }

    /**
     * Lists relationship tuples based on the specified subject, relation, object, with the default pagination parameters.
     *
     * @param subject   The subject of the relationship (a resource instance key).
     * @param relation  The relation between the two resource instances.
     * @param object    The object of the relationship (a resource instance key).
     * @return An array of RelationshipTupleRead objects representing the relationship tuples.
     * @throws IOException           If an I/Oerror occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RelationshipTupleRead[] list(String subject, String relation, String object) throws IOException, PermitApiError, PermitContextError {
        return this.list(subject, relation, object, 1);
    }

    /**
     * Lists all relationship tuples with the default pagination parameters.
     *
     * @return An array of RelationshipTupleRead objects representing the relationship tuples.
     * @throws IOException           If an I/Oerror occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RelationshipTupleRead[] list() throws IOException, PermitApiError, PermitContextError {
        return this.list(null, null, null);
    }

    /**
     * Creates a new relationship tuple.
     *
     * @param tuple The RelationshipTupleCreate object containing the tuple data.
     * @return The RelationshipTupleRead object representing the created tuple.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public RelationshipTupleRead create(RelationshipTupleCreate tuple) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getRelationshipTuplesUrl("");
        RequestBody jsonBody = getJsonRequestBody(tuple);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .post(jsonBody)
        );

        return this.<RelationshipTupleRead>callApiAndParseJson(request, RelationshipTupleRead.class);
    }

    /**
     * Removes a relationship tuple.
     *
     * @param tuple The RelationshipTupleRemove object containing the tuple data.
     * @throws IOException           If an I/O error occurs during the HTTP request.
     * @throws PermitApiError        If the Permit API returns a response with an error status code.
     * @throws PermitContextError    If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public void delete(RelationshipTupleDelete tuple) throws IOException, PermitApiError, PermitContextError {
        ensureAccessLevel(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        ensureContext(ApiContextLevel.ENVIRONMENT);
        String url = getRelationshipTuplesUrl("");
        RequestBody jsonBody = getJsonRequestBody(tuple);

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
