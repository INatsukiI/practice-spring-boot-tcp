package com.tcp.tcp_communication.model.response

import com.tcp.tcp_communication.model.enum.Status

data class CalcResponse(
  val result: Int,
  val status: Status
)