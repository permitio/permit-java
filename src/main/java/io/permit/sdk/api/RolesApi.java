package io.permit.sdk.api;

import com.google.gson.Gson;
import io.permit.sdk.PermitConfig;
import io.permit.sdk.openapi.models.RoleRead;
import okhttp3.*;
import org.slf4j.LoggerFactory;
import java.io.IOException;

public class RolesApi extends BaseApi {
    public RolesApi(OkHttpClient client, PermitConfig config) {
        super(client, config, LoggerFactory.getLogger(RolesApi.class));
    }

    public RoleRead get(String roleKey) throws IOException, PermitApiError {
        String url = buildUrl(String.format("/v2/schema/default/production/roles/%s", roleKey));
        Request request = buildRequest(
            new Request.Builder()
                .url(url)
                .get()
        );

        try (Response response = client.newCall(request).execute()) {
            String responseString = processResponseBody(response);
            Gson gson = new Gson();
            return gson.fromJson(responseString, RoleRead.class);
        }
    }
}
