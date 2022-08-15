package com.czy.study.netty.handle;

import com.czy.study.netty.serialize.jdk.SubScribeResp;
import com.czy.study.netty.serialize.protobuf.SubscribeReqProto;
import com.czy.study.netty.serialize.protobuf.SubscribeRespProto;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqServerHandler extends ChannelHandlerAdapter {

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;
//        if ("Lilinfeng".equalsIgnoreCase(req.getUserName())) {
//            System.out.println("Service accept client subscribe req:"+req.toString());
//            ctx.writeAndFlush(resp(req.getSubReqID()));
//        }
//    }

    private Object resp(int subReqID) {
        SubScribeResp resp = new SubScribeResp();
        resp.setSubRespID(subReqID);
        resp.setRespCode(0);
        resp.setDesc("Netty book order succeed, 3 days later, sent to the designated address");
        return resp;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
