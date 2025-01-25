package com.tcp.tcp_communication.usecase

import com.tcp.tcp_communication.model.enum.Operation
import com.tcp.tcp_communication.model.enum.Status
import com.tcp.tcp_communication.model.request.CalcRequest
import com.tcp.tcp_communication.model.response.CalcResponse
import com.tcp.tcp_communication.shared.annotation.UseCase

@UseCase
class CalcUseCaseImpl: CalcUseCase {
  override fun execute(request: CalcRequest): CalcResponse {
    val result = when (request.operation) {
      Operation.ADD -> request.num1 + request.num2
      Operation.SUBTRACT -> request.num1 - request.num2
    }
    return CalcResponse(result, Status.SUCCESS)
  }
}