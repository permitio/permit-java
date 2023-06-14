package io.permit.sdk;

public enum ApiContextLevel {
    WAIT_FOR_INIT(0),
    ORGANIZATION(1),
    PROJECT(2),
    ENVIRONMENT(3);

    private final int value;

    ApiContextLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}