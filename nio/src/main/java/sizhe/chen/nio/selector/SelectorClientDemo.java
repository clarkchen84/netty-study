package sizhe.chen.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class SelectorClientDemo {
    public static void main(String[] args) throws IOException {
        // 1. 获得通道，绑定主机
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 9999));
        // 2. 切换到非阻塞模式
        channel.configureBlocking(false);
        // 3. 创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Scanner  scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String scan = scanner.nextLine();
            // 4. 向buffer 写入数据
            buffer.put(scan.getBytes(StandardCharsets.UTF_8));
            // 5. 模式切换
            buffer.flip();
            // 6. 写入通道
            channel.write(buffer);
            // 7. 关闭通道
            buffer.clear();
        }
        channel.close();

    }
}
