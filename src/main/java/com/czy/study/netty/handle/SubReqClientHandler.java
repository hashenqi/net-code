package com.czy.study.netty.handle;

import com.czy.study.netty.serialize.jdk.SubScribeReq;
//import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelHandlerInvoker;
//import io.netty.util.concurrent.EventExecutorGroup;

public class SubReqClientHandler extends ChannelHandlerAdapter {
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        for (int i = 0; i < 10; i++) {
//            ctx.write(subReq(i));
//        }
//        ctx.flush();
//    }

    private Object subReq(int i) {
        SubScribeReq req = new SubScribeReq();
        req.setSubReqID(i);
        req.setPhoneNumber("18300000000");
        req.setUserName("Lilinfeng");
        req.setAddress("广州");
        req.setProductName("Netty 权威指南");
        return req;
    }

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("Receive server response :" + msg.toString());
//    }
//
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        ctx.flush();
//    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
