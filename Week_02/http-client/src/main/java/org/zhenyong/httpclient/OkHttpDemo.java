package org.zhenyong.httpclient;

import cn.hutool.core.util.StrUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * OkHttpDemo
 *
 * @author zhenyong
 * @version 1.0
 * @date 2020/10/26 11:41 下午
 * @see ApacheHttpClientDemo
 * @since JDK1.8
 */
public class OkHttpDemo {

    final static OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {

        // String url = "http://localhost:8801";
        String url = "https://www.baidu.com";
        String responseBody = null;
        try {
            responseBody = getUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!StrUtil.isBlank(responseBody)) {
            Pattern titlePattern = Pattern.compile("<title.*>(.*)</title>");
            Matcher m = titlePattern.matcher(responseBody);
            if (m.find()) {
                System.out.println(m.group(1));
            }
        }
    }

    private static String getUrl(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
