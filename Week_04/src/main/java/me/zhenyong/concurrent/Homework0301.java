package me.zhenyong.concurrent;

/**
 * 方法1
 *
 * @author zhenyong
 */
public class Homework0301 {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        final int[] result = new int[1];
        new Thread(new Runnable() {
            public void run() {
                result[0] = Homework03.sum1();
            }
        }).start();

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result[0]);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }
}
