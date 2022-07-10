package sizhe.chen.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Author: sizhe.chen
 * @Date: Create in 4:34 下午 2022/7/10
 * @Description:
 * @Modified:
 * @Version:
 */

public class SocketChannelDemo {

    public static void main(String[] args) throws Exception {
        try (SocketChannel sc = SocketChannel.open(new InetSocketAddress("www.baidu.com", 80))) {
            sc.configureBlocking(false);
            ByteBuffer bf = ByteBuffer.allocate(128);
            sc.read(bf);

        }
        System.out.println("over");

    }
}
