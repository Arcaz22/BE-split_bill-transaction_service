package com.splitbill.transaction.service.notification;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.splitbill.transaction.factory.dto.notification.NotificationEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendNotification(String userId, String message, String type) {
        NotificationEvent event = NotificationEvent.builder()
                .userId(Long.parseLong(userId))
                .message(message)
                .type(type)
                .build();

        rabbitTemplate.convertAndSend("friendship_notifications", event);
    }
}
