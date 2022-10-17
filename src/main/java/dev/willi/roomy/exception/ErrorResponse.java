package dev.willi.roomy.exception;

public class ErrorResponse {
    
    // attributes
    
    private int status;
    private String message;
    private long timeStamp;
    
    // constructors
    
    public ErrorResponse() {}

    public ErrorResponse(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }
    
    // getters & setters

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }    
    
}
