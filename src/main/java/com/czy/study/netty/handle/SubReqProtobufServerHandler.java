package com.czy.study.netty.handle;

import com.czy.study.netty.serialize.jdk.SubScribeReq;
import com.czy.study.netty.serialize.jdk.SubScribeResp;
import com.czy.study.netty.serialize.protobuf.SubscribeReqProto;
import com.czy.study.netty.serialize.protobuf.SubscribeRespProto;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelHandlerInvoker;
import io.netty.util.concurrent.EventExecutorGroup;

public class SubReqProtobufServerHandler extends ChannelHandlerAdapter {

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;
//        if ("Lilinfeng".equalsIgnoreCase(req.getUserName())) {
//            System.out.println("Service accept client subscribe req:"+req.toString());
//            ctx.writeAndFlush(resp(req.getSubReqID()));
//        }
//    }

    private Object resp(int subReqID) {
        SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();
        builder.setSubReqID(subReqID)
                .setRespCode(0)
                .setDesc("Netty book order succeed, 3 days later, sent to the designated address");
        return builder.build();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
