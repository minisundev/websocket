package org.minisun.websocket.dto

import org.jetbrains.annotations.NotNull

data class ChatRequest (
    @field:NotNull
    val username: String,
    @field:NotNull
    val content: String,
)