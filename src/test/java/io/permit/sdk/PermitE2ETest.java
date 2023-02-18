package io.permit.sdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class PermitE2ETest {
    protected final static Logger logger = LoggerFactory.getLogger(PermitIntegrationTests.class);
    protected final PermitConfig config;
    protected boolean skipTests = false;
    private final static int connectionTimeout = 3; // 3 seconds to give up on sidecar

    public PermitE2ETest() {
        String token = "permit_key_camCCsNhluGfSJXg2ZzLGlCWjXSYWJul68nCnthIOug8tR9PUfPGHeSYoImU6lWFzML7uJexzAG1GCdm8hyYxH";
        this.config = new PermitConfig.Builder(token)
                .withApiUrl("http://localhost:8000")
                .withDebugMode(true)
                .build();

//        HttpUrl pdpUrl = HttpUrl.parse(config.getPdpAddress());

//        try {
//            if (!isAddressReachable(pdpUrl.host(), pdpUrl.port(), connectionTimeout)) {
//                skipTests = true;
//                logger.warn(String.format("PDP at address %s is not reachable, SKIPPING TESTS.", config.getPdpAddress()));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    //    private static boolean isAddressReachable(String address, int port, int timeout) throws IOException {
//        Socket socket = new Socket();
//        try {
//            // Connects this socket to the server with a specified timeout value.
//            socket.connect(new InetSocketAddress(address, port), timeout);
//            // Return true if connection successful
//            return true;
//        } catch (IOException exception) {
//            // Return false if connection fails
//            return false;
//        } finally {
//            socket.close();
//        }
//    }
}
