package sizhe.chen.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Set;

/**
 * @Author: sizhe.chen
 * @Date: Create in 12:23 下午 2022/7/10
 * @Description:
 * @Modified:
 * @Version:
 */

public class ServerSocketChannelDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        //端口号设置
        int port = 8888;
        //buffer
        ByteBuffer buffer = ByteBuffer.wrap("Hello World".getBytes(StandardCharsets.UTF_8));
        //ServerSocketChannel
        ServerSocketChannel  serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port ));
        //设置非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 无限循环，监听是不是有新的连接传入
        while(true){
            SocketChannel socketChannel = serverSocketChannel.accept();

            if(socketChannel == null){
                System.out.println("have no socket connect to ser ver");
                Thread.sleep(2000);
            }else {
                System.out.println("Incommint from :" + socketChannel.socket().getRemoteSocketAddress() );
                buffer.rewind(); // 指针指向了0
                socketChannel.write( buffer);
                socketChannel.close();
            }
        }
    }
}
