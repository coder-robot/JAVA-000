package me.zhenyong.gateway1;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@SpringBootApplication
@RestController
public class Gateway1Application {

    final static OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {
        SpringApplication.run(Gateway1Application.class, args);
    }

    @GetMapping("/gateway/{serverName}")
    public String gateway(@PathVariable String serverName) {
        if (StringUtils.isEmpty(serverName)) {
            return "非法请求\n";
        }
        return routing(serverName);
    }

    /**
     * 路由服务请求
     *
     * @param serverName 服务名称
     * @return 响应内容
     */
    private static String routing(String serverName) {

        String endpoint = null;
        switch (serverName.toLowerCase()) {
            case "server01":
                endpoint = "http://localhost:8801";
                break;
            case "server02":
                endpoint = "http://localhost:8802";
                break;
            case "server03":
                endpoint = "http://localhost:8803";
                break;
        }

        if (StringUtils.isEmpty(endpoint)) {
            return "未知请求\n";
        }
        return httpGetWithOkHttp(endpoint);
    }

    /**
     * HTTP GET请求
     *
     * @param endpoint 后端服务器地址 例如http://localhost:8801
     * @return 响应内容
     */
    private static String httpGetWithOkHttp(String endpoint) {

        Request request = new Request.Builder()
                .url(endpoint)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "服务器正忙\n";
        }
    }

}
