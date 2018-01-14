package com.chenshun.test;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * User: chenshun131 <p />
 * Time: 18/1/13 15:01  <p />
 * Version: V1.0  <p />
 * Description:
 * 一、使用 NIO 完成网络通信的三个核心：
 * <p>
 * 1. 通道（Channel）：负责连接
 * <pre>
 * 	   java.nio.channels.Channel 接口：
 * 			|--SelectableChannel
 * 				|--SocketChannel
 * 				|--ServerSocketChannel
 * 				|--DatagramChannel
 *
 * 				|--Pipe.SinkChannel
 * 				|--Pipe.SourceChannel
 * </pre>
 * 2. 缓冲区（Buffer）：负责数据的存取
 * <p>
 * 3. 选择器（Selector）：是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况 <p />
 */
public class TestNonBlockingNIO {

    // 客户端
    @Test
    public void client() throws IOException {
        // 获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        // 切换非阻塞模式
        sChannel.configureBlocking(false);
        // 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        // 发送数据给服务端
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String str = scan.next();
            buf.put((new Date().toString() + "\n" + str).getBytes());
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }
        // 关闭通道
        sChannel.close();
    }

    // 服务端
    @Test
    public void server() throws IOException {
        // 获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        // 切换非阻塞模式
        ssChannel.configureBlocking(false);
        // 绑定连接
        ssChannel.bind(new InetSocketAddress(9898));
        // 获取选择器
        Selector selector = Selector.open();
        // 将通道注册到选择器上, 并且指定“监听接收事件”
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 轮询式的获取选择器上已经“准备就绪”的事件
        while (selector.select() > 0) {
            // 获取当前选择器中所有注册的“选择键(已就绪的监听事件)”
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                // 获取准备“就绪”的是事件
                SelectionKey sk = it.next();
                // 判断具体是什么事件准备就绪
                if (sk.isAcceptable()) {
                    // 若 "接收就绪"，获取客户端连接
                    SocketChannel sChannel = ssChannel.accept();
                    // 切换非阻塞模式
                    sChannel.configureBlocking(false);
                    // 将该通道注册到选择器上
                    sChannel.register(selector, SelectionKey.OP_READ);
                } else if (sk.isReadable()) {
                    // 获取当前选择器上“读就绪”状态的通道
                    SocketChannel sChannel = (SocketChannel) sk.channel();
                    // 读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = sChannel.read(buf)) > 0) {
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                }
                // 取消选择键 SelectionKey
                it.remove();
            }
        }
    }

}