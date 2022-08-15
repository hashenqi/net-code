package com.czy.study.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.TimeUnit;

public class DiscardClientHandler extends SimpleChannelInboundHandler<Object> {

    private ByteBuf content;
    private ChannelHandlerContext ctx;

    /**
     * 读取信息，因为是Discard（丢弃的），所以不做处理
     * @param ctx
     * @param o
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
        // do noting
    }

    /**
     * 活跃的时候
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
        content = ctx.alloc().directBuffer(DiscardClient.SIZE).writeZero(DiscardClient.SIZE);
        System.out.println("执行了！！！");
        generateTraffic();
    }

    /**
     * 构造Traffic（流量）
     */
    private void generateTraffic() {
        // addListener 一直在监听通信事件
        ctx.writeAndFlush(content.retainedDuplicate()).addListener(trafficGenerator);
    }

    private final ChannelFutureListener trafficGenerator = (future)->{
        //如果一直在通信成功，则会一直调用询问是否正常
        if (future.isSuccess()) {
            TimeUnit.SECONDS.sleep(2);
            generateTraffic();
        }else {
            System.out.println("running....");
            future.cause().printStackTrace();
            future.channel().close();
        }
    };


    /**
     * 不活跃的时候
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        content.release();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
