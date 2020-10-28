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
public class ObjectSizeDemo3 {

    public static void main(String[] args) {

        // 查看对象内部信息
        System.out.println("------>对象内部信息size: " + ClassLayout.parseInstance(X.class).toPrintable());

        // 查看对象外部信息
        System.out.println("------>对象外部信息size: " + GraphLayout.parseInstance(X.class).toPrintable());

        // 获取对象总大小
        System.out.println("------>对象总大小size : " + GraphLayout.parseInstance(X.class).totalSize());

    }
}
