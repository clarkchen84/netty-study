package sizhe.chen.nio.charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @Author: sizhe.chen
 * @Date: Create in 9:08 下午 2022/8/28
 * @Description:
 * @Modified:
 * @Version:
 */

public class CharSetEncoderAndDecoderDemo {
    public static void main(String[] args) throws CharacterCodingException {
        // 1. 获得Charset对象
        Charset charset = Charset.forName("UTF-8");
        // 2. 获取编码器对象
        CharsetEncoder encoder = charset.newEncoder();

        // 3. 创建缓冲区对象
        CharBuffer buffer = CharBuffer.allocate(1024);
        buffer.put("编码解码测试");
        buffer.flip();
        // 4. 编码
        ByteBuffer byteBuffer = encoder.encode(buffer);
        while (byteBuffer.hasRemaining()) {
            System.out.println(byteBuffer.get());

        }
        byteBuffer.flip();
        // 5. 获得解码器对象
        CharsetDecoder decoder = charset.newDecoder();

        // 6. 解码
        CharBuffer charBuffer1 = decoder.decode(byteBuffer);
        System.out.println(charBuffer1.toString());


    }
}
