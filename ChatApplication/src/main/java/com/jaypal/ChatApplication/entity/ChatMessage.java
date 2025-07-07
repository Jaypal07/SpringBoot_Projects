package com.jaypal.ChatApplication.entity;

import com.jaypal.ChatApplication.enums.MessageType;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ChatMessage {

    private MessageType type;
    private String content;
    private String sender;
}

