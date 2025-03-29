package com.splitbill.transaction.factory.dto.notification;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationEvent {
    private Long userId;
    private String message;
    private String type;
}
