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

interface IEnvironmentsApi {
    EnvironmentRead[] list(String projectKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    EnvironmentRead[] list(String projectKey, int page) throws IOException, PermitApiError, PermitContextError;
    EnvironmentRead[] list(String projectKey) throws IOException, PermitApiError, PermitContextError;
    EnvironmentRead get(String projectKey, String environmentKey) throws IOException, PermitApiError, PermitContextError;
    EnvironmentRead getByKey(String projectKey, String environmentKey) throws IOException, PermitApiError, PermitContextError;
    EnvironmentRead getById(UUID projectId, UUID environmentId) throws IOException, PermitApiError, PermitContextError;
    EnvironmentRead create(String projectKey, EnvironmentCreate environmentData) throws IOException, PermitApiError, PermitContextError;
    EnvironmentRead update(String projectKey, String environmentKey, EnvironmentUpdate environmentData) throws IOException, PermitApiError, PermitContextError;
    EnvironmentRead copy(String projectKey, String environmentKey, EnvironmentCopy copyParams) throws IOException, PermitApiError, PermitContextError;
    void delete(String projectKey, String environmentKey) throws IOException, PermitApiError, PermitContextError;
}

/**
 * The {@code EnvironmentsApi} class provides methods for interacting with environments in the Permit REST API.
 * It implements the {@code IEnvironmentsApi} interface.
 */
public class EnvironmentsApi extends BaseApi implements IEnvironmentsApi {
    /**
     * Constructs an instance of the {@code EnvironmentsApi} class.
     *
     * @param client The OkHttpClient instance to be used for HTTP requests.
     * @param config The PermitConfig instance containing the API configuration.
     */
    public EnvironmentsApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(EnvironmentsApi.class));
    }

    /**
     * Builds the URL for accessing the environments API.
     *
     * @param projectKey The project key.
     * @param url        The URL fragment.
     * @return The complete URL for accessing the environments API.
     */
    private String getEnvironmentsUrl(String projectKey, String url) {
        return buildUrl(
                String.format(
                    "/v2/projects/%s/envs%s",
                    projectKey,
                    url
                )
        );
    }

    /**
     * Lists all environments for the specified project, with pagination support
     *
     * @param projectKey The project key.
     * @param page       The page number.
     * @param perPage    The number of environments per page.
     * @return An array of EnvironmentRead objects representing the environments.
     * @throws IOException            If an I/O error occurs during the HTTP request.
     * @throws PermitApiError         If the Permit API returns a response with an error status code.
     * @throws PermitContextError     If there is an error in the Permit context.
     */
    public EnvironmentRead[] list(String projectKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.PROJECT_LEVEL_API_KEY);
        String url = getEnvironmentsUrl(projectKey, "");
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
            return (new Gson()).fromJson(responseString, EnvironmentRead[].class);
        }
    }

    /**
     * Lists all environments for the specified project, with the default number of environments per page.
     *
     * @param projectKey The project key.
     * @param page       The page number.
     * @return An array of EnvironmentRead objects representing the environments.
     * @throws IOException            If an I/O error occurs during the HTTP request.
     * @throws PermitApiError         If the Permit API returns a response with an error status code.
     * @throws PermitContextError     If there is an error in the Permit context.
     */
    public EnvironmentRead[] list(String projectKey, int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(projectKey, page, 100);
    }

    /**
     * Lists all environments for the specified project, with the default page number and number of environments per page.
     *
     * @param projectKey The projectkey.
     * @return An array of EnvironmentRead objects representing the environments.
     * @throws IOException            If an I/O error occurs during the HTTP request.
     * @throws PermitApiError         If the Permit API returns a response with an error status code.
     * @throws PermitContextError     If there is an error in the Permit context.
     */
    public EnvironmentRead[] list(String projectKey) throws IOException, PermitApiError, PermitContextError {
        return this.list(projectKey,1);
    }

    /**
     * Retrieves the environment with the specified project key and environment key.
     *
     * @param projectKey     The project key.
     * @param environmentKey The environment key.
     * @return The EnvironmentRead object representing the environment.
     * @throws IOException            If an I/O error occurs during the HTTP request.
     * @throws PermitApiError         If the Permit API returns a response with an error status code.
     * @throws PermitContextError     If there is an error in the Permit context.
     */
    public EnvironmentRead get(String projectKey, String environmentKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.PROJECT_LEVEL_API_KEY);
        String url = getEnvironmentsUrl(projectKey, String.format("/%s", environmentKey));
        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .get()
        );

        return this.<EnvironmentRead>callApiAndParseJson(request, EnvironmentRead.class);
    }

    /**
     * Retrieves the environment with the specified project key and environment key.
     * This method is an alias for the {@link #get(String, String)} method.
     *
     * @param projectKey     The project key.
     * @param environmentKey The environment key.
     * @return The EnvironmentRead object representing the environment.
     * @throws IOException            If an I/O error occurs during the HTTP request.
     * @throws PermitApiError         If the Permit API returns a response with an error status code.
     * @throws PermitContextError     If there is an error in the Permit context.
     */
    public EnvironmentRead getByKey(String projectKey, String environmentKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(projectKey, environmentKey);
    }

    /**
     * Retrieves the environment with the specified project ID and environment ID.
     *
     * @param projectId     The project ID.
     * @param environmentId The environment ID.
     * @return The EnvironmentRead object representing the environment.
     * @throws IOException            If an I/O error occurs during the HTTP request.
     * @throws PermitApiError         If the Permit API returns a response with an error status code.
     * @throws PermitContextError     If there is an error in the Permit context.
     */
    public EnvironmentRead getById(UUID projectId, UUID environmentId) throws IOException, PermitApiError, PermitContextError {
        return this.get(projectId.toString(), environmentId.toString());
    }

    /**
     * Creates a new environment inside the specified project.
     *
     * @param projectKey       The parent project key.
     * @param environmentData  The environment data.
     * @return The EnvironmentRead object representing the created environment.
     * @throws IOException            If an I/O error occurs during the HTTP request.
     * @throws PermitApiError         If the Permit API returns a response with an error status code.
     * @throws PermitContextError     If there is an error in the Permit context.
     */
    public EnvironmentRead create(String projectKey, EnvironmentCreate environmentData) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.PROJECT_LEVEL_API_KEY);
        String url = getEnvironmentsUrl(projectKey,"");
        RequestBody jsonBody = getJsonRequestBody(environmentData);

        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .post(jsonBody)
        );

        return this.<EnvironmentRead>callApiAndParseJson(request, EnvironmentRead.class);
    }

    /**
     * Updates the specified environment in the given project.
     *
     * @param projectKey       The project key.
     * @param environmentKey   The environment key.
     * @param environmentData  The updated environment data.
     * @return The EnvironmentRead object representing the updated environment.
     * @throws IOException            If an I/O error occurs during the HTTP request.
     * @throws PermitApiError         If the Permit API returns a response with an error status code.
     * @throws PermitContextError     If there is an error in the Permit context.
     */
    public EnvironmentRead update(String projectKey, String environmentKey, EnvironmentUpdate environmentData) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.PROJECT_LEVEL_API_KEY);
        String url = getEnvironmentsUrl(projectKey, String.format("/%s", environmentKey));
        RequestBody jsonBody = getJsonRequestBody(environmentData);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .patch(jsonBody)
        );

        return this.<EnvironmentRead>callApiAndParseJson(request, EnvironmentRead.class);
    }

    /**
     * Clones data from (creates a copy) from a source specified environment into a different target
     * environment in the same project. The target environment can be a new environment or an existing
     * environment. For existing environments, the user must specify a conflict strategy - meaning what
     * the system should do in case a copied object conflicts with an existing object (with the same key)
     * in the target environment. The system can overwrite all the conflicting objects, or fail (and
     * cancel the copy) when encountering the first conflict.
     *
     * @param projectKey       The project key.
     * @param environmentKey   The environment key.
     * @param copyParams       The parameters for the copy operation.
     * @return The EnvironmentRead object representing the copied environment.
     * @throws IOException            If an I/O error occurs during the HTTP request.
     * @throws PermitApiError         If the Permit API returns a response with an error status code.
     * @throws PermitContextError     If there is an error in the Permit context.
     */
    public EnvironmentRead copy(String projectKey, String environmentKey, EnvironmentCopy copyParams) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.PROJECT_LEVEL_API_KEY);
        String url = getEnvironmentsUrl(projectKey, String.format("/%s/copy", environmentKey));
        RequestBody jsonBody = getJsonRequestBody(copyParams);

        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .post(jsonBody)
        );

        return this.<EnvironmentRead>callApiAndParseJson(request, EnvironmentRead.class);
    }

    /**
     * Deletes the specified environment in the given project.
     *
     * @param projectKey       The project key.
     * @param environmentKey   The environment key.
     * @throws IOException            If an I/O error occurs during the HTTP request.
     * @throws PermitApiError         If the Permit API returns a response with an error status code.
     * @throws PermitContextError     If there is an error in the Permit context.
     */
    public void delete(String projectKey, String environmentKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.PROJECT_LEVEL_API_KEY);
        String url = getEnvironmentsUrl(projectKey, String.format("/%s", environmentKey));
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
