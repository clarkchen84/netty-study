package sizhe.chen.nio.asynchronousfilechannel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Author: sizhe.chen
 * @Date: Create in 3:21 下午 2022/8/28
 * @Description:
 * @Modified:
 * @Version:
 */

public class CompletionHandlerDemo {
    public static void main(String[] args) throws InterruptedException, IOException {
        Path path = Paths.get("nio-demo-dir/01.txt");
        // 创建一个ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 创建一个AsynchronousFileChannel
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
        fileChannel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result :" + result);
                // 判断是否完成 isDone
                buffer.flip();
                byte[] bytes = new byte[buffer.limit()];
                buffer.get(bytes);
                System.out.println(new String(bytes));
                buffer.clear();
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
                System.out.println("failed");
            }
        });


        Thread.sleep(2000);

    }
}
