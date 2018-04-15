# 基于SpringBoot快速搭建SpringWebDemo

IDE: Intellij IDEA

技术栈: SpringBoot + SpringDataJPA + H2 + Thymeleaf + Lombok

有什么用: emmmm 方便自己做些web相关的实验blablabla

---

## 召唤术

```
git clone https://github.com/xuzhenyang/SpringWebDemo.git
```

## 弹射起步

1. 在IDEA里通过Spring Initializr创建项目

2. 勾选需要的组件

![](http://7xrr7e.com1.z0.glb.clouddn.com/TIM%E6%88%AA%E5%9B%BE20180402214042.jpg)

3. 坐和放宽，等待Maven构建完成

---

## 常用框架、组件

### Lombok

你懂的 不懂找谷歌

### H2

H2是嵌入式DB，配置后还可以在浏览器中访问H2的控制台，非常方便！

首先创建H2WebConsoleConfig.java，代码如下：

```
@Configuration
public class H2WebConsoleConfig {
    @Bean
    ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
}

```

需要注意的是，WebServlet类在org.h2.server.web.WebServlet中，并且需要去除pom.xml中scope属性

```
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <!--<scope>runtime</scope>-->
</dependency>
```

配置完成后，就可以在浏览器中访问localhost:8080/console来使用Console

[](H2WebConsole)

当然还需要在application.properties中配置连接信息

H2支持内存或文件的形式存储，默认的用户名为sa，密码为空

```
#spring.datasource.url=jdbc:h2:file:./src/main/resources/database/mydb #文件db
spring.datasource.url=jdbc:h2:mem:mydb #内存db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
```

### Thymeleaf

Thymeleaf是Spring官方推荐的模板引擎

SpringBoot已经升级到2.0.0了，内嵌的Thymeleaf也升级到了3.0，所以忽略以下。

~~有个小坑需要注意，Thymeleaf默认启用HTML的严格检查模式，经常会遇到标签未闭合而报错，我们可以在application.properties中配置：~~

```
spring.thymeleaf.mode=LEGACYHTML5
```

~~这样，对HTML标签的检查就不会辣么严格啦(LEGACYHTML5 需要搭配一个额外的库 NekoHTML 才可用)~~

### JPA

设置hibernate的方言，在加载hibernate时创建db表结构，并显示sql语句

```
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update 
spring.jpa.show-sql=true
```

注 ddl-auto属性：

validate           加载hibernate时，验证创建数据库表结构  
create             每次加载hibernate，重新创建数据库表结构  
create-drop        加载hibernate时创建，退出是删除表结构  
update             加载hibernate自动更新数据库结构

### SpringBootTest

在Test类上加上注解：

```
@RunWith(SpringRunner.class)
@SpringBootTest
```
