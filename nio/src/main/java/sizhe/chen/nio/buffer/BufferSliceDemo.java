package sizhe.chen.nio.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * 缓冲区切片{@link ByteBuffer#slice()}
 *
 * @Author: sizhe.chen
 * @Date: Create in 8:28 上午 2022/8/7
 * @Description:
 * @Modified:
 * @Version:
 */

public class BufferSliceDemo {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        for(int i = 0 ; i < byteBuffer.capacity(); i++ ){
            byteBuffer.put((byte) i);
        }

        // 创建子缓冲区
        byteBuffer.position(3);

        byteBuffer.limit(7);

        ByteBuffer slice = byteBuffer.slice();
        System.out.println("postion= " + byteBuffer.position());
        System.out.println("limit= " + byteBuffer.limit());

        // 改变自缓冲区的内容
        for(int i = 0 ; i < slice.capacity(); i++ ){
            slice.put(i   ,(byte) i);
        }
        System.out.println("postion= " + byteBuffer.position());
        System.out.println("limit= " + byteBuffer.limit());
       // byteBuffer.position(0);

//        byteBuffer.limit(10);
//        for(int i = 0 ; i < byteBuffer.capacity(); i++ ){
//            System.out.println(byteBuffer.get(i));
//        }
        byteBuffer.flip();
//        byteBuffer.limit(10);
        System.out.println("postion= " + byteBuffer.position());
        System.out.println("limit= " + byteBuffer.limit());
        while (byteBuffer.hasRemaining()){
            System.out.println(byteBuffer.get());
        }


    }
}
