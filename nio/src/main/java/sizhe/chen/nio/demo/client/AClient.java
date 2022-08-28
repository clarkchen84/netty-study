package sizhe.chen.nio.demo.client;

import java.io.IOException;

/**
 * @Author: sizhe.chen
 * @Date: Create in 11:48 下午 2022/8/28
 * @Description:
 * @Modified:
 * @Version:
 */

public class AClient {
    public static void main(String[] args) throws IOException {
        new ChatClient().startClient("张三");
    }
}
