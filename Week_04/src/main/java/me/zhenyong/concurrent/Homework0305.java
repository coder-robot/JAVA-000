package me.zhenyong.concurrent;

import java.util.concurrent.*;

/**
 * 方法5
 *
 * @author zhenyong
 */
public class Homework0305 {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        final int[] result = new int[1];

        CyclicBarrier cyclicBarrier = new CyclicBarrier(1, new Runnable() {
            @Override
            public void run() {
                // 确保 拿到result 并输出
                System.out.println("异步计算结果为：" + result[0]);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                result[0] = Homework03.sum1();
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }
}
