package com.jjanelle.portfolio.models;

import java.util.ArrayList;
import java.util.List;

public class EmailMessageValidationCommand implements ValidationCommand {
    private final EmailMessage emailMessage;
    private final List<ValidationError> validationErrors;
    
    public EmailMessageValidationCommand(EmailMessage message){
        emailMessage = message;
        validationErrors = new ArrayList<>();
    }
    
    @Override
    public List<ValidationError> validate() {
        if (emailMessage != null) {
            if (emailMessage.getBody() == null || emailMessage.getBody().trim().length() == 0)
                validationErrors.add(new ValidationError(EmailMessage.class.getName(), "Email body is required."));
            if (emailMessage.getSenderName() == null || emailMessage.getSenderName().trim().length() == 0)
                validationErrors.add(new ValidationError(EmailMessage.class.getName(), "Sender name is required.")); 
            if (emailMessage.getSenderEmail() == null || emailMessage.getSenderEmail().trim().length() == 0)
                validationErrors.add(new ValidationError(EmailMessage.class.getName(), "Sender email address is required.")); 
        }
        else
            validationErrors.add(new ValidationError(EmailMessage.class.getName(), "Request body cannot be null."));
        
        return validationErrors;
    }
    
}
