package com.example.employee.exception;

import java.time.LocalDateTime;

public class ErrorInfo {
    private String message;
    private Integer errorCode;
    private LocalDateTime time;

    public ErrorInfo() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
