package org.minisun.websocket.global

import org.springframework.http.HttpStatus

data class ApiResponse<T>(
    var code: String? = null,
    var message: String? = null,
    var data: T? = null
) {
    companion object {
        fun success(): ApiResponse<Nothing> {
            return ApiResponse(
                code = HttpStatus.OK.toString()
            )
        }
    }

    fun message(message: String): ApiResponse<T> {
        this.message = message
        return this
    }

    fun data(data: T): ApiResponse<T> {
        this.data = data
        return this
    }
}
