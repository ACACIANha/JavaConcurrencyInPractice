package netty.recorder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class RecorderServer {


	public static void main( String[] args ) {
		RecorderServerForm recorderServerForm = new RecorderServerForm();

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
							ch.pipeline().addLast( new RecorderServerHandler( recorderServerForm ) );
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
