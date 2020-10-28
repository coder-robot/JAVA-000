package org.zhenyong.httpclient;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * HuToolDemo
 *
 * @author zhenyong
 * @version 1.0
 * @date 2020/10/27 11:24 上午
 * @since JDK1.8
 */
public class HuToolDemo {

    public static void main(String[] args) {

        // String url = "http://localhost:8801";
        String url = "https://www.baidu.com";

        HttpResponse httpResponse = HttpUtil.createGet(url).execute();
        // System.out.println(httpResponse.header("Server"));

        Pattern titlePattern = Pattern.compile("<title.*>(.*)</title>");
        Matcher m = titlePattern.matcher(httpResponse.body());
        if (m.find()) {
            System.out.println(m.group(1));
        }
    }
}
