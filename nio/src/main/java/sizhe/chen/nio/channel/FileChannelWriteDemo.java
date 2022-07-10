package sizhe.chen.nio.channel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @Author: sizhe.chen
 * @Date: Create in 8:47 下午 2022/7/4
 * @Description:
 * @Modified:
 * @Version:
 */

public class FileChannelWriteDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile("nio-demo-dir/01.txt","rw");
        FileChannel fileChannel = accessFile.getChannel();
        String data = "New String to write to file...." + System.currentTimeMillis();
        fileChannel.position(fileChannel.size());
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()){
            fileChannel.write(byteBuffer);
        }

        fileChannel.close();
    }
}
