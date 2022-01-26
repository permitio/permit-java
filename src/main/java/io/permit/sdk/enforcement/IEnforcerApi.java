package io.permit.sdk.enforcement;

import io.permit.sdk.util.Context;

import java.io.IOException;

public interface IEnforcerApi {
    boolean check(User user, String action, Resource resource, Context context) throws IOException;
    boolean check(User user, String action, Resource resource) throws IOException;
}
