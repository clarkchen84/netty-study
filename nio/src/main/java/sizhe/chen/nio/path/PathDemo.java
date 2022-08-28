package sizhe.chen.nio.path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author: sizhe.chen
 * @Date: Create in 10:54 上午 2022/8/28
 * @Description:
 * @Modified:
 * @Version:
 */

public class PathDemo {
    public static void main(String[] args) {
        // 常见path实例
//        Path path = Paths.get("nio-demo-dir/new_directory");
//        try {
//            Files.createDirectory(path);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        // Files.copy
        Path sourcePath = Paths.get("nio-demo-dir/01.txt");
        Path targetPath  = Paths.get("nio-demo-dir/00.txt");
        try {
            Files.copy(sourcePath,targetPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
