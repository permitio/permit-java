package io.permit.sdk.api;

/**
 * The {@code PermitContextChangeError} will be thrown when the user is trying to set the
 * SDK context to an object that the current API Key cannot access (and if allowed,
 * such API calls will result in a 401 Unauthorized response). Instead, the SDK throws
 * this exception.
 */
public class PermitContextChangeError extends Exception {
    public PermitContextChangeError(String message) {
        super(message);
    }
}
