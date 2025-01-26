package com.tcp.tcp_communication.handler

import com.tcp.tcp_communication.model.enum.Operation
import com.tcp.tcp_communication.model.enum.Status
import com.tcp.tcp_communication.model.request.CalcRequest
import com.tcp.tcp_communication.model.response.CalcResponse
import com.tcp.tcp_communication.shared.annotation.Handler
import com.tcp.tcp_communication.usecase.CalcUseCase
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder

@Handler
class CalcHandlerImpl(private val calcUseCase: CalcUseCase) : CalcHandler {
    override fun handle(message: Message<CalcRequest>): Message<CalcResponse> {
        val response = when (message.payload.operation) {
            Operation.OTHER -> CalcResponse(0, Status.ERROR)
            else -> calcUseCase.execute(message.payload)
        }

        return MessageBuilder.withPayload(response)
            .copyHeaders(message.headers)
            .build()
    }
}