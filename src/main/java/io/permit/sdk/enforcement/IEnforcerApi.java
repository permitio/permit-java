package io.permit.sdk.enforcement;

import io.permit.sdk.util.Context;

public interface IEnforcerApi {
    boolean check(User user, String action, Resource resource, Context context);
    boolean check(User user, String action, Resource resource);
}
