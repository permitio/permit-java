package io.permit.sdk.enforcement;

import io.permit.sdk.util.Context;

public final class GetUserTenantsQuery {
    public final User user;
    public final Context context;

    /**
     * input to get user tenants api
     *
     * @param user              the user we'd like to get a list of permissions for.
     * @param context  The context for the authorization check.
     */
    public GetUserTenantsQuery(User user, Context context) {
        this.user = user;
        this.context = context;
    }
    public GetUserTenantsQuery(User user) {
        this(user, new Context());
    }
}
