package netty.recordersecond.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class RecorderClient {

	private final String host = "127.0.0.1";
	private final int port = 9999;

	EventLoopGroup group = new NioEventLoopGroup();
	Bootstrap b = new Bootstrap();


	public static void main( String[] args ) {

		RecorderClientForm clientForm = new RecorderClientForm();

		RecorderClient client = new RecorderClient( clientForm );

		clientForm.setConnectButtonListener( event -> client.connect() );
		clientForm.setDisconnectButtonListener( event -> client.disconnect() );

	}

	RecorderClient( RecorderClientForm clientForm ) {
		b.group( group )
				.channel( NioSocketChannel.class )
				.remoteAddress( new InetSocketAddress( host, port ) )
				.handler( new ChannelInitializer< SocketChannel >() {
					@Override
					protected void initChannel( SocketChannel ch ) throws Exception {
						ch.pipeline().addLast( new RecorderClientHandler( clientForm ) );
					}
				} );
	}


	@Deprecated
	void start( RecorderClientForm clientForm ) throws InterruptedException {

		try {

			ChannelFuture f = b.connect().sync();
			f.channel().closeFuture().sync();
		} catch ( InterruptedException e ) {
			e.printStackTrace();
		} finally {

			group.shutdownGracefully().sync();
		}
	}

	public void connect() {
		try {

			ChannelFuture f = b.connect();
			f.channel().closeFuture().sync();
		} catch ( InterruptedException e ) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			group.shutdownGracefully().sync();
		} catch ( InterruptedException e ) {
			e.printStackTrace();
		}
	}
}
