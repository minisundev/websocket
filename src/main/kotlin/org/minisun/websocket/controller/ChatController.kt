package org.minisun.websocket.controller

import lombok.RequiredArgsConstructor
import org.minisun.websocket.dto.ChatRequest
import org.minisun.websocket.dto.ChatResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller


@Controller
@RequiredArgsConstructor
class ChatController(
) {
    private val logger: Logger = LoggerFactory.getLogger(ChatController::class.java)
    private val simpMessagingTemplate: SimpMessagingTemplate? = null

    @MessageMapping("/chat/send")
    fun sendMsg(@Payload data: Map<String?, Any?>?) {
        simpMessagingTemplate!!.convertAndSend("/topic/1", data!!)
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    fun send(chat: ChatRequest): ChatResponse {
        return ChatResponse(
            id = 1,
            username = "username1",
            content = chat.content,
        )
    }

    @MessageMapping("/createChat")
    @SendTo("/topic/response")
    fun createChat(
        @Payload @Validated request: ChatRequest,
        headerAccessor: SimpMessageHeaderAccessor,
    ): ChatResponse {
        logger.info("createChat")

        logger.info("Creating Room chat with request: $request")
        val result = chatService.createRoomChat(request)

        logger.info("Chat created successfully with request: $request")

        return result
    }
}