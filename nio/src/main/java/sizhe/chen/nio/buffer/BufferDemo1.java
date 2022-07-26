package sizhe.chen.nio.buffer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: sizhe.chen
 * @Date: Create in 11:11 下午 2022/7/10
 * @Description:
 * @Modified:
 * @Version:
 */

public class BufferDemo1 {


    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("nio-demo-dir/01.txt", "rw" );
        FileChannel channel = randomAccessFile.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        int size = channel.read(byteBuffer);
        System.out.println(size);
        while (size != -1){
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()){
                System.out.print(String.valueOf((char)byteBuffer.get()));
            }
            byteBuffer.clear();
            size = channel.read(byteBuffer);
            System.out.println(size);

        }
        channel.close();

    }
}
