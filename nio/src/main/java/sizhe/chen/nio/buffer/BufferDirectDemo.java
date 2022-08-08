package sizhe.chen.nio.buffer;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.List;

/**
 * 直接缓冲区{@link java.nio.ByteBuffer#allocateDirect(int)}
 */
public class BufferDirectDemo {
    public static void main(String[] args) throws IOException {
        File file = new File("nio-demo-dir/01.txt");
        File outFile = new File("nio-demo-dir/04.txt");
        FileOutputStream outputStream = new FileOutputStream(outFile);

        FileInputStream stream = new FileInputStream(file);
        FileChannel channel = stream.getChannel();
        FileChannel outChannel = outputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        while (channel.read(buffer) != -1){
            buffer.flip();
            while (buffer.hasRemaining()) {
                outChannel.write(buffer);
            }
            buffer.clear();
        }

        channel.close();
        outChannel.close();


    }
}
