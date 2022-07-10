package sizhe.chen.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @Author: sizhe.chen
 * @Date: Create in 8:31 下午 2022/7/10
 * @Description:
 * @Modified:
 * @Version:
 */

public class DatagramChannelServerDemo {
    public static void main(String[] args) throws IOException {
        // 打开DatagramChannel
        DatagramChannel server = DatagramChannel.open();
        server.bind(new InetSocketAddress(10086));
        // 通过Receive 接收UDP的包
        ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);
        while (true) {
            receiveBuffer.clear();
            SocketAddress address = server.receive(receiveBuffer);
            receiveBuffer.flip();
            if(address != null) {
                System.out.println(address.toString() );
                while (receiveBuffer.hasRemaining()) {
                    System.out.println(receiveBuffer.get());
                }
                receiveBuffer.flip();
                System.out.println(Charset.forName("UTF-8").decode(receiveBuffer));
            }
        }



    }
}
