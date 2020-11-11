package me.zhenyong.concurrent;

import java.util.concurrent.*;

/**
 * 方法5
 *
 * @author zhenyong
 */
public class Homework0306 {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        final int[] result = new int[1];
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            CompletableFuture.runAsync(new Runnable() {
                @Override
                public void run() {
                    result[0] = Homework03.sum1();
                    // 确保 拿到result 并输出
                    System.out.println("异步计算结果为：" + result[0]);
                }
            }, executor).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
        executor.shutdown();
        new ThreadPoolExecutor()
    }
}
