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

public class ConditionSetsApi extends BaseApi implements IConditionSetsApi {
    public ConditionSetsApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(ConditionSetsApi.class));
    }

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

    public ConditionSetRead[] list(int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(page, 100);
    }

    public ConditionSetRead[] list() throws IOException, PermitApiError, PermitContextError {
        return this.list(1);
    }

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

    public ConditionSetRead getByKey(String conditionSetKey) throws IOException, PermitApiError, PermitContextError {
        return this.get(conditionSetKey);
    }

    public ConditionSetRead getById(UUID conditionSetId) throws IOException, PermitApiError, PermitContextError {
        return this.get(conditionSetId.toString());
    }

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
