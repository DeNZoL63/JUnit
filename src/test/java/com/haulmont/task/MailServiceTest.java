package com.haulmont.task;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.*;
import static org.mockito.ArgumentMatchers.anyString;

public class MailServiceTest {

    private MailSender mailSender = Mockito.mock(MailSender.class);
    private MessageRepository messageRepository = Mockito.spy(MessageRepository.class);
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
    public void simplePositiveTest() {
        assertNull(message.getStatus());
        Mockito
                .when(mailSender.sendMessage(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(true);

        boolean result = mailService.sendMessage(message);
        assertTrue(result);
        assertEquals(Status.DELIVERED, message.getStatus());
    }

    @Test
    public void simpleNegativeTest() {
        assertNull(message.getStatus());
        boolean result = mailService.sendMessage(message);
        assertFalse(result);
        assertEquals(Status.ERROR, message.getStatus());
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

    @Test
    public void wasUsedTest() {
        mailService.sendMessage(message);
        Mockito.verify(mailSender).sendMessage(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    public void removeMessageTest() {
        mailService.removeMessage(message);
        assertEquals(Status.REMOVED, message.getStatus());
    }
}
