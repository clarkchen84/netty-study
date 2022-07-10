package sizhe.chen.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;

/**
 * @Author: sizhe.chen
 * @Date: Create in 8:36 下午 2022/7/10
 * @Description:
 * @Modified:
 * @Version:
 */

public class DatagramChannelClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 打开DatagramChannel
        DatagramChannel client = DatagramChannel.open();
        InetSocketAddress address = new InetSocketAddress("localhost",10086);
        while (true) {
            // 发送数据
            ByteBuffer sendBuffer = ByteBuffer.wrap("发包".getBytes(StandardCharsets.UTF_8));
            client.send(sendBuffer, address);

            System.out.println("客户端发包");
            Thread.sleep(2000);
        }
    }
}
