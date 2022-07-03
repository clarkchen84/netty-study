package sizhe.chen.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: sizhe.chen
 * @Date: Create in 5:42 下午 2022/7/3
 * @Description:
 * @Modified:
 * @Version:
 */

public class FileChannelDemo {

    public static void main(String[] args) throws IOException {
        // 创建FileChannel
        RandomAccessFile file = new RandomAccessFile("nio-demo-dir/01.txt","rw");
        FileChannel inChannel= file.getChannel();
        // 创建Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        // 读取buffer
        int bytesRead = inChannel.read(byteBuffer);
        while(bytesRead != -1){
            System.out.println("读取了：" + bytesRead);
            byteBuffer.flip();
            while(byteBuffer.hasRemaining()){
                System.out.println((char) byteBuffer.get());

            }
            byteBuffer.clear();
            bytesRead = inChannel.read(byteBuffer);
        }

        file.close();
        System.out.println("结束了");





    }
}
