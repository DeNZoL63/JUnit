package com.haulmont.task;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
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

        message = messageRepository.addMessage(personFrom, personTo, "testSubject", "testText");
        mailService = new MailService(mailSender, messageRepository);
    }

    @Test
    public void simplePositiveTest() {
        assertEquals(Status.NEW, message.getStatus());
        Mockito
                .when(mailSender.sendMessage(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(true);

        boolean result = mailService.sendMessage(message);
        assertTrue(result);
        assertEquals(Status.DELIVERED, message.getStatus());
    }

    @Test
    public void simpleNegativeTest() {
        assertEquals(Status.NEW, message.getStatus());
        boolean result = mailService.sendMessage(message);
        assertFalse(result);
        assertEquals(Status.ERROR, message.getStatus());
    }

    @Test
    public void argumentsTest() {
        ArgumentCaptor<String> emailToCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> emailFromCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> subjectCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> textCaptor = ArgumentCaptor.forClass(String.class);

        mailService.sendMessage(message);

        Mockito
                .verify(mailSender)
                .sendMessage(emailFromCaptor.capture(), emailToCaptor.capture(), subjectCaptor.capture(), textCaptor.capture());

        assert emailToCaptor.getValue().equals(message.getTo().getEmail());
        assert emailFromCaptor.getValue().equals(message.getFrom().getEmail());
        assert subjectCaptor.getValue().equals(message.getSubject());
        assert textCaptor.getValue().equals(message.getText());
    }

    @Test
    public void wasUsedTest() {
        mailService.sendMessage(message);
        Mockito.verify(mailSender).sendMessage(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    public void removeMessageTest() {
        setUpRepository();
        mailService.removeMessage(message);
        assertEquals(Status.REMOVED, message.getStatus());
    }

    private void setUpRepository() {
        Mockito.doAnswer(invocation -> {
            Message message = invocation.getArgument(0);
            message.setStatus(Status.REMOVED);
            return null;
        }).when(messageRepository).removeMessage(message);
    }
}
