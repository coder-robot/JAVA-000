package me.zhenyong.concurrent;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * 一个简单的代码参考：
 * @see "https://github.com/kimmking/JavaCourseCodes/blob/main/03concurrency/0301/src/main/java/java0/conc0303/Homework03.java"
 * @author zhenyong
 */
@Deprecated
public class Homework03 {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        // 这是得到的返回值
        int result = sum1();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }

    public static int sum1() {
        return fb1(36);
    }
    private static int sum2() {
        return fb2(36);
    }
    private static int sum3() {
        return fb3(36);
    }
    private static int sum4() {
        return fb4(36);
    }

    private static int fb1(int a) {
        if (a < 2) {
            return 1;
        }
        return fb1(a - 1) + fb1(a - 2);
    }

    private static int fb2(int a) {
        if (a <= 0)
            return 0;
        if (a == 1)
            return 1;

        int f1 = 1;
        int f2 = 1;
        int result = 0;
        int i = 2;
        while (i++ <= a) {
            result = f1 + f2;
            f1 = f2;
            f2 = result;
        }
        return result;
    }

    private static int fb3(int a) {
        if (a <= 0)
            return 0;
        if (a == 1)
            return 1;

        int f1 = 1;
        int f2 = 1;
        int i = 2;
        while (i++ <= a) {
            f2 = f1 + f2;
            f1 = f2 - f1;
        }
        return f2;
    }

    private static int fb4(int a) {
        if (a < 2)
            return 1;
        int a1 = 0, a2 = 1;
        for (int i = 2; i <= a; i++) {
            a2 = a1 + (a1 = a2);
        }
        return a1 + a2;
    }
}