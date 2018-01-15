package com.chenshun.test;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * User: chenshun131 <p />
 * Time: 18/1/13 15:37  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class TestPipe {

    @Test
    public void test1() throws IOException {
        //  获取管道
        Pipe pipe = Pipe.open();
        // 将缓冲区中的数据写入管道
        ByteBuffer buf = ByteBuffer.allocate(1024);

        Pipe.SinkChannel sinkChannel = pipe.sink();
        buf.put("通过单向管道发送数据".getBytes());
        buf.flip();
        sinkChannel.write(buf);

        // 读取缓冲区中的数据
        Pipe.SourceChannel sourceChannel = pipe.source();
        buf.flip();
        int len = sourceChannel.read(buf);
        System.out.println(new String(buf.array(), 0, len));

        sourceChannel.close();
        sinkChannel.close();
    }

}
