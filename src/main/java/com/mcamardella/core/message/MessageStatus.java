package com.mcamardella.core.message;

import com.mcamardella.core.enumeration.MessageStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class MessageStatus {
    private String message;
    private String developerMessage;
    private String reason;
    private MessageStatusEnum status;
    private Integer statusCode;
    private Map<?, ?> data;

    public MessageStatus(String message, String developerMessage, String reason, MessageStatusEnum status, Integer statusCode) {
        this.message = message;
        this.developerMessage = developerMessage;
        this.reason = reason;
        this.status = status;
        this.statusCode = statusCode;
    }
}
