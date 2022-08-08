package sizhe.chen.nio.buffer;

import java.nio.ByteBuffer;

/**
 * 只读缓冲区的例子{@link ByteBuffer#asReadOnlyBuffer()}
 */
public class BufferReadOnlyDemo {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        for(int i = 0; i < buffer.capacity(); i++){
            buffer.put((byte) i);
        }
        buffer.flip();
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        for(int i = 0; i < readOnlyBuffer.capacity(); i++){
            System.out.println(readOnlyBuffer.get(i));
        }

        System.out.println(readOnlyBuffer.position());
        System.out.println(readOnlyBuffer.limit());
    }
}
