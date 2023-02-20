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

public class ProjectsApi extends BaseApi implements IProjectsApi {
    public ProjectsApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(ProjectsApi.class));
    }

    private String getProjectsUrl(String url) {
        return buildUrl(
                String.format(
                        "/v2/projects%s",
                        url
                )
        );
    }

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

    public ProjectRead[] list(int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(page, 100);
    }

    public ProjectRead[] list() throws IOException, PermitApiError, PermitContextError {
        return this.list(1);
    }

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

    public ProjectRead getByKey(String projectKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(projectKey);
    }

    public ProjectRead getById(UUID projectId) throws IOException, PermitApiError, PermitContextError {
        return this.get(projectId.toString());
    }

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
