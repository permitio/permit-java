package io.permit.sdk;

import io.permit.sdk.enforcement.Enforcer;
import io.permit.sdk.enforcement.Resource;
import io.permit.sdk.enforcement.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class EnforcementTest {
    @Test void permitCheckSucceeds() {
        PermitConfig config = new PermitConfig.Builder("PJUKkuwiJkKxbIoC4o4cguWxB_2gX6MyATYKc2OCM").build();
        Enforcer enforcer = new Enforcer(config);

        Boolean allowed = enforcer.check(
            User.fromString("55de594980944d48944dc10b9c70483c"),
            "create",
            Resource.fromString("document")
        );

        assertTrue(allowed, "permit.check() should be true");
    }
}
