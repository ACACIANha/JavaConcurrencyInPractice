package netty.recorder;

import io.netty.bootstrap.Bootstrap;
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

	public static void main( String[] args ) throws Exception {

		RecorderClientForm clientForm = new RecorderClientForm();
//		RecorderClient client = new RecorderClient();
//		client.start( clientForm );
		clientForm.setButtonAddListener( e -> {
			try {
				new RecorderClient().start( clientForm );
			} catch ( Exception ex ) {
				ex.printStackTrace();
			}
		} );
	}

	void start( RecorderClientForm clientForm ) throws Exception {

		EventLoopGroup group = new NioEventLoopGroup();
		try {

			Bootstrap b = new Bootstrap();
			b.group( group )
					.channel( NioSocketChannel.class )
					.remoteAddress( new InetSocketAddress( host, port ) )
					.handler( new ChannelInitializer< SocketChannel >() {
						@Override
						protected void initChannel( SocketChannel ch ) throws Exception {
							ch.pipeline().addLast( new RecorderClientHandler( clientForm ) );
						}
					} );
			ChannelFuture f = b.connect().sync();
			f.channel().closeFuture().sync();
		} catch ( InterruptedException e ) {
			e.printStackTrace();
		} finally {

			group.shutdownGracefully().sync();
		}

	}
}
