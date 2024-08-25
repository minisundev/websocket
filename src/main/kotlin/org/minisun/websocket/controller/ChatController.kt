package org.minisun.websocket.controller

import lombok.RequiredArgsConstructor
import org.minisun.websocket.dto.ChatRequest
import org.minisun.websocket.dto.ChatResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import org.springframework.validation.annotation.Validated


@Controller
@RequiredArgsConstructor
class ChatController {
    private val logger: Logger = LoggerFactory.getLogger(ChatController::class.java)

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    fun send(chat: ChatRequest): ChatResponse {
        return ChatResponse(
            id = 1,
            username = chat.username,
            content = chat.content,
        )
    }
}