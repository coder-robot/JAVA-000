import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义ClassLoader处理Hello.xlass
 * <p>
 * 运行环境 jdk1.8.0_231
 *
 * @author zhenyong
 * @version 1.0
 * @date 2020/10/16 11:33 上午
 * @since JDK1.8
 */
public class HelloClassLoader extends ClassLoader {

    // @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        // 要加载的类文件
        String loadClassFilePath = HelloClassLoader.class
                .getProtectionDomain().getCodeSource().getLocation().getPath() + "/Hello.xlass";

        try {

            // 获取要加载的类对象
            Class<?> clz = new HelloClassLoader().findClass("Hello", loadClassFilePath);

            // 获取要调用的方法
            Method hello = clz.getDeclaredMethod("hello");
            hello.setAccessible(true);

            // 调用指定实例的方法
            hello.invoke(clz.newInstance());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    protected Class<?> findClass(String className, String loadClassFilePath)
            throws ClassNotFoundException {

        byte[] bytes = file2Bytes(loadClassFilePath);

        return defineClass(className, bytes, 0, bytes.length);
    }

//    @Override
//    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        throw new ClassNotFoundException(name);
//    }

    /**
     * 逐个字节读取,将读取的所有字节用255减去之后再写入ByteArrayOutputStream
     *
     * @param filePath class文件的路径
     * @return 返回处理后的字节数组
     */
    private byte[] file2Bytes(String filePath) {

        byte[] buffer = null;
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(new File(filePath));
            bos = new ByteArrayOutputStream(1024);
            byte[] b = new byte[1];
            byte[] restoreBytes = new byte[1];
            byte b255 = (byte) 255;
            int n;
            while ((n = fis.read(b)) != -1) {

                // 关键的一行代码
                restoreBytes[0] = (byte) (b255 - b[0]);

                bos.write(restoreBytes, 0, n);
            }
            buffer = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer;
    }
}
