package sizhe.chen.nio.demo.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @Author: sizhe.chen
 * @Date: Create in 9:41 下午 2022/8/28
 * @Description:
 * @Modified:
 * @Version:
 */

public class ChatClient {

    public void startClient(String name ) throws IOException {
        // 连接服务器端
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8888));

        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        new Thread(new ClientThread(selector)).start();
        // 向服务器端发送消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.nextLine();
            if (message.length() > 0) {
                socketChannel.write(Charset.forName("UTF-8").encode(name + ":" +message));
            }
        }
        // 接收服务器端的相应的数据
    }

    public static void main(String[] args) throws IOException {
        new ChatClient().startClient(args[0]);
    }
}

class ClientThread implements Runnable {

    private Selector selector;

    public ClientThread(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            while (true) {

                int readChannels = selector.select();
                if(readChannels == 0){
                    continue;
                }
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if(key.isReadable()){
                        readOperator(selector, key);
                    }


                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readOperator(Selector selector, SelectionKey key) throws IOException {
        // 1.  从SelectionKey中获取已经就绪的通道
        SocketChannel socketChannel = (SocketChannel) key.channel();
        // 2. 创建Buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 3. 循环的方式客户端发来的消息
        int readLength = socketChannel.read(buffer);
        String message = "";
        while (readLength > 0 ){
            buffer.flip();
            message += Charset.forName("UTF-8").decode(buffer).toString();
            System.out.println(message);
            buffer.clear();
            readLength = socketChannel.read(buffer);
        }
        // 4. 将channel再次注册到选择器上，监听可读的状态
        socketChannel.register(selector, SelectionKey.OP_READ);

    }
}
