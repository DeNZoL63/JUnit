package com.haulmont.task;

public class Message {
    private String subject;
    private Person from;
    private Person to;
    private Status status;
    private String text;

    public Message(String subject, Person from, Person to, String text) {
        this.subject = subject;
        this.from = from;
        this.to = to;
        this.text = text;
    }

    public Person getFrom() {
        return from;
    }

    public void setFrom(Person from) {
        this.from = from;
    }

    public Person getTo() {
        return to;
    }

    public void setTo(Person to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
