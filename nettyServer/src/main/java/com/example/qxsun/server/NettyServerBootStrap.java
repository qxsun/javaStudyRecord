package com.example.qxsun.server;


import com.example.qxsun.handler.NettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NettyServerBootStrap {

    @Autowired
    private NettyServerHandler nettyServerHandler;

    public void start() throws InterruptedException {
        /**
         * 配置服务端的NIO线程组
         * NioEventLoopGroup 是用来处理I/O操作的Reactor线程组
         * bossGroup：用来接收进来的连接，workerGroup：用来处理已经被接收的连接,进行socketChannel的网络读写，
         * bossGroup接收到连接后就会把连接信息注册到workerGroup
         * workerGroup的EventLoopGroup默认的线程数是CPU核数的二倍
         */
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        /**
         * ServerBootstrap 是一个启动NIO服务的辅助启动类
         */
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            /**
             * 设置group，将bossGroup， workerGroup线程组传递到ServerBootstrap
             */
            bootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class) //ServerSocketChannel是以NIO的selector为基础进行实现的，用来接收新的连接，这里告诉Channel通过NioServerSocketChannel获取新的连接
                    .option(ChannelOption.SO_BACKLOG, 128)
                    /**
                     * option是设置 bossGroup，childOption是设置workerGroup
                     * netty 默认数据包传输大小为1024字节, 设置它可以自动调整下一次缓冲区建立时分配的空间大小，避免内存的浪费    最小  初始化  最大 (根据生产环境实际情况来定)
                     * 使用对象池，重用缓冲区
                     */
                    // 使消息立即发出去，不用等待到一定的数据量才发出去
                    .option(ChannelOption.TCP_NODELAY, true)
                    // 保持长连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //设置 I/O处理类,主要用于网络I/O事件，记录日志，编码、解码消息
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline p = socketChannel.pipeline();
                            p.addLast(new StringDecoder(CharsetUtil.UTF_8));
                            p.addLast(new StringEncoder(CharsetUtil.UTF_8));
                            p.addLast(nettyServerHandler);
                        }
                    });
            // 绑定端口，同步等待成功
            ChannelFuture f = bootstrap.bind(5678).sync();
            if (f.isSuccess()) {
                log.info("Netty Start successful");
            } else {
                log.error("Netty Start failed");
            }
            // 等待服务监听端口关闭
            f.channel().closeFuture().sync();
        } finally {
            // 退出，释放线程资源
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }

}
