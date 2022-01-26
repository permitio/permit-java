package io.permit.sdk;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import io.permit.sdk.api.models.UserModel;
import io.permit.sdk.enforcement.Resource;
import io.permit.sdk.enforcement.User;
import okhttp3.HttpUrl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Sanity tests for basic usage of the SDK.
 *
 * These tests will only work against a running PDP.
 * Therefore, they are not considered unit tests.
 */
class PermitIntegrationTests {
    private final static Logger logger = LoggerFactory.getLogger(PermitIntegrationTests.class);
    private final PermitConfig config;
    private final static int connectionTimeout = 3; // 3 seconds to give up on sidecar
    private final static int loggerSeparatorLength = 80;
    private boolean skipTests = false;

    public PermitIntegrationTests() {
        this.config = new PermitConfig.Builder("PJUKkuwiJkKxbIoC4o4cguWxB_2gX6MyATYKc2OCM")
                .withDebugMode(true)
                .build();

        HttpUrl pdpUrl = HttpUrl.parse(config.getPdpAddress());

        try {
            if (!isAddressReachable(pdpUrl.host(), pdpUrl.port(), connectionTimeout)) {
                skipTests = true;
                logger.warn(String.format("PDP at address %s is not reachable, SKIPPING TESTS.", config.getPdpAddress()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isAddressReachable(String address, int port, int timeout) throws IOException {
        Socket socket = new Socket();
        try {
            // Connects this socket to the server with a specified timeout value.
            socket.connect(new InetSocketAddress(address, port), timeout);
            // Return true if connection successful
            return true;
        } catch (IOException exception) {
            // Return false if connection fails
            return false;
        } finally {
            socket.close();
        }
    }

    private static void logTestIsStarting(String testName) {
        logger.info(Strings.repeat("-", loggerSeparatorLength));
        logger.info(String.format("Running test: %s", testName));
        logger.info(Strings.repeat("-", loggerSeparatorLength));
    }

    @Test void permitCheckSucceeds() {
        if (skipTests) {
            return;
        }
        logTestIsStarting("permitCheckSucceeds");
        Permit permit = new Permit(this.config);
        Boolean allowed = null;
        try {
            allowed = permit.check(
                User.fromString("55de594980944d48944dc10b9c70483c"),
                "create",
                Resource.fromString("document")
            );
        } catch (IOException e) {
            fail(e);
        }

        assertTrue(allowed, "permit.check() should be true");
    }

    @Test void checkGetUser() {
        if (skipTests) {
            return;
        }
        logTestIsStarting("checkGetUser");
        Permit permit = new Permit(this.config);
        Gson gson = new Gson();

        try {
            UserModel user = permit.api.getUser("55de594980944d48944dc10b9c70483c");
            System.out.println(user.id);
            System.out.println(gson.toJson(user));
        } catch (IOException e) {
            fail("got api error: " + e);
        }
    }
}
