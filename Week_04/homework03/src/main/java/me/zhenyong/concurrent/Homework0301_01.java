package me.zhenyong.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 方法1_1
 *
 * @author zhenyong
 */
public class Homework0301_01 {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        final int[] result = new int[1];
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            public void run() {
                result[0] = Homework03.sum1();
            }
        });

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
