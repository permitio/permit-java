package io.permit.sdk;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.HttpUrl;

public abstract class PermitE2ETestBase {
    protected final static Logger logger = LoggerFactory.getLogger(PermitE2ETestBase.class);
    protected final PermitConfig config;
    protected final PermitConfig opaConfig;
    protected boolean skipTests = false;
    private final static int connectionTimeout = 10; // 3 seconds to give up on sidecar / API

    public PermitE2ETestBase() {
        final String token = System.getenv().getOrDefault("PDP_API_KEY", "");
        final String pdpAddress = System.getenv().getOrDefault("PDP_URL", "http://localhost:7766");
        final String opaAddress = System.getenv().getOrDefault("OPA_URL", "http://localhost:8181/v1/data/permit");
        final String pdpControlPlane = System.getenv().getOrDefault("PDP_CONTROL_PLANE", "https://api.permit.io");

        this.config = new PermitConfig.Builder(token)
            .withApiUrl(pdpControlPlane)
            .withPdpAddress(pdpAddress)
            .withDebugMode(true)
            .build();

        
        this.opaConfig = new PermitConfig.Builder(token)
            .withApiUrl(pdpControlPlane)
            .withOpaAddress(opaAddress)
            .withDebugMode(true)
            .build();
        
        HttpUrl apiUrl = HttpUrl.parse(config.getApiUrl());
        HttpUrl pdpUrl = HttpUrl.parse(config.getPdpAddress());

        try {
            if (!isAddressReachable(apiUrl.host(), apiUrl.port(), connectionTimeout)) {
                skipTests = true;
                logger.warn(String.format("Permit API is not reachable (expected at address %s), SKIPPING TESTS.", config.getApiUrl()));
            }
            if (!isAddressReachable(pdpUrl.host(), pdpUrl.port(), connectionTimeout)) {
                skipTests = true;
                logger.warn(String.format("PDP is not reachable (expected at address %s), SKIPPING TESTS.", config.getPdpAddress()));
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
}
