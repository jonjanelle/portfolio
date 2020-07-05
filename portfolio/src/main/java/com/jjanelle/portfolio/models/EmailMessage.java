package com.jjanelle.portfolio.models;

public class EmailMessage {
    private final String subject;
    private final String senderName;
    private final String senderEmail;
    private final String body;

    public EmailMessage(String subject, String senderName, String senderEmail, String body) {
        this.subject = subject;
        this.senderName = senderName;
        this.senderEmail = senderEmail;
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public String getBody() {
        return body;
    }
}
