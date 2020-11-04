# 周六作业：实现过滤器

## 最简单的网关--gateway 2.0

### 网关请求地址 http://localhost:9888/gateway/${serverName}
${serverName}仅支持server01、server02、server03
例如 http://localhost:9888/gateway/server01

### 测试请求结果
```shell script

# curl http://localhost:9888/gateway/server01 
hello,server01:8801
# curl http://localhost:9888/gateway/server02
hello,server02:8802
# curl http://localhost:9888/gateway/server03 
hello,server03:8803

```