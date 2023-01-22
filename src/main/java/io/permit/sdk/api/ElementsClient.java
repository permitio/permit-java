package io.permit.sdk.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.api.models.*;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

interface IElementsApi {
    UserLoginResponse loginAs(String userId, String tenantId) throws IOException, PermitApiException;
}

public class ElementsClient implements IElementsApi {
    final static Logger logger = LoggerFactory.getLogger(ApiClient.class);
    private final OkHttpClient client = new OkHttpClient();
    private final PermitConfig config;
    private final Headers headers;
    private final String apiUrl;

    public ElementsClient(PermitConfig config) {
        this.config = config;
        this.headers = new Headers.Builder()
                .add("Content-Type", "application/json")
                .add("Authorization", String.format("Bearer %s", this.config.getToken()))
                .build();
        this.apiUrl = this.config.getApiUrl();
    }

    private void throwIfErrorResponseCode(String requestRepr, Response response, String responseContent, List<Integer> expectedErrorCodes) throws PermitApiException {
        String log = String.format("Received response: %s : status code %d : %s", requestRepr, response.code(), responseContent);
        if (!response.isSuccessful() && this.config.isDebugMode()) {
            this.logger.error(log);
        } else {
            this.logger.debug(log);
        }
        if (!response.isSuccessful() && !expectedErrorCodes.contains(response.code())) {
            throw new PermitApiException(
                    String.format(
                            "unexpected status code: %d for request: %s",
                            response.code(),
                            requestRepr
                    )
            );
        }
    }

    private void throwIfErrorResponseCode(String requestRepr, Response response, String responseContent) throws PermitApiException {
        throwIfErrorResponseCode(requestRepr, response, responseContent, List.of());
    }

    @Override
    public UserLoginResponse loginAs(String userId, String tenantId) throws IOException, PermitApiException {
        UserLoginRequest element = new UserLoginRequest();
        element.tenantId = tenantId;
        element.userId = userId;

        // request body
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        String requestBody = gson.toJson(element);
        RequestBody body = RequestBody.create(requestBody, MediaType.parse("application/json"));

        // create the request
        String url = String.format("%s/v2/auth/elements_login_as", this.config.getApiUrl());
        Request request = new Request.Builder()
                .url(url)
                .headers(this.headers)
                .post(body)
                .build();

        String requestRepr = String.format("permit.elements.login_as(%s)", requestBody);
        this.logger.debug(String.format("Sending request: %s", requestRepr));

        // send the request
        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                throw new IOException("got empty response");
            }
            String responseString = responseBody.string();
            throwIfErrorResponseCode(requestRepr, response, responseString);
            UserLoginResponse userLoginResponse = gson.fromJson(responseString, UserLoginResponse.class);
            userLoginResponse.content = new HashMap<>();
            userLoginResponse.content.put("url", userLoginResponse.redirectUrl);
            return userLoginResponse;
        }
    }

    public String getApiUrl() {
        return apiUrl;
    }
}

