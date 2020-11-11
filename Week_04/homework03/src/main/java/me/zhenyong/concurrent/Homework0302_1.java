package me.zhenyong.concurrent;

import java.util.concurrent.*;

/**
 * 方法3
 *
 * @author zhenyong
 */
public class Homework0302_1 {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return Homework03.sum1();
            }
        });

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(task);

        Integer result = -1;
        try {
            result = task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 确保 拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
        executor.shutdown();
    }
}
