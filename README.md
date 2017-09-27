# Spring-boot-test
<p>spring-boot 框架demo，一个目前不知道会变成什么样的工程</p>
<p>该项目为练习用，可以随意更改添加，但是要保证提交的代码能运行，而且请添加必要的注释，谢谢</p>

<h4>开发环境</h4>
<ul>
<li>JDK: 1.8</li>
<li>MySQL Server: 5.7</li>
<li>Redis: 3.2.6</li>
</ul>


<h4>数据库</h4>
<p>可修改application.properties配置本地数据库，如果新建或修改库或者表，请将对应的SQL上传至sql目录下</p>


<h4>本地启动(默认localhost:8080)</h4>
<ol>
<li>IDE中运行com.spittr.main.Application.java</li>
<li>使用mvn命令，mvn spring-boot:run</li>
<li>将工程打包成jar，进入jar所在目录，使用命令java -jar xxx.jar</li>
</ol>


<h4>多环境配置文件</h4>
<p>resources目录下建立不同开发环境目录，例如dev（本地开发），product（线上生产），在对应环境下创建配置文件application.properties</p>
<p>使用maven命令打包不同环境，例如打包生产环境：mvn package -Pproduct</p>


<h4>properties文件</h4>
<p>application.properties：spring-boot通用配置文件，可配置服务端口，jdbc连接，redis连接等</p>


<h4>jar文件</h4>
<p>工程依赖的jar基本用maven，但是本地工具jar请手动添加依赖</p>
<p>my-utils-0.0.1-SNAPSHOT.jar：本地编写的一些常用工具类，可自行添加和修改代码</p>
<p>github地址：https://github.com/CodeTheft/my-utils</p>