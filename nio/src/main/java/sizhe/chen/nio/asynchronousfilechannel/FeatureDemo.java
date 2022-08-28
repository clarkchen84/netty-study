package sizhe.chen.nio.asynchronousfilechannel;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * @Author: sizhe.chen
 * @Date: Create in 2:56 下午 2022/8/28
 * @Description:
 * @Modified:
 * @Version:
 */

public class FeatureDemo {
    public static void main(String[] args) {
        Path path = Paths.get("nio-demo-dir/01.txt");
        // 创建一个ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 创建一个AsynchronousFileChannel
        try (AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ)) {
            // 调用Read方法，取得Feature对象

            Future<Integer> future;
            int limit = 0;
            while ((future =  fileChannel.read(buffer, limit)) != null) {
                // 判断是否完成 isDone
                while (!future.isDone()) {
                    ;
                }
                buffer.flip();
//            while (buffer.hasRemaining()){
//                System.out.println(buffer.get());
//            }
                if(buffer.limit() == 0){
                    break;
                }
                limit += buffer.limit();
                byte[] bytes = new byte[buffer.limit()];
                buffer.get(bytes);


                System.out.println(new String(bytes));
                buffer.clear();
            }

        } catch (IOException e) {

        }

    }
}
