
package com.jjanelle.portfolio.models;

public class ValidationError {
    private final String className;
    private final String message;

    public ValidationError(String className, String message) {
        this.className = className;
        this.message = message;
    }

    public String getClassName() {
        return className;
    }

    public String getMessage() {
        return message;
    }

    
    
}
