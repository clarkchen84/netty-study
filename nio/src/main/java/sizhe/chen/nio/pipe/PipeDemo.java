package sizhe.chen.nio.pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;

/**
 * @Author: sizhe.chen
 * @Date: Create in 10:54 下午 2022/8/22
 * @Description:
 * @Modified:
 * @Version:
 */

public class PipeDemo {
    public static void main(String[] args) throws IOException {
        //1. 获取管道
        Pipe pipe = Pipe.open();
        //2. 获取Sink通道
        Pipe.SinkChannel sinkChannel = pipe.sink();
        //3.  创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("晴川历历汉阳树，芳草萋萋鹦鹉洲".getBytes(StandardCharsets.UTF_8));
        buffer.flip();
        //4. 写入数据
        sinkChannel.write(buffer);
        // 获取source通道
        Pipe.SourceChannel sourceChannel = pipe.source();

        // 创建缓冲区读取数据
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);
        int length = 0;

        // 不能写循环， 阻塞线程
//        while ((length = sourceChannel.read(buffer2)) > 0) {
//            buffer2.flip();
//            String str = new String(buffer2.array());
//            System.out.println(str);
//            buffer2.clear();
//        }
        length = sourceChannel.read(buffer2);
//        buffer2.flip();
        String str = new String(buffer2.array());
        System.out.println(str);
        buffer2.clear();

        // 关闭通道
        sourceChannel.close();
        sinkChannel.close();
    }
}
