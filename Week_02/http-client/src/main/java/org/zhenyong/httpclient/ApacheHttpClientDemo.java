package org.zhenyong.httpclient;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ApacheHttpClientDemo
 *
 * @author zhenyong
 * @version 1.0
 * @date 2020/10/26 11:41 下午
 * @see OkHttpDemo
 * @since JDK1.8
 */
public class ApacheHttpClientDemo {

    public static void main(String[] args) throws Exception {

        // String url = "http://localhost:8801";
        String url = "https://www.baidu.com";

        try (CloseableHttpClient httpclient = HttpClients.custom().build()) {

            HttpGet httpget = new HttpGet(url);

            httpget.setConfig(
                    RequestConfig.custom()
                            .setSocketTimeout(3000)
                            .setConnectTimeout(3000)
                            .setConnectionRequestTimeout(15000)
                            .build()
            );

            HttpClientContext context = HttpClientContext.create();

            System.out.println("executing request " + httpget.getURI());

            try (CloseableHttpResponse response = httpclient.execute(httpget, context)) {

//                System.out.println("----------------------------------------");
//                System.out.println(response.getStatusLine());
//                System.out.println(EntityUtils.toString(response.getEntity()));
//                System.out.println("----------------------------------------");

                Pattern titlePattern = Pattern.compile("<title.*>(.*)</title>");
                String reponseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
                Matcher m = titlePattern.matcher(reponseBody);
                if (m.find()) {
                    System.out.println(m.group(1));
                }
            }
        }
    }
}
