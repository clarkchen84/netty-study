package sizhe.chen.nio.buffer;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 内存映射文件IO{@link FileChannel#map(FileChannel.MapMode, long, long)}
 */
public class BufferMermorryDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("nio-demo-dir/05.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();
        MappedByteBuffer buffer =  channel.map(FileChannel.MapMode.READ_WRITE,0 , 1024);

        buffer.put(0, (byte) 97);
        buffer.put(1023, (byte) 126);
        System.out.println((char)126);
        channel.close();
    }
}
