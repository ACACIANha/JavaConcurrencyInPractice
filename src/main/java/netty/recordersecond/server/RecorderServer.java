package netty.recordersecond.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class RecorderServer {


	public static void main( String[] args ) {

		RecorderServerForm serverForm = new RecorderServerForm();
		
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		int port = 9999;

		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group( workerGroup )
					.channel( NioServerSocketChannel.class )
					.localAddress( new InetSocketAddress( port ) )
					.childHandler( new ChannelInitializer< SocketChannel >() {
						@Override
						public void initChannel( SocketChannel ch ) {
							ch.pipeline().addLast( new RecorderServerHandler( serverForm ) );
						}
					} );

			ChannelFuture f = b.bind().sync();

			f.channel().closeFuture().sync();
		} catch ( InterruptedException e ) {
			e.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}
}
