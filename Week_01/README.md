## JVM学习笔记

# 一、java进阶训练营开发环境准备
### 1、运行环境
```
- macOS Catalina 10.15.7
- Idea Ultimate 2020.2.1
- jdk-8u231-macosx-x64.dmg与训练营版本保持一致
- Apache Maven 3.6.2
- git 2.24.3
- mysql:5.6.49 port:3308
- mysql:5.7.31 port:3309
- mysql:8.0.21 port:3307
```
### 2、本机额外已安装的其它版本JDK
```
- adoptopenjdk8.0.265.hotspot
- openjdk11.0.2
- openjdk12.0.2
- openjdk13.0.2
- openjdk14.0.2
- openjdk-15-ga
```

## 3、其它版本JDK下载地址
```
# oracle官方版下载地址
https://www.oracle.com/java/technologies/oracle-java-archive-downloads.html

# oracle openjdk 下载地址
http://jdk.java.net/archive/

# 通过sdkman安装多版本openjdk并进行切换
# https://sdkman.io/install
sdk install java 11.0.3.hs-adpt
cd ~/.sdkman/candidates/java
ln -s /Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home jdk1.8.0_231
# 临时使用jdk1.8.0_231
sdk use java jdk1.8.0_231
# 默认使用jdk1.8.0_231
sdk default java jdk1.8.0_231

```

## 4、一些疑问
```
4.1 jvm中的线程和系统中的线程是一一对应的关系吗?
4.2 java、jcmd、jps等命令应该是C通过JNI和JAVA代码结合写的吗？
注意java的JNI和C/C++的互操作性。
4.3 如何通过命令查看jvm的所有内存分布情况?
4.4 如何通过命令查看java程序中都设置了哪些虚拟机参数？
```

## 编译的时候需要加上-g参数,否则没有局部变量表信息。
javac -g MovingAverage.java ForLoopTest.java

## 5、查看java命令相关参数设置
```bash
# 查看java命令帮助
man java
java --help
# 查看jvm非标准参数
java -X
# 查看jvm非稳定参数
java -XX:+PrintFlagsFinal
```

## 6、jvm常用参数笔记
```bash
-Xms<size>        设置初始 Java 堆大小
-Xmx<size>        设置最大 Java 堆大小
-Xss<size>        设置 Java 线程堆栈大小
```
