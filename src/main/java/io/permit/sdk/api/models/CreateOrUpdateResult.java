package io.permit.sdk.api.models;

public class CreateOrUpdateResult<T> {
    private final T object;
    private final boolean created;
    public CreateOrUpdateResult(T object, boolean created) {
        this.object = object;
        this.created = created;
    }

    public T getResult() {
        return this.object;
    }

    public boolean wasCreated() {
        return this.created;
    }
}
