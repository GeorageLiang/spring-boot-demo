# Spring-boot-test
<p>spring-boot 框架demo，一个目前不知道会变成什么样的工程</p>
<p>该项目为练习用，可以随意更改添加，但是要保证提交的代码能运行，而且请添加必要的注释，谢谢</p>

本地启动(默认localhost:8080)
1.IDE中运行com.spittr.main.Application.java
2.使用mvn命令，mvn spring-boot:run
3.将工程打包成jar，进入jar所在目录，使用命令java -jar xxx.jar

xml文件
spring-spittr-container.xml：spring通用配置
spring-spittr-datasource.xml：datasource配置，如新增db，请加到该文件
spring-spittr-mapper.xml：mybatis映射配置，如新增mapper，请加到该文件


properties文件
jdbc.properties：数据库配置文件，请根据本地配置修改
redis.properties：redis配置文件，请根据本地配置修改


jar文件
工程依赖的jar基本用maven，但是本地工具jar请手动添加依赖
my-utils-0.0.1-SNAPSHOT.jar：本地编写的一些常用工具类，可自行添加和修改代码
github地址：https://github.com/CodeTheft/my-utils