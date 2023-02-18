package io.permit.sdk.api;

import com.google.gson.Gson;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.api.models.RoleModel;
import io.permit.sdk.openapi.models.RoleRead;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class RolesApi {
    private final OkHttpClient client;
    private final PermitConfig config;
    final static Logger logger = LoggerFactory.getLogger(RolesApi.class);

    final static int HTTP_404_NOT_FOUND = 404;
    private final Headers headers;

    public RolesApi(OkHttpClient client, PermitConfig config, Headers headers) {
        this.client = client;
        this.config = config;
        this.headers = headers;
    }

    private void throwIfErrorResponseCode(String requestRepr, Response response, String responseContent, List<Integer> expectedErrorCodes) throws PermitApiException {
        String log = String.format("Received response: %s : status code %d : %s", requestRepr, response.code(), responseContent);
        if (!response.isSuccessful() && this.config.isDebugMode()) {
            logger.error(log);
        } else {
            logger.debug(log);
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

    public RoleRead get(String roleKey)  throws IOException, PermitApiException {
        String url = String.format("%s/v2/schema/default/production/roles/%s", config.getApiUrl(), roleKey);
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .get()
                .build();

        String requestRepr = String.format("permit.api.getRole(%s)", roleKey);
        logger.debug(String.format("Sending request: %s", requestRepr));

        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                throw new IOException("got empty response");
            }
            String responseString = responseBody.string();
            throwIfErrorResponseCode(requestRepr, response, responseString, List.of(HTTP_404_NOT_FOUND));
            if (response.code() == HTTP_404_NOT_FOUND) {
                return null;
            }
            Gson gson = new Gson();
            return gson.fromJson(responseString, RoleRead.class);
        }
    }
}
