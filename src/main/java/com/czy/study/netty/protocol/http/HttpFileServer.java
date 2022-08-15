package com.czy.study.netty.protocol.http;

import com.czy.study.netty.handle.HttpFileServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpFileServer {
    private static final String DEFAULT_URL="/src/main/java/com/czy/study/netty/protocol/http/";
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        EventLoopGroup boss = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(boss,group)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("http-decoder",
                                    new HttpRequestDecoder());
                            ch.pipeline().addLast("http-aggregator",
                                    new HttpObjectAggregator(65536));
                            ch.pipeline().addLast("http-encoder",
                                    new HttpResponseEncoder());
                            ch.pipeline().addLast("http-chunked",
                                    new ChunkedWriteHandler());
                            ch.pipeline().addLast("fileServerHandler",
                                    new HttpFileServerHandler(DEFAULT_URL));
                        }
                    });
            ChannelFuture future = b.bind("127.0.0.1", 8080).sync();
            System.out.println("HTTP 文件目录服务启动，网址是：127.0.0.1:8080"+DEFAULT_URL);
            future.channel().closeFuture().sync();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
            boss.shutdownGracefully();
        }

    }
}
