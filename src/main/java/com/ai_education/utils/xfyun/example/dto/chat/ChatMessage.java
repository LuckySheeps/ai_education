package com.ai_education.utils.xfyun.example.dto.chat;

import lombok.Getter;
import lombok.Setter;

/**
 * ChatMessage
 *
 * @author ydwang16
 * @version 2023/06/21 15:43
 **/
@Getter
@Setter
public class ChatMessage {

    private String role;

    private String content;
}