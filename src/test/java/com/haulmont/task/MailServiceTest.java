package com.haulmont.task;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

public class MailServiceTest {
    @Mock
    private MailSender mailSender;
    @Spy
    private MessageRepository messageRepository;

    @Test
    public void argumentsTest() {
        Person personFrom = new Person();
        personFrom.setEmail("sender@mail.ru");
        Person personTo = new Person();
        personTo.setEmail("getter@mail.ru");
        Message message = new Message("", personFrom, personTo, "");

        MailService mailService = new MailService(mailSender, messageRepository);
        Mockito.when(mailSender.sendMessage(any(), any(), any(), any())).thenReturn(true);

        boolean result = mailService.sendMessage(message);

        assertTrue(result);
    }
}
