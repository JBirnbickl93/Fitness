package org.birnbickl.fitness.errorhandling;

import java.time.LocalDateTime;
import java.util.List;

public class ApiError {


    private final LocalDateTime timestamp;
    private final String message;
    private final int status;
    private final List<String> details;

    public ApiError(LocalDateTime timestamp, String message, int status, List<String> details) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
    public List<String> getDetails() {
        return details;
    }

}


