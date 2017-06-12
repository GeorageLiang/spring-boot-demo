# Spring-boot-test
<p>spring-boot 框架demo，一个目前不知道会变成什么样的工程</p>
<p>该项目为练习用，可以随意更改添加，但是要保证提交的代码能运行，而且请添加必要的注释，谢谢</p>


<h4>数据库</h4>
<p>可修改jdbc.properties配置本地数据库，如果新建或修改库或者表，请将对应的SQL上传至sql目录下</p>


<h4>本地启动(默认localhost:8080)</h4>
<ol>
<li>IDE中运行com.spittr.main.Application.java</li>
<li>使用mvn命令，mvn spring-boot:run</li>
<li>将工程打包成jar，进入jar所在目录，使用命令java -jar xxx.jar</li>
</ol>


<h4>properties文件</h4>
<ul>
<li>jdbc.properties：数据库配置文件，请根据本地配置修改</li>
<li>redis.properties：redis配置文件，请根据本地配置修改</li>
<li>application.properties：spring-boot通用配置文件，请根据本地配置修改</li>
</ul>


<h4>jar文件</h4>
<p>工程依赖的jar基本用maven，但是本地工具jar请手动添加依赖</p>
<p>my-utils-0.0.1-SNAPSHOT.jar：本地编写的一些常用工具类，可自行添加和修改代码</p>
<p>github地址：https://github.com/CodeTheft/my-utils</p>