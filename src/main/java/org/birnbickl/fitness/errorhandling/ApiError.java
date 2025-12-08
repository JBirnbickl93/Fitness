package org.birnbickl.fitness.errorhandling;

import java.time.LocalDateTime;
import java.util.List;

public class ApiError {


    private LocalDateTime timestamp;
    private String message;
    private int status;
    private List<String> details;

    public ApiError(LocalDateTime timestamp, String message, int status, List<String> details) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }




}


