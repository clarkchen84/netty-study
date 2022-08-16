package sizhe.chen.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 创建Selector{@link Selector}
 *
 * @Author: sizhe.chen
 * @Date: Create in 11:11 下午 2022/8/15
 * @Description:
 * @Modified:
 * @Version:
 */

public class SelectorServerDemo {
    public static void main(String[] args) throws IOException {
        // 1. 创建Selector
        Selector selector = Selector.open();
        // 2. 创建一个通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 3. 设置为非阻塞状态
        serverSocketChannel.configureBlocking(false);
        // 4. 绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9999 ));
        // 5. 将通道注册到选择器上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 6. 创建Buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 7. 进行轮询， 查询已经就绪的通道操作
        while (selector.select() > 0) {
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                // 7.1 获取就绪操作
                SelectionKey key = iterator.next();
                // 7.2 判读什么操作
                if (key.isAcceptable()) {
                    // 获取连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 切换非阻塞模式
                    socketChannel.configureBlocking(false);

                    // 注册
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isConnectable()) {


                } else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    buffer.clear();
                    int length = 0;
                    while((length = socketChannel.read(buffer)) > 0){
                        buffer.flip();
                        String say = new String(buffer.array(),0,length);
                        System.out.println(say);
                        buffer.clear();
                    }


                    //socketChannel.register(selector, SelectionKey.OP_WRITE);
                    //key.interestOps(SelectionKey.OP_WRITE);

                } else if (key.isWritable()) {
                    buffer.rewind();
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    socketChannel.write(buffer);
                    key.interestOps(SelectionKey.OP_READ);
//                    buffer.clear();
//                    buffer
//                    buffer.flip();
//                    String say = new String(buffer.array());
//                    System.out.println(say);
//                    socketChannel.register(selector, SelectionKey.OP_WRITE);
                } else {

                }
                // 7.3 remove
                iterator.remove();
            }
        }
    }
}
