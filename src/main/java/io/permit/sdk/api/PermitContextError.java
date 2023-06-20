package io.permit.sdk.api;

/**
 * The {@code PermitContextError} class represents an error that occurs when an API method
 * is called with insufficient context (not knowing in what environment, project or
 * organization the API call is being made).
 * Part of the input for each API method is provided implicitly via the SDK context.
 * If the context is missing some data required for a method - the api call will fail.
 */
public class PermitContextError extends Exception {
    public PermitContextError(String message) {
        super(message);
    }
}
