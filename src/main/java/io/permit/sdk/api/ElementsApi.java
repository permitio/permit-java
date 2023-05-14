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

/**
 * API client for interacting with the Permit Elements API.
 */
public class ElementsApi extends BaseApi implements IElementsApi {
    public ElementsApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(ElementsApi.class));
    }

    /**
     * Logs in as an end-user within a specific tenant and returns the embedded-authentication session data.
     *
     * @param userKey   The key of the user the element will log in as.
     * @param tenantKey The key of the active tenant for the logged in user.
     * @return The embedded login authentication session data.
     * @throws IOException          If an I/O error occurs while sending the request.
     * @throws PermitApiError       If the Permit API returns a response with an error status code.
     * @throws PermitContextError   If the configured {@link io.permit.sdk.PermitContext} does not match the required endpoint context.
     */
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
