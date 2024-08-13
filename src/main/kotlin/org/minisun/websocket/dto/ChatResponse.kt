package org.minisun.websocket.dto

data class ChatResponse (
    val id: Long,
    val username: String,
    val content: String,
)