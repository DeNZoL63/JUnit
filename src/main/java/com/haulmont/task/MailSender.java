package com.haulmont.task;

public interface MailSender {
    boolean sendMessage(String from, String to, String subject, String text);
}
