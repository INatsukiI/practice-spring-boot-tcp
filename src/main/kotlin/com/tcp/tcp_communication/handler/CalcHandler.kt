package com.tcp.tcp_communication.handler

import com.tcp.tcp_communication.model.request.CalcRequest
import com.tcp.tcp_communication.model.response.CalcResponse
import org.springframework.messaging.Message

interface CalcHandler {
  fun handle(message: Message<CalcRequest>): Message<CalcResponse>
}