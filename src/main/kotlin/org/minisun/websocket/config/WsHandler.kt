package org.minisun.websocket.config

import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import org.springframework.web.socket.CloseStatus
import java.io.IOException

class WsHandler : TextWebSocketHandler() {

    companion object {
        var ID = 0
    }

    private val sessionMap = mutableMapOf<WebSocketSession, Int>()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        println("Connection established: $session")
        sessionMap[session] = ++ID
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val msg = message.payload
        val id = sessionMap[session]
        val textMessage = TextMessage("$id: $msg")

        for (client in sessionMap.keys) {
            try {
                client.sendMessage(textMessage)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        println("Connection closed: $session")
        sessionMap.remove(session)
        session.close()
    }
}
