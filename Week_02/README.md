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
```bash
1.执行brew install wrk
如果显式brew update很慢，可以ctrl+C打断更新
2.输入 wrk
执行 wrk -t8 -c40 -d60s http://localhost:8088/api/hello
```
### 2. 编译GCLogAnalysis.java(为方便操作移除了包名)
```bash
# 编译
javac -g GCLogAnalysis.java
# 查看字节码
javap -v GCLogAnalysis
# 执行代码
java GCLogAnalysis
```

## 二、GC 日志解读与分析
```
# GC Tool
https://gceasy.io/
## https://www.tagtraum.com/gcviewer.html的分支
https://github.com/chewiebug/GCViewer
https://perfma.com/product/community
```
![avatar](ref_images/GC-001.png)

### 1. 简单示例
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
```

### 3. 分别使用512m、1024m、2048m、4096m堆内存观察 GC 信息的不同
```bash
# -Xms512m -Xmx512m
java -Xms512m -Xmx512m -XX:+PrintGCDetails GCLogAnalysis
# -Xms1024m -Xmx1024m 或 -Xms1g -Xmx1g
java -Xms1g -Xmx1g -XX:+PrintGCDetails GCLogAnalysis
# -Xms2048m -Xmx2048m 或 -Xms2g -Xmx2g
java -Xms2g -Xmx2g -XX:+PrintGCDetails GCLogAnalysis
# -Xms4096m -Xmx4096m 或 -Xms4g -Xmx4g
java -Xms4g -Xmx4g -XX:+PrintGCDetails GCLogAnalysis
```

### 4. 串行GC 日志解读与分析
```bash
# 观察 Young GC 与 Full GC
java -XX:+UseSerialGC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
```

### 5. 并行GC 日志解读与分析
```bash
# 观察 Young GC 与 Full GC
java -XX:+UseParallelGC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
# 思考：如果不配置 Xms 会怎么样？
java -XX:+UseParallelGC -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
```

### 6. CMS GC 日志解读与分析
```bash
# 观察 Young GC 与 Full GC
java -XX:+UseConcMarkSweepGC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
# 思考：假如 Xmx/Xms 设置 4g 会怎么样？
java -XX:+UseConcMarkSweepGC -Xms4g -Xmx4g -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
# 4g 内存下跟并行 gc 相比呢？
java -XX:+UseG1GC -Xms4g -Xmx4g -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
```

### 7. G1 GC 日志解读与分析
```bash
# 观察 Young GC 与 Full GC
java -XX:+UseG1GC -Xms512m -Xmx512m -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
# 思考：假如 Xmx/Xms 设置 4g 会怎么样？
java -XX:+UseG1GC -Xms4g -Xmx4g -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
# 4g 内存下跟 cms gc 相比呢？
java -XX:+UseConcMarkSweepGC -Xms4g -Xmx4g -Xloggc:gc.demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
```

### 8. [扩展练习]通过jdk15观察ZGC和ShenandoahGC的情况

### 总结：
    1. 如何查看/分析不同 GC 配置下的日志信息？
    答：

    2. 各种 GC 有什么特点和使用场景？
    答：

## 三、JVM 线程堆栈数据分析
```
# Java Thread Dump Analyzer
https://fastthread.io/
```

### 1. JVM 线程堆栈数据分析工具
```bash
jconsole
jvisualvm
jmc
```

## 四、内存分析与相关工具
```
# 请思考一个问题：
一个对象具有100个属性，与100个对象每个具有1个属性，哪个占用的内存空间更大？

#
OutOfMemoryError: Java heap space

# 内存 Dump 分析工具
• Eclipse MAT
• jhat
```

## 五、 JVM 问题分析调优经验
```
```

## 六、 GC 疑难情况问题分析
```
# Java Diagnostic Tool
https://arthas.aliyun.com/zh-cn/
```
### 1. arthas常见案例分析
```bash
```