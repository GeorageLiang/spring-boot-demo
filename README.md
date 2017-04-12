# Spring-boot-test
<p>spring-boot 框架demo，一个目前不知道会变成什么样的工程</p>
<p>该项目为练习用，可以随意更改添加，但是要保证提交的代码能运行，而且请添加必要的注释，谢谢</p>

<h4>本地启动(默认localhost:8080)</h4>
<ol>
<li>IDE中运行com.spittr.main.Application.java</li>
<li>使用mvn命令，mvn spring-boot:run</li>
<li>将工程打包成jar，进入jar所在目录，使用命令java -jar xxx.jar</li>
</ol>


<h4>xml文件</h4>
<ul>
<li>spring-spittr-container.xml：spring通用配置</li>
<li>spring-spittr-datasource.xml：datasource配置，如新增db，请加到该文件</li>
<li>spring-spittr-mapper.xml：mybatis映射配置，如新增mapper，请加到该文件</li>
</ul>


<h4>properties文件</h4>
<ul>
<li>jdbc.properties：数据库配置文件，请根据本地配置修改</li>
<li>redis.properties：redis配置文件，请根据本地配置修改</li>
</ul>


<h4>jar文件</h4>
<p>工程依赖的jar基本用maven，但是本地工具jar请手动添加依赖</p>
<span>my-utils-0.0.1-SNAPSHOT.jar：本地编写的一些常用工具类，可自行添加和修改代码</span>
<span>github地址：https://github.com/CodeTheft/my-utils</span>