package me.zhenyong.concurrent;

import java.util.concurrent.*;

/**
 * 方法4
 *
 * @author zhenyong
 */
public class Homework0304 {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        CountDownLatch latch = new CountDownLatch(1);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        final Integer[] result = new Integer[1];
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    result[0] = Homework03.sum1();
                }finally {
                    latch.countDown();
                }
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 确保 拿到result 并输出
        System.out.println("异步计算结果为：" + result[0]);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
        executor.shutdown();
    }
}
