package sizhe.chen.nio.filelock;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * @Author: sizhe.chen
 * @Date: Create in 10:00 上午 2022/8/28
 * @Description:
 * @Modified:
 * @Version:
 */

public class FileLockDemo {
    public static void main(String[] args) {
        String content = " File lock Test";
        ByteBuffer buffer = ByteBuffer.wrap(content.getBytes(StandardCharsets.UTF_8) );
        String filePath = "nio-demo-dir/01.txt";
        Path path = Path.of(filePath);
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE,StandardOpenOption.APPEND )) {
            // 加锁
            FileLock lock = channel.lock();
            // 查看是否是共享锁
            System.out.println("是否是共享锁：" + lock.isShared());

            // 文件写
            channel.write(buffer);
           readFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();

        }
      //  readFile(filePath);

    }

    private static void readFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            BufferedReader reader1 = new BufferedReader(reader);

            String str ;
            while ((str =reader1.readLine()) != null){
                System.out.println(str);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
