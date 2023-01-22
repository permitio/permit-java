package io.permit.sdk;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import io.permit.sdk.api.PermitApiException;
import io.permit.sdk.api.models.UserLoginResponse;
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
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

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

    static Random rand = new Random();
    static String suffixedUserKey = "test|" + rand.nextInt();

    private final static String roleKey = "captain";
    private final static String tenantKey = "tortuga";
    private final static String userKey = suffixedUserKey;
    private final static String userEmail = "jack@pirates.com";
    private final static String userFirstName = "Jack";
    private final static String userLastName = "Sparrow";

    public PermitIntegrationTests() {
        String token = System.getenv("DEV_MODE_CLIENT_TOKEN");
        if (token == null) {
            token = "";
        }
        this.config = new PermitConfig.Builder(token)
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

    @Test void testPermitClientEnforcer() {
        if (skipTests) {
            return;
        }
        logTestIsStarting("permitCheckSucceeds");
        Permit permit = new Permit(this.config);
        Boolean allowed = null;
        try {
            allowed = permit.check(
                User.fromString(userKey),
                "create",
                Resource.fromString("document")
            );
        } catch (IOException e) {
            fail(e);
        }

        assertTrue(allowed, "permit.check() should be true");
    }

    @Test void testPermitElementsLoginAs() {
        if (skipTests) {
            return;
        }
        logTestIsStarting("permitCheckSucceeds");
        Permit permit = new Permit(this.config);
        UserLoginResponse loginAs = null;
        try {
            loginAs = permit.elements.loginAs("raz@permit.io", "fafb66f9c98647ad954f129b9f2b1c84");
        } catch (IOException e) {
            fail(e);
        } catch (PermitApiException e) {
            e.printStackTrace();
        }

        assertNotNull(loginAs.redirectUrl);
        assertNotNull(loginAs.content);
    }

    @Test void testPermitApiUserLifecycle() {
        if (skipTests) {
            return;
        }
        logTestIsStarting("checkGetUser");

        // objects to setup;
        User testUser = new User.Builder(userKey)
            .withEmail(userEmail)
            .withFirstName(userFirstName)
            .withLastName(userLastName)
            .build();

        // init the client
        Permit permit = new Permit(this.config);
        Gson gson = new Gson();

        // create user lifecycle
        try {
            // check if the test user exists - expect null
            UserModel user = permit.api.getUser(testUser.getKey());
            assertNull(user);
            // returned user after syncUser
            user = permit.api.syncUser(testUser);
            assertNotNull(user);
            assertEquals(user.customId, userKey);
            assertEquals(user.email, userEmail);
            assertEquals(user.firstName, userFirstName);
            assertEquals(user.lastName, userLastName);
            // getUser now returns the synced user
            user = permit.api.getUser(testUser.getKey());
            assertNotNull(user);
            assertEquals(user.customId, userKey);
            assertEquals(user.email, userEmail);
            assertEquals(user.firstName, userFirstName);
            assertEquals(user.lastName, userLastName);
            // delete the user
            boolean deleted = permit.api.deleteUser(testUser.getKey());
            assertTrue(deleted);
            // user will be null again
            user = permit.api.getUser(testUser.getKey());
            assertNull(user);
        } catch (IOException | PermitApiException e) {
            fail("got error: " + e);
        }
    }
}
