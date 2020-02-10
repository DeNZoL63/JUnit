package com.haulmont.task;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

public class MailServiceTest {

    private MailSender mailSender = Mockito.mock(MailSender.class);
    private MessageRepository messageRepository = Mockito.mock(MessageRepository.class);

    @Test
    public void simpleTest() {
        Person personFrom = new Person();
        personFrom.setEmail("sender@mail.ru");
        Person personTo = new Person();
        personTo.setEmail("getter@mail.ru");
        Message message = new Message("", personFrom, personTo, "");

        MailService mailService = new MailService(mailSender, messageRepository);
        Mockito.when(mailSender.sendMessage(anyString(), anyString(), anyString(), anyString())).thenReturn(true);

        boolean result = mailService.sendMessage(message);
        assertTrue(result);
    }
}
