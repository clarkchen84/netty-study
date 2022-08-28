package sizhe.chen.nio.demo.server;

import javax.swing.event.ChangeEvent;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: sizhe.chen
 * @Date: Create in 9:41 下午 2022/8/28
 * @Description:
 * @Modified:
 * @Version:
 */

public class ChatServer {

    /**
     * 服务器启动
     */
    public void startServer() throws IOException {
        // 1. 创建一个选择器
        Selector selector = Selector.open();
        // 2. 创建一个ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 3. 为通道绑定监听的端口
        serverSocketChannel.bind(new InetSocketAddress(8888));
        // 4. 通道设置成非阻塞的模式
        serverSocketChannel.configureBlocking(false);
        // 5. 将通道注册到Selector 中
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动成功了");
        // 4. 循环有新的连接接入
        while (true){
            // 4.1 获取channel的数量
            int channelCount = selector.select();
            if(channelCount == 0){
                continue;
            }
            // 获取可用的channel
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                // 移除集合
                iterator.remove();
                if (selectionKey.isAcceptable()){
                    acceptOperator(serverSocketChannel, selector);
                }
                if(selectionKey.isReadable()){
                    readOperation(selectionKey, selector);
                }
                if (selectionKey.isWritable()){

                }


            }

        }
        // 5. 根据就绪的状态调用对应的方法
            // 5.1 如果是 accept状态
            // 5.2 如果是一个可读的状态

    }

    /**
     * 处理读取数据
     * @param selectionKey
     * @param selector
     */
    private void readOperation(SelectionKey selectionKey, Selector selector) throws IOException {
        // 1.  从SelectionKey中获取已经就绪的通道
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        // 2. 创建Buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 3. 循环的方式客户端发来的消息
         int readLength = socketChannel.read(buffer);
         String message = "";
         while (readLength > 0 ){
             buffer.flip();
             message += Charset.forName("UTF-8").decode(buffer).toString();
             buffer.clear();
             readLength = socketChannel.read(buffer);
         }
        // 4. 将channel再次注册到选择器上，监听可读的状态
        socketChannel.register(selector, SelectionKey.OP_READ);
        // 5. 向客户端发送消息，广播到其他客户端
        if(message.length() > 0){
            castOtherClient(message, selector,socketChannel );
        }

    }

    /**
     * 广播message 给其他客户端
     * @param message
     * @param selector
     * @param socketChannel
     */
    private void castOtherClient(String message, Selector selector, SocketChannel socketChannel) throws IOException {
         // 1. 获得所有已经接入的客户端
        Set<SelectionKey> keys = selector.selectedKeys();
        // 循环锁有的channel进行广播
        for (SelectionKey key: keys){
            Channel channel = key.channel();

            if(channel instanceof  SocketChannel && channel != socketChannel){
                SocketChannel writeChannel = (SocketChannel) channel;
                writeChannel.write(Charset.forName("UTF-8").encode(message));
            }
        }

    }

    /**
     * 处理接入的状态操作
     * @param serverSocketChannel
     * @param selector
     */
    private void acceptOperator(ServerSocketChannel serverSocketChannel, Selector selector) throws IOException {
        // 1. 接入的状态， 创建socketChannel
        SocketChannel socketChannel = serverSocketChannel.accept();
        // 2. 把SocketChannel设置为非阻塞模式
        socketChannel.configureBlocking(false);
        // 3. 把Channel注册到选择器上面， 监听可读的状态
        socketChannel.register(selector, SelectionKey.OP_READ);
        // 4. 给客户端回复信息
        socketChannel.write(Charset.forName("UTF-8").encode("欢迎加入聊天室" ));
    }


    public static void main(String[] args) throws IOException {
            new ChatServer().startServer();

    }
}
