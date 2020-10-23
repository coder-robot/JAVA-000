# JVM调优分析笔记

## 一、准备工作
```
# 操作系统
MacOSX 10.15.7 16CPU 32G
# jdk
JDK1.8.0_231
# 用于分析的java类
ref_files/GCLogAnalysis.java(utf-8编码的文件)
# http压测工具
wrk
# 测试用spring boot应用 http://localhost:8088/api/hello
ref_files/gateway-server-0.0.1-SNAPSHOT.jar
```
### 1. Mac 安装wrk
```
1.执行brew install wrk
如果显式brew update很慢，可以ctrl+C打断更新
2.输入 wrk
执行 wrk -t8 -c40 -d60s http://localhost:8088/api/hello
```
### 2. 编译GCLogAnalysis.java(为方便操作移除了包名)
```
# 编译
javac -g GCLogAnalysis.java
# 查看字节码
javap -v GCLogAnalysis
# 执行代码
java GCLogAnalysis
```

## 二、GC 日志解读与分析
![avatar](ref_images/GC-001.png)

### 1. GC查看简单示例
```bash
# 打印GC详情
java -XX:+PrintGCDetails GCLogAnalysis
# 将GC日志保存至文件中
java -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
# 查看文件中的GC日志
less gc.demo.log
```

### 2. 模拟一下 OOM
```bash
# 模拟一下 OOM
java -Xmx128m -XX:+PrintGCDetails GCLogAnalysis
# -Xms512m -Xmx512m
java -Xms512m -Xmx512m -XX:+PrintGCDetails GCLogAnalysis
# -Xms1024m -Xmx1024m 或 -Xms1g -Xmx1g
java -Xms1024m -Xmx1024m -XX:+PrintGCDetails GCLogAnalysis
# -Xms2048m -Xmx2048m 或 -Xms2g -Xmx2g
java -Xms2048m -Xmx2048m -XX:+PrintGCDetails GCLogAnalysis
```

### 3. 分别使用 512m,1024m,2048m,4096m,观察 GC 信息的不同
```bash
# 模拟一下 OOM
java -Xmx128m -XX:+PrintGCDetails GCLogAnalysis
# -Xms512m -Xmx512m
java -Xms512m -Xmx512m -XX:+PrintGCDetails GCLogAnalysis
# -Xms1024m -Xmx1024m 或 -Xms1g -Xmx1g
java -Xms1g -Xmx1g -XX:+PrintGCDetails GCLogAnalysis
# -Xms2048m -Xmx2048m 或 -Xms2g -Xmx2g
java -Xms2g -Xmx2g -XX:+PrintGCDetails GCLogAnalysis
# -Xms4096m -Xmx4096m 或 -Xms4g -Xmx4g
java -Xms4g -Xmx4g -XX:+PrintGCDetails GCLogAnalysis
```