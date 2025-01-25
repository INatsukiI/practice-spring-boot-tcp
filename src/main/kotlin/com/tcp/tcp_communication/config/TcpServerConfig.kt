package com.tcp.tcp_communication.config

import com.tcp.tcp_communication.handler.CalcHandler
import com.tcp.tcp_communication.shared.CalcSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.ip.dsl.Tcp
import org.springframework.integration.ip.dsl.TcpInboundGatewaySpec

@Configuration
class TcpServerConfig(
  @Value("\${tcp.server.port}")
  private val port: Int,
  private val calcHandler: CalcHandler,
  private val calcSerializer: CalcSerializer,
) {
  @Bean
  fun tcpServer(): IntegrationFlow {
    return IntegrationFlow.from(generateInboundGateway())
      .handle(calcHandler)
      .get()
  }

  private fun generateInboundGateway(): TcpInboundGatewaySpec {
    return Tcp.inboundGateway(
      Tcp.nioServer(port)
        .serializer(calcSerializer)
        .deserializer(calcSerializer)
    )
  }
}