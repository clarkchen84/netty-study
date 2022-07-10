package sizhe.chen.nio.channel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: sizhe.chen
 * @Date: Create in 9:26 下午 2022/7/10
 * @Description:
 * @Modified:
 * @Version:
 */

public class ScatterDemo {

    public static void main(String[] args) throws IOException {
        ByteBuffer head = ByteBuffer.allocate(1024);

        ByteBuffer body = ByteBuffer.allocate(1024*1024);
        ByteBuffer[] byteBuffers = new ByteBuffer[]{head, body};
        RandomAccessFile file = new RandomAccessFile("nio-demo-dir/01.txt","rw");
        FileChannel inChannel= file.getChannel();
        inChannel.read(byteBuffers);
        head.flip();

        //
        file = new RandomAccessFile("nio-demo-dir/02.txt","rw");
        FileChannel outChannel= file.getChannel();
        head.flip();
        body.flip();
        while (head.hasRemaining()){
            outChannel.write(head);
        }
        while (body.hasRemaining()){
            outChannel.write(body);
        }
    }
}
