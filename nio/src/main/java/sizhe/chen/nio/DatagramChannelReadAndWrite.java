package sizhe.chen.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @Author: sizhe.chen
 * @Date: Create in 8:59 下午 2022/7/10
 * @Description:
 * @Modified:
 * @Version:
 */

public class DatagramChannelReadAndWrite {
    public static void main(String[] args) throws IOException {
        // 打开DatagramChannel
        DatagramChannel datagramChannel = DatagramChannel.open();
        // 绑定
        datagramChannel.bind(new InetSocketAddress(9999));
        // 连接
        datagramChannel.connect(new InetSocketAddress("localhost",9999));

        datagramChannel.write(ByteBuffer.wrap("发送数据".getBytes(StandardCharsets.UTF_8)));

        //Read buffer
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        while (true) {
            readBuffer.clear();
            datagramChannel.read(readBuffer);
            readBuffer.flip();
            System.out.println(Charset.forName("UTF-8").decode(readBuffer));
        }

    }
}
