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

interface IConditionSetRulesApi {
    ConditionSetRuleRead[] list(String userSetKey, String permissionKey, String resourceSetKey, int page, int perPage) throws IOException, PermitApiError, PermitContextError;
    ConditionSetRuleRead[] list(String userSetKey, String permissionKey, String resourceSetKey, int page) throws IOException, PermitApiError, PermitContextError;
    ConditionSetRuleRead[] list(String userSetKey, String permissionKey, String resourceSetKey) throws IOException, PermitApiError, PermitContextError;
    ConditionSetRuleRead create(ConditionSetRuleCreate rule) throws IOException, PermitApiError, PermitContextError;
    void delete(ConditionSetRuleRemove rule) throws IOException, PermitApiError, PermitContextError;
}

public class ConditionSetRulesApi extends BaseApi implements IConditionSetRulesApi {
    public ConditionSetRulesApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(ConditionSetRulesApi.class));
    }

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

    public ConditionSetRuleRead[] list(String userSetKey, String permissionKey, String resourceSetKey, int page) throws IOException, PermitApiError, PermitContextError {
        return this.list(userSetKey, permissionKey, resourceSetKey, page, 100);
    }

    public ConditionSetRuleRead[] list(String userSetKey, String permissionKey, String resourceSetKey) throws IOException, PermitApiError, PermitContextError {
        return this.list(userSetKey, permissionKey, resourceSetKey, 1);
    }

    public ConditionSetRuleRead create(ConditionSetRuleCreate rule) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = getConditionSetRulesUrl("");
        RequestBody jsonBody = getJsonRequestBody(rule);

        Request request = buildRequest(
                new Request.Builder()
                        .url(url)
                        .post(jsonBody)
        );

        return this.<ConditionSetRuleRead>callApiAndParseJson(request, ConditionSetRuleRead.class);
    }

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
