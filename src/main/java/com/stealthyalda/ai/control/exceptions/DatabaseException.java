package com.stealthyalda.ai.control.exceptions;

public class DatabaseException extends Exception {
    private String reason = null;

    public DatabaseException(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
