package io.permit.sdk;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import io.permit.sdk.api.PermitApiError;
import io.permit.sdk.api.PermitContextError;
import io.permit.sdk.openapi.models.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this e2e test should run against a clean permit environment.
 * if the environment contains any objects the test will fail.
 * eventually we want to create an environment programmatically
 * and then extract the api key and start the test.
 */
public class PermissionCheckE2ETest extends PermitE2ETestBase {
    @Test
    void testPermitCheck() {
        // init the client
//        Permit permit = new Permit(this.config);
//        Gson gson = new Gson();
//
//        try {
            // set up resources
//            permit.api.resources.create(new ResourceCreate());

            // set up roles

            // assign permissions to roles

            // create a tenant

            // create a user

            // assign role to user in tenant

            // positive permission check

            // negative permission check

            // list all objects

            // cleanup
//
//        }
//        catch (IOException | PermitApiError | PermitContextError e) {
//            fail("got error: " + e);
//        }
    }
}
