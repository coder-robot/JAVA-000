package org.zhenyong.httpclient;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

/**
 * 获取对象大小
 *
 * @author zhenyong
 * @version 1.0
 * @date 2020/10/28 10:31 下午
 * @since JDK1.8
 */
public class ObjectSizeDemo5 {

    public static void main(String[] args) {

        X obj = new X();

        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        // System.out.println(ClassLayout.parseClass(X.class).toPrintable());
        System.out.println(GraphLayout.parseInstance(obj).toPrintable());
        System.out.println(GraphLayout.parseInstance(obj).totalSize());

    }

    static class X {
        int a;
        byte b;
        Integer c;
    }
}
