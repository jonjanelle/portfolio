package com.jjanelle.portfolio.controllers;

import com.jjanelle.portfolio.models.EmailMessage;
import com.jjanelle.portfolio.models.EmailMessageValidationCommand;
import com.jjanelle.portfolio.models.ValidationError;
import com.jjanelle.portfolio.services.SendEmailService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    private final SendEmailService _mailService; 
    
    public EmailController(SendEmailService mailService) {
        _mailService = mailService;
    }
    
    @CrossOrigin
    @PostMapping("/email")
    public ResponseEntity SendEmail(@RequestBody EmailMessage emailMessage) {
        List<ValidationError> validationErrors = new EmailMessageValidationCommand(emailMessage).validate();
        if (validationErrors.size() > 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors);
        try {
            _mailService.send(emailMessage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while sending e-mail: " + e.getMessage());
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(emailMessage);
    }
}
