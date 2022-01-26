package io.permit.sdk;

import com.google.gson.Gson;
import io.permit.sdk.api.ApiClient;
import io.permit.sdk.api.models.UserModel;
import io.permit.sdk.enforcement.Enforcer;
import io.permit.sdk.enforcement.Resource;
import io.permit.sdk.enforcement.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class ApiTests {
    @Test void checkGetUser() {
        PermitConfig config = new PermitConfig.Builder("PJUKkuwiJkKxbIoC4o4cguWxB_2gX6MyATYKc2OCM").build();
        ApiClient client = new ApiClient(config);
        Gson gson = new Gson();

        try {
            UserModel user = client.getUser("55de594980944d48944dc10b9c70483c");
            System.out.println(user.id);
            System.out.println(gson.toJson(user));
        } catch (IOException e) {
            fail("got api error: " + e);
        }
    }
}