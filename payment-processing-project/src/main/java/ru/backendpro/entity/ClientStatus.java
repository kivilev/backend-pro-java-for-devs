package ru.backendpro.entity;

import lombok.Getter;

@Getter
public enum ClientStatus {
    INACTIVE(0, true),
    ACTIVE(1, false),
    BLOCKED(2, false);

    private final int id;
    private final boolean isFinal;

    ClientStatus(int id, boolean isFinal) {
        this.id = id;
        this.isFinal = isFinal;
    }

    public static ClientStatus fromId(int id) {
        for (ClientStatus status : ClientStatus.values()) {
            if (status.id == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown ClientStatus id: " + id);
    }
}
