package com.devsuperior.bds02.controllers.exceptions;

import java.io.Serializable;
import java.time.Instant;

public class StandartError implements Serializable {
    private static final long serialVersionUID = 1L;

    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    private String paht;

    public StandartError() {
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPaht() {
        return paht;
    }

    public void setPaht(String paht) {
        this.paht = paht;
    }
}
