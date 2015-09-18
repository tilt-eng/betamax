package co.freeside.betamax.proxy.jetty

import org.eclipse.jetty.server.Handler
import org.eclipse.jetty.proxy.ConnectHandler
import org.eclipse.jetty.server.Request

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.nio.channels.SocketChannel

class CustomConnectHandler extends ConnectHandler {

	int sslPort

	CustomConnectHandler(Handler handler, int sslPort) {
		super(handler)
		this.sslPort = sslPort
	}

	@Override
	protected void handleConnect(Request jettyRequest,
										  HttpServletRequest request,
										  HttpServletResponse response,
										  String serverAddress) {
		def channel = jettyRequest.getChannel()
		channel.socket().tcpNoDelay = true
		channel.socket().connect(new InetSocketAddress('127.0.0.1', sslPort), connectTimeout)
	}
}
