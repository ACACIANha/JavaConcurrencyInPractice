package netty.recorder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class RecorderClientHandler extends SimpleChannelInboundHandler< ByteBuf > {

	private final RecorderClientForm form;

	public RecorderClientHandler( RecorderClientForm form ) {
		this.form = form;
	}

	@Override
	public void channelActive( ChannelHandlerContext ctx ) {
		log.info( "client channelActive" );
		ctx.writeAndFlush( Unpooled.copiedBuffer( form.getText(), CharsetUtil.UTF_8 ) );
	}


	@Override
	protected void channelRead0( ChannelHandlerContext ctx, ByteBuf msg ) throws Exception {
		log.info( "client channelRead0" );
		ctx.fireChannelRead( msg );
	}

	@Override
	public void channelReadComplete( ChannelHandlerContext ctx ) throws Exception {
		log.info( "client channelReadComplete" );
		form.exit();
		ctx.disconnect();
	}

	@Override
	public void exceptionCaught( ChannelHandlerContext ctx, Throwable cause ) {
		cause.printStackTrace();
		ctx.close();
	}
}
