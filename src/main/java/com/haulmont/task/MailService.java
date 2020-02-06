package com.haulmont.task;

public class MailService {
    private MailSender mailSender;
    private MessageRepository messageRepository;

    public MailService(MailSender mailSender, MessageRepository messageRepository) {
        this.mailSender = mailSender;
        this.messageRepository = messageRepository;
    }

    public boolean sendMessage(Message message) {
        boolean success = mailSender.sendMessage(message.getFrom().getEmail(),
                message.getTo().getEmail(), message.getSubject(), message.getText());
        if (success) {
            message.setStatus(Status.DELIVERED);
            return true;
        }
        message.setStatus(Status.ERROR);
        return false;
    }

    public void removeMessage(Message message) {
        messageRepository.removeMessage(message);//This method removes message from database and sets status of message to REMOVED. Mock this behavior(without DB of course)
    }

}
//Необходимо покрыть тестами класс com.haulmont.task.MailService
//Требования: Обычный тест, мок возвращаемого значения, проверка вызвался ли метод, с правильными ли параметрами вызван метод. Adv - мок поведения
//Подсказки:
//Правильно ли подставились аргументы в mailSender.sendMessage(...)
//Правильно ли сменился статус
//статус при другом ответе от сервера
//обратились ли к MailSender
//Замокать поведение репозитория, проверить поменялся ли статус
