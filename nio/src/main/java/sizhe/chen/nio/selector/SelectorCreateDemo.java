package sizhe.chen.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * 创建Selector{@link Selector}
 *
 * @Author: sizhe.chen
 * @Date: Create in 11:11 下午 2022/8/15
 * @Description:
 * @Modified:
 * @Version:
 */

public class SelectorCreateDemo {
    public static void main(String[] args) throws IOException {
        // 创建Selector
        Selector selector = Selector.open();
        // 创建一个通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置为非阻塞状态
        serverSocketChannel.configureBlocking(false);
        // 绑定连接
        serverSocketChannel.bind(new InetSocketAddress(8888 ));
        //将通道注册到选择器上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.)
    }
}
