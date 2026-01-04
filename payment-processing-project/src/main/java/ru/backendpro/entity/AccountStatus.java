package ru.backendpro.entity;

import lombok.Getter;

@Getter
public enum AccountStatus {
    ACTIVE(1),
    BLOCKED(0);

    private final int id;

    AccountStatus(int id) {
        this.id = id;
    }

    public static AccountStatus fromId(int id) {
        for (AccountStatus status : AccountStatus.values()) {
            if (status.id == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown AccountStatus id: " + id);
    }
}

