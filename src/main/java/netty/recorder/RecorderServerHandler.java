package netty.recorder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketAddress;

@Slf4j
public class RecorderServerHandler extends ChannelInboundHandlerAdapter {

	private final RecorderServerForm serverForm;

	public RecorderServerHandler( RecorderServerForm serverForm ) {
		this.serverForm = serverForm;
	}


	@Override
	public void channelRead( ChannelHandlerContext ctx, Object msg ) throws Exception {
//		log.info( "server channelRead" );
		ByteBuf in = ( ByteBuf ) msg;
		byte[] b = new byte[ in.readableBytes() ];
		in.getBytes( 0, b, 0, in.readableBytes() );
		String s = new String( b );

		SocketAddress socketAddress = ctx.channel().remoteAddress();
		serverForm.addText( socketAddress.toString() + " : " + s );
		ctx.fireChannelRead( msg );
	}

	@Override
	public void channelReadComplete( ChannelHandlerContext ctx ) {
//		log.info( "server channelReadComplete" );
		serverForm.addText( ctx.channel().remoteAddress().toString() + " : " + "channelReadComplete" );
		ctx.writeAndFlush( Unpooled.EMPTY_BUFFER ).addListener( ChannelFutureListener.CLOSE );
	}

	@Override
	public void channelRegistered( ChannelHandlerContext ctx ) throws Exception {
		super.channelRegistered( ctx );
		serverForm.addText( ctx.channel().remoteAddress().toString() + " : " + "registered" );
	}

	@Override
	public void channelUnregistered( ChannelHandlerContext ctx ) throws Exception {
		super.channelUnregistered( ctx );
		serverForm.addText( ctx.channel().remoteAddress().toString() + " : " + "unregistered" );
	}

	@Override
	public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) {
		cause.printStackTrace();
		ctx.close();
	}
}
