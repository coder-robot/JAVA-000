# 6. （必做）研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：
### 1）`当前作业 ---> 使用 JDBC 原生接口，实现数据库的增删改查操作。`---实现了纯jdbc操作和jdbcTemplate操作
### 2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
### 3）配置 Hikari 连接池，改进上述操作。提交代码到 Github。

# http://localhost:6001/
```shell script
curl http://localhost:6001/user/get/1
```

# 测试用数据库
```sql
-- 创建数据库
CREATE DATABASE `week5_zhenyong` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
-- 创建表
CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT
;
```

# 备用用户表
```
-- 创建表
CREATE TABLE `user2` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `nickname` varchar(128) DEFAULT NULL COMMENT '昵称',
  `password` varchar(128) DEFAULT NULL COMMENT '加密后的密码',
  `phone_number` varchar(64) DEFAULT NULL COMMENT '手机号',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `create_date` datetime DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL COMMENT '是否有效用户',
  `gender` int(11) DEFAULT NULL COMMENT '0:未知 \r\n 1:MALE \r\n 2:FEMALE',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_number` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT
;
```

# JDBC数据库交互接口,也就是JDBC API
```
DriverManager
Connection
Statement
ResultSet
DataSource--Pool
```