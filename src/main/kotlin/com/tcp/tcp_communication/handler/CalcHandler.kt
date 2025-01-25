package com.tcp.tcp_communication.handler

import com.tcp.tcp_communication.annotation.Handler
import com.tcp.tcp_communication.model.request.CalcRequest
import com.tcp.tcp_communication.model.response.CalcResponse
import com.tcp.tcp_communication.usecase.CalcUseCase

@Handler
class CalcHandler(
    private val calcUseCase: CalcUseCase
) {
    fun handle(request: CalcRequest): CalcResponse {
        return calcUseCase.execute(request)
    }
}