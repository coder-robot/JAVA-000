package me.zhenyong.gateway1;

import me.zhenyong.gateway1.filter.post.PostFilter;
import me.zhenyong.gateway1.filter.pre.PreFilter;
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
import java.util.List;

@SpringBootApplication
@RestController
public class Gateway2Application {

    private final OkHttpClient client;

    private final List<PreFilter> preFilters;
    private final List<PostFilter> postFilters;

    public Gateway2Application(List<PreFilter> preFilters, List<PostFilter> postFilters) {
        this.preFilters = preFilters;
        this.postFilters = postFilters;
        client = new OkHttpClient();
    }

    public static void main(String[] args) {
        SpringApplication.run(Gateway2Application.class, args);
    }

    @GetMapping("/gateway/{serverName}")
    public String gateway(@PathVariable String serverName) {
        if (StringUtils.isEmpty(serverName)) {
            return "非法请求\n";
        }

        String inbound = preFilter(serverName);

        String outbound = routing(inbound);

        return postFilter(outbound);
    }

    /**
     * 前置过滤器
     *
     * @param inbound 入站数据
     */
    private String preFilter(String inbound) {
        for (PreFilter preFilter : preFilters) {
            inbound = preFilter.process(inbound);
        }
        return inbound;
    }

    /**
     * 后置过滤器
     *
     * @param outbound 出站数据
     */
    private String postFilter(String outbound) {
        for (PostFilter postFilter : postFilters) {
            outbound = postFilter.process(outbound);
        }
        return outbound;
    }

    /**
     * 路由服务请求
     *
     * @param serverName 服务名称
     * @return 响应内容
     */
    private String routing(String serverName) {

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
    private String httpGetWithOkHttp(String endpoint) {

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
