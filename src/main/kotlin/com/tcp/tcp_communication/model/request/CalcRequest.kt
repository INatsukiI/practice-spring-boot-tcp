package com.tcp.tcp_communication.model.request

import com.tcp.tcp_communication.model.enum.Operation

data class CalcRequest(
  val num1: Int,
  val num2: Int,
  val operation: Operation
)