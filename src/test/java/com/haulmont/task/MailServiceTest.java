package com.haulmont.task;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

public class MailServiceTest {

    private MailSender mailSender = Mockito.mock(MailSender.class);
    private MessageRepository messageRepository = Mockito.mock(MessageRepository.class);
    private Message message;
    private MailService mailService;

    @Before
    public void beforeTests() {
        Person personFrom = new Person();
        personFrom.setEmail("sender@mail.ru");
        Person personTo = new Person();
        personTo.setEmail("getter@mail.ru");

        message = new Message("testSubject", personFrom, personTo, "testText");
        mailService = new MailService(mailSender, messageRepository);
    }

    @Test
    public void simpleTest() {
        Mockito
                .when(mailSender.sendMessage(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(true);

        boolean result = mailService.sendMessage(message);
        assertTrue(result);
    }

    @Test
    public void argumentsTest() {

        Mockito
                .when(mailSender.sendMessage(message.getFrom().getEmail(), message.getTo().getEmail(),
                        message.getSubject(), message.getText()))
                .thenReturn(true);

        boolean result = mailService.sendMessage(message);
        assertTrue(result);
    }

}
