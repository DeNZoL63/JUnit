package com.haulmont.task;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MessageRepository {
    public void removeMessage(Message message) {
        throw new NotImplementedException();
    }

    public Message addMessage(Person from, Person to, String subject, String text) {
        Message message = new Message(subject, from, to, text);
        message.setStatus(Status.NEW);
        return message;
    }
}
