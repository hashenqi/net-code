package com.czy.study.netty.handle;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

public class TimeClientHandler extends ChannelHandlerAdapter {
    private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());

//    private final ByteBuf firstMessage;

    private int counter;

    private byte[] req;

//    public TimeClientHandler() {
//        req = "QUERY TIME ORDER".getBytes();
//        firstMessage = Unpooled.buffer(req.length);
//        firstMessage.writeBytes(req);
//    }
    public TimeClientHandler() {
        req = ("QUERY TIME ORDER"+System.getProperty("line.separator")).getBytes();
    }

    /**
     * 发送消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = null;
        for (int i = 0; i < 100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    /**
     * 发送消息
     * @param ctx
     * @throws Exception
     */
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ctx.writeAndFlush(firstMessage);
//
//    }

    /**
     * 读消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//        String body = new String(req,"UTF-8");
//        System.out.println("Now is :" + body);
//
//    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("Now is :" + body + "； counter is:"+ ++counter);
    }

    /**
     * 读消息/拆包
     * @param ctx
     * @param msg
     * @throws Exception
     */
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//        String body = new String(req,"UTF-8");
//        System.out.println("Now is :" + body + "； counter is:"+ ++counter);
//
//    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warning("Unexpected exception from downstream :" + cause.getMessage());
        ctx.close();
    }
}
