package io.permit.sdk.api;

import com.google.gson.Gson;
import io.permit.sdk.ApiKeyLevel;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.api.models.*;
import io.permit.sdk.openapi.models.*;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.*;

interface IElementsApi {
    EmbeddedLoginRequestOutput loginAs(String userKey, String tenantKey) throws IOException, PermitApiError, PermitContextError;
}

public class ElementsApi extends BaseApi implements IElementsApi {
    public ElementsApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(ElementsApi.class));
    }

    public EmbeddedLoginRequestOutput loginAs(String userKey, String tenantKey) throws IOException, PermitApiError, PermitContextError {
        ensureContext(ApiKeyLevel.ENVIRONMENT_LEVEL_API_KEY);
        String url = buildUrl("/v2/auth/elements_login_as");
        RequestBody jsonBody = getJsonRequestBody(new UserLoginRequestInput(userKey, tenantKey));

        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .post(jsonBody)
        );

        try (Response response = client.newCall(request).execute()) {
            String responseString = processResponseBody(response);
            EmbeddedLoginRequestOutput result = (new Gson()).fromJson(responseString, EmbeddedLoginRequestOutput.class);
            return processResult(result);
        }
    }

    private ElementsLoginResult processResult(EmbeddedLoginRequestOutput result) {
        Map<String, String> content = new HashMap<>();
        content.put("url", result.redirectUrl);

        ElementsLoginResult loginResult = (ElementsLoginResult) (new ElementsLoginResult())
                .withError(result.error)
                .withErrorCode(result.errorCode)
                .withToken(result.token)
                .withExtra(result.extra)
                .withRedirectUrl(result.redirectUrl);

        loginResult.withContent(content);

        return loginResult;
    }
}
