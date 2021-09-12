package com.halter.backendTest.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CollarStatus {
    BROKEN("Broken"),
    HEALTHY("Healthy");

    private final String label;

    CollarStatus(String label) { this.label = label; }

    @Override
    @JsonValue
    public String toString() { return this.label; }
}
