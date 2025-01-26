package com.tcp.tcp_communication.usecase

import com.tcp.tcp_communication.model.request.CalcRequest
import com.tcp.tcp_communication.model.response.CalcResponse

interface CalcUseCase {
  fun execute(request: CalcRequest): CalcResponse
}