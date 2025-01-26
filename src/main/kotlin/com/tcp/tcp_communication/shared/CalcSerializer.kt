package com.tcp.tcp_communication.shared

import com.tcp.tcp_communication.model.enum.Operation
import com.tcp.tcp_communication.model.enum.Status
import com.tcp.tcp_communication.model.request.CalcRequest
import com.tcp.tcp_communication.model.response.CalcResponse
import org.springframework.core.serializer.Deserializer
import org.springframework.core.serializer.Serializer
import org.springframework.stereotype.Component
import java.io.InputStream
import java.io.OutputStream

@Component
class CalcSerializer : Serializer<CalcResponse>, Deserializer<CalcRequest> {
  override fun deserialize(inputStream: InputStream): CalcRequest {
    return try {
      val buffer = ByteArray(BUFFER_SIZE)
      if (inputStream.read(buffer) != BUFFER_SIZE) {
        error("Invalid input size")
      }
      val num1 = convertToInt(buffer[FIRST_NUMBER_INDEX])
      val num2 = convertToInt(buffer[SECOND_NUMBER_INDEX])
      val operation = when (convertToInt(buffer[OPERATION_INDEX]).toChar()) {
        ADDITION_SYMBOL -> Operation.ADD
        SUBTRACTION_SYMBOL -> Operation.SUBTRACT
        else -> error("Invalid operation")
      }

      CalcRequest(num1, num2, operation)
    } catch (e: Exception) {
      CalcRequest(0, 0, Operation.OTHER)
    }
  }

  override fun serialize(response: CalcResponse, outputStream: OutputStream) {
    val responseBytes = when (response.status) {
      Status.SUCCESS -> buildResponseBytes(SUCCESS_STATUS, response.result)
      Status.ERROR -> buildResponseBytes(ERROR_STATUS, ERROR_RESULT)
    }
    outputStream.write(responseBytes)
  }

  private fun buildResponseBytes(status: Char, calcResult: Int): ByteArray =
    byteArrayOf(status.code.toByte(), calcResult.toByte())

  private fun convertToInt(byte: Byte): Int =
    String.format("%02X", byte).toInt(16)

  companion object {
    private const val BUFFER_SIZE = 3
    private const val FIRST_NUMBER_INDEX = 1
    private const val SECOND_NUMBER_INDEX = 2
    private const val OPERATION_INDEX = 0
    private const val SUCCESS_STATUS = 'A'
    private const val ERROR_STATUS = 'N'
    private const val ERROR_RESULT = 0
    private const val ADDITION_SYMBOL = '+'
    private const val SUBTRACTION_SYMBOL = '-'
  }
}