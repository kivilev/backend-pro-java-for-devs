package ru.backendpro.entity;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    PENDING(0, false),
    COMPLETED(1, true),
    FAILED(2, true),
    CANCELLED(3, true);

    private final int id;
    private final boolean isFinalStatus;

    PaymentStatus(int id, boolean isFinalStatus) {
        this.id = id;
        this.isFinalStatus = isFinalStatus;
    }

    public static PaymentStatus fromId(int id) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.id == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown PaymentStatus id: " + id);
    }
}

