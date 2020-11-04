# 后端服务项目 backend-server

```shell script
# 运行环境
macosx + jdk1.8.0_231

# 打包程序
mvn clean package

# 启动程序,不要执行
## server01:8801
java -Xmx512m -cp target/backend-server-1.0-SNAPSHOT.jar me.zhenyong.server.HttpServer01 &
## server02:8802
java -Xmx512m -cp target/backend-server-1.0-SNAPSHOT.jar me.zhenyong.server.HttpServer02 &
## server03:8801
java -Xmx512m -cp target/backend-server-1.0-SNAPSHOT.jar me.zhenyong.server.HttpServer03 &

# 查看运行的程序PID,四行一块执行按回车
java -Xmx512m -cp target/backend-server-1.0-SNAPSHOT.jar me.zhenyong.server.HttpServer01 &
java -Xmx512m -cp target/backend-server-1.0-SNAPSHOT.jar me.zhenyong.server.HttpServer02 &
java -Xmx512m -cp target/backend-server-1.0-SNAPSHOT.jar me.zhenyong.server.HttpServer03 &
jps | awk '/HttpServer0/ {print $1}'

# 关掉程序
kill $(jps | awk '/HttpServer0/ {print $1}')

# 测试运行的程序
# curl http://localhost:8801
hello,server01:8801
# curl http://localhost:8802
hello,server02:8802
# curl http://localhost:8803
hello,,server03:8803

# 压测程序,linux压测正常,MaxOSX上压测报Connection reset by peer错误
wrk -t 8 -c 40 -d 30s --latency http://localhost:8801
wrk -t 8 -c 40 -d 30s --latency http://localhost:8802
wrk -t 8 -c 40 -d 30s --latency http://localhost:8803

# 常见问题
## mac上压测报错
$ curl http://localhost:8801
curl: (56) Recv failure: Connection reset by peer

```

# 代码来源链接
https://github.com/kimmking/JavaCourseCodes/tree/main/02nio/nio01/src/main/java/java0/nio01