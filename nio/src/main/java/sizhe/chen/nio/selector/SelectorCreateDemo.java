package sizhe.chen.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
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

        //查询已经就绪的通道操作
        Set<SelectionKey> keys =  selector.selectedKeys();
        Iterator<SelectionKey> iterator = keys.iterator();
        while (iterator.hasNext()){
            SelectionKey key = iterator.next();

            // 判断key处于就绪状态
            if(key.isAcceptable()){

            }else if (key.isConnectable()){

            }else if(key.isReadable()){

            }else if(key.isWritable()){

            }else {

            }
            iterator.remove();
        }
    }
}
