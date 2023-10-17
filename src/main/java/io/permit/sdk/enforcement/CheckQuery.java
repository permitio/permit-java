package io.permit.sdk.enforcement;

import io.permit.sdk.util.Context;

import java.util.HashMap;

/**
 * The {@code CheckQuery} class represents a single permit.check() request (query)
 * It is used by the bulk APIs to call many checks at once.
 */
public final class CheckQuery extends EnforcerInput {
    public CheckQuery(User user, String action, Resource resource, Context context) {
        super(user, action, resource, context);
    }

    public CheckQuery(User user, String action, Resource resource) {
        this(user, action, resource, new Context());
    }
}
