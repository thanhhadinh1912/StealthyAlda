package com.stealthyalda.ai.control.exceptions;

public class UserExistsException extends Throwable {
    private String reason = null;

    public UserExistsException(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
