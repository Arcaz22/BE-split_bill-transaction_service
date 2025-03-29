package com.splitbill.transaction.service.notification;

public interface NotificationService {
    void sendNotification(String userId, String message, String type);
}
