package com.stealthyalda.ai.control.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends Throwable {
    private final List<String> failures = new ArrayList<>();

    public ValidationError(String reason) {
        failures.add(reason);
    }

    public List<String> getReason() {
        return failures;
    }

    public void setReason(String reason) {
        this.failures.add(reason);
    }
}
