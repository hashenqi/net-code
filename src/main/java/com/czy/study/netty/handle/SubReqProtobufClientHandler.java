package com.czy.study.netty.handle;

import com.czy.study.netty.serialize.jdk.SubScribeReq;
import com.czy.study.netty.serialize.protobuf.SubscribeReqProto;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqProtobufClientHandler extends ChannelHandlerAdapter {
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        for (int i = 0; i < 10; i++) {
//            ctx.write(subReq(i));
//        }
//        ctx.flush();
//    }

    private Object subReq(int i) {
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(i).setUserName("Lilinfeng").setAddress("广州")
        .setProductName("Netty 权威指南");
        return builder.build();
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
