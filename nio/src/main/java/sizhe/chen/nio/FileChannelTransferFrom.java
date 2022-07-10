package sizhe.chen.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.RandomAccess;

/**
 * @Author: sizhe.chen
 * @Date: Create in 11:16 下午 2022/7/4
 * @Description:
 * @Modified:
 * @Version:
 */

public class FileChannelTransferFrom {
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("nio-demo-dir/01.txt", "rw");
        FileChannel from = file.getChannel();
        file = new RandomAccessFile("nio-demo-dir/02.txt", "rw");
        FileChannel to = file.getChannel();

        long position = 0;
        long count = from.size();
        System.out.println("size:" + count + " position:" + from.position());
        to.transferFrom(from,position,count);
        System.out.println(from.position());
        to.close();
        from.close();
        System.out.println("over");

    }
}
