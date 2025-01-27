package com.tcp.tcp_communication.usecase

import com.tcp.tcp_communication.model.enum.Operation
import com.tcp.tcp_communication.model.enum.Status
import com.tcp.tcp_communication.model.request.CalcRequest
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class CalcUseCaseImplTest: StringSpec({
  lateinit var sut: CalcUseCaseImpl

  beforeTest {
    sut = CalcUseCaseImpl()
  }

  "加算が正しく計算される" {
    val request = CalcRequest(
      num1 = 5,
      num2 = 3,
      operation = Operation.ADD
    )

    val response = sut.execute(request)

    response.result shouldBe 8
    response.status shouldBe Status.SUCCESS
  }

  "減算が正しく計算される" {
    val request = CalcRequest(
      num1 = 10,
      num2 = 4,
      operation = Operation.SUBTRACT
    )

    val response = sut.execute(request)

    response.result shouldBe 6
    response.status shouldBe Status.SUCCESS
  }

  "無効な操作が指定された場合はエラーがスローされる" {
    val request = CalcRequest(
      num1 = 10,
      num2 = 5,
      operation = Operation.OTHER
    )

    shouldThrow<IllegalStateException> {
      sut.execute(request)
    }
  }
})