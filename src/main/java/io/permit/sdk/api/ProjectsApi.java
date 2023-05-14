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


interface IProjectsApi {
    ProjectRead[] list(int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    ProjectRead[] list(int page) throws IOException, PermitApiError, PermitContextError;
    ProjectRead[] list() throws IOException, PermitApiError, PermitContextError;
    ProjectRead get(String projectKey) throws IOException, PermitApiError, PermitContextError;
    ProjectRead getByKey(String projectKey) throws IOException, PermitApiError, PermitContextError;
    ProjectRead getById(UUID projectId) throws IOException, PermitApiError, PermitContextError;
    ProjectRead create(ProjectCreate projectData) throws IOException, PermitApiError, PermitContextError;
    ProjectRead update(String projectKey, ProjectUpdate projectData) throws IOException, PermitApiError, PermitContextError;
    void delete(String projectKey) throws IOException, PermitApiError, PermitContextError;
}

/**
 * The {@code ProjectsApi} class provides methods for interacting with projects in the Permit API.
 * It implements the {@code IProjectsApi} interface.
 */
public class ProjectsApi extends BaseApi implements IProjectsApi {
    /**
     * Constructs an instance of the {@code ProjectsApi} class.
     *
     * @param client The OkHttpClient instance to be used for HTTP requests.
     * @param config The PermitConfig instance containing the API configuration.
     */
    public ProjectsApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(ProjectsApi.class));
    }

    /**
     * Builds the URL for accessing the projects API.
     *
     * @param url The URL fragment.
     * @return The complete URL for accessing the projects API.
     */
    private String getProjectsUrl(String url) {
        return buildUrl(
                String.format(
                        "/v2/projects%s",
                        url
                )
        );
    }

    /**
     * Lists all projects with pagination.
     *
     * @param page    The page number.
     * @param perPage The number of projects per page.
     * @return An array of ProjectRead objects representing the projects.
     * @throws IOException            If an I/O error occurs during the HTTP request.
     * @throws PermitApiError         If the Permit API returns a response with an error status code.
     * @throws PermitContextError     If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ProjectRead[] list(int page, int perPage) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ORGANIZATION_LEVEL_API_KEY);
        String url = getProjectsUrl("");
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
            return (new Gson()).fromJson(responseString, ProjectRead[].class);
        }
    }

    /**
     * Lists all projects with the default number of projects per page.
     *
     * @param page The page number.
     * @return An array of ProjectRead objects representing the projects.
     * @throws IOException            If an I/O error occurs during the HTTP request.
     * @throws PermitApiError         If the Permit API returns a response with an error status code.
     * @throws PermitContextError     If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ProjectRead[] list(int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(page, 100);
    }

    /**
     * Lists all projects with the default page number and number of projects per page.
     *
     * @return An array of ProjectRead objects representing the projects.
     * @throws IOException            If an I/O error occurs during the HTTP request.
     * @throws PermitApiError         If the Permit API returns a response with an error status code.
     * @throws PermitContextError     If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ProjectRead[] list() throws IOException, PermitApiError, PermitContextError {
        return this.list(1);
    }

    /**
     * Retrieves the project with the specified project key.
     *
     * @param projectKey The project key.
     * @return The ProjectRead object representing the project.
     * @throws IOException            If an I/O error occurs during the HTTP request.
     * @throws PermitApiError         If the Permit API returns a response with an error status code.
     * @throws PermitContextError     If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ProjectRead get(String projectKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ORGANIZATION_LEVEL_API_KEY);
        String url = getProjectsUrl(String.format("/%s", projectKey));
        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .get()
        );

        return this.<ProjectRead>callApiAndParseJson(request, ProjectRead.class);
    }

    /**
     * Retrieves the project with the specified project key.
     * This method is an alias for the {@link #get(String)} method.
     *
     * @param projectKey The project key.
     * @return The ProjectRead object representing the project.
     * @throws IOException            If an I/O error occurs during the HTTP request.
     * @throws PermitApiError         If the Permit API returns a response with an error status code.
     * @throws PermitContextError     If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ProjectRead getByKey(String projectKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(projectKey);
    }

    /**
     * Retrieves the project with the specified project ID.
     *
     * @param projectId The project ID.
     * @return The ProjectRead object representing the project.
     * @throws IOException            If an I/O error occurs during the HTTP request.
     * @throws PermitApiError         If the Permit API returns a response with an error status code.
     * @throws PermitContextError     If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ProjectRead getById(UUID projectId) throws IOException, PermitApiError, PermitContextError {
        return this.get(projectId.toString());
    }

    /**
     * Creates a new project.
     *
     * @param projectData The project data.
     * @return The ProjectRead object representing the created project.
     * @throws IOException            If an I/O error occurs during the HTTP request.
     * @throws PermitApiError         If the Permit API returns a response with an error status code.
     * @throws PermitContextError     If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ProjectRead create(ProjectCreate projectData) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ORGANIZATION_LEVEL_API_KEY);
        String url = getProjectsUrl("");
        RequestBody jsonBody = getJsonRequestBody(projectData);

        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .post(jsonBody)
        );

        return this.<ProjectRead>callApiAndParseJson(request, ProjectRead.class);
    }

    /**
     * Updates the specified project.
     *
     * @param projectKey    The project key.
     * @param projectData   The updated project data.
     * @return The ProjectRead object representing the updated project.
     * @throws IOException            If an I/O error occurs during the HTTP request.
     * @throws PermitApiError         If the Permit API returns a response with an error status code.
     * @throws PermitContextError     If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public ProjectRead update(String projectKey, ProjectUpdate projectData) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ORGANIZATION_LEVEL_API_KEY);
        String url = getProjectsUrl(String.format("/%s", projectKey));
        RequestBody jsonBody = getJsonRequestBody(projectData);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .patch(jsonBody)
        );

        return this.<ProjectRead>callApiAndParseJson(request, ProjectRead.class);
    }

    /**
     * Deletes the specified project.
     *
     * @param projectKey The project key.
     * @throws IOException            If an I/O error occurs during the HTTP request.
     * @throws PermitApiError         If the Permit API returns a response with an error status code.
     * @throws PermitContextError     If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
    public void delete(String projectKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ORGANIZATION_LEVEL_API_KEY);
        String url = getProjectsUrl(String.format("/%s", projectKey));
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
