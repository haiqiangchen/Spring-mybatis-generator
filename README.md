# Spring-mybatis-generator逆向框架生成接口和配置文件项目
## 1、项目的背景
Spring-Mybatis框架的配置文件编写十分消耗时间，所以楼主这里采用mybatis-generator-core的实现的方法，自动的生成Mapper配置文件和接口的方法。
## 2、项目的准备
* 开发的环境：ieda
* 开发所需要的jar包：Spring相关的jar包、mybatis-generator-core jar包，mybatis jar包、数据库的驱动jar包
## 3、项目开始
### 3.1、建立maven项目
很多同学可能很喜欢使用gradle构建工具，其实都一样，本项目使用maven构建作为一个例子，使用ieda或者eclipse构建一个简单的maven项目。
### 3.2、编写pom.xml文件
 pom.xml文件主要编写相应的依赖包和编译的war包设置，具体的配置如下：
 ```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.springapp</groupId>
    <artifactId>Mybatis-generator</artifactId>
    <packaging>war</packaging>
    <version>1.0-SNAPSHOT</version>
    <name>Mybatis-generator</name>
 
    <properties>
        <spring.version>4.1.1.RELEASE</spring.version>
        <mybatis.version>3.2.1</mybatis.version>
        <mysql.version>5.1.35</mysql.version>
    </properties>
 
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>${spring.version}</version>
        </dependency>
 
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <!-- mysql驱动包 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>${mysql.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
        </dependency>
 
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.1</version>
            <scope>provided</scope>
        </dependency>
 
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring.version}</version>
        </dependency>
 
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>${spring.version}</version>
            <scope>test</scope>
        </dependency>
 
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.mybatis.generator/mybatis-generator-core -->
        <dependency>
            <groupId>org.mybatis.generator</groupId>
            <artifactId>mybatis-generator-core</artifactId>
            <version>1.3.5</version>
        </dependency>
        <!-- alibaba data source 相关jar包-->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>0.2.23</version>
        </dependency>
        <!-- alibaba fastjson 格式化对 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.1.41</version>
        </dependency>
        <!--mybatis依赖 -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>${mybatis.version}</version>
        </dependency>
 
        <!-- mybatis/spring包 -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.2.0</version>
        </dependency>
    </dependencies>
 
    <build>
        <finalName>Mybatis-generator</finalName>
        <plugins>
            <plugin>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>1.6</source>
                    <target>1.6</target>
                </configuration>
            </plugin>
            <plugin>
                <artifactId>maven-surefire-plugin</artifactId>
                <configuration>
                    <includes>
                        <include>**/*Tests.java</include>
                    </includes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```
### 3.3、编写mybatis-generator的配置文件
想了解比较仔细点的内容可以参考官网http://www.mybatis.org/generator/ 对generator的使用说明。
  这里主要对配置文件
  > Generator.xml
  * 里面的标签做一个说明，首先先看大概的配置文件的内容：
  ```xml

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
 
<generatorConfiguration>
 
    <context id="DB2Tables" targetRuntime="MyBatis3">
        <commentGenerator>
            <property name="suppressAllComments" value="true" />
        </commentGenerator>
        <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                        connectionURL="jdbc:mysql://localhost:3306/MybatisGenerator?useUnicode=true"
                        userId="root"
                        password="haiqiang">
        </jdbcConnection>
 
        <javaTypeResolver >
            <property name="forceBigDecimals" value="false" />
        </javaTypeResolver>
      <!--此处存放实体类的文件-->
        <javaModelGenerator targetPackage="com.mybatis.generator.Entity" targetProject=".\src\main\java">
            <property name="enableSubPackages" value="true" />
            <property name="trimStrings" value="true" />
        </javaModelGenerator>
      <!--此处存放配置的文件-->
        <sqlMapGenerator targetPackage="Mapper"  targetProject=".\src\main\resources">
            <property name="enableSubPackages" value="true" />
        </sqlMapGenerator>
        <!--此处存放持久层的文件-->
        <javaClientGenerator type="XMLMAPPER" targetPackage="com.mybatis.generator.Repository"  targetProject=".\src\main\java">
            <property name="enableSubPackages" value="true" />
        </javaClientGenerator>
 
        <table  tableName="student" domainObjectName="student" > </table>
        <table  tableName="teacher" domainObjectName="teacher" > </table>
    </context>
</generatorConfiguration>
```
其中<javaModelGenerator>标签主要存放实体类的文件，<sqlMapGenerator>存放mapper下的配置文件，<javaClientGenerator>存放服务接口的文件。<table>则是编写数据库中的数据表，根据数据表生成相对应的实体类，mapper和服务接口。
### 3.4、编写生成的以上相关文件的程序
```java
/**
 * Created by haiqiang on 2018/1/11.
 */
public class test{
    public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
		//Generator.xml为3.3编写的generator的xml配置文件。
        File configFile = new File("./src/Generator.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
```
编写完成，直接运行以上的文件，就可以发现在每个包里面都生成相应的文件了。这就是Spring-mybatis-generator逆向框架生成的主要步骤。
