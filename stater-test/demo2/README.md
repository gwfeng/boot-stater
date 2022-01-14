# demo-spring-boot-starter
自定义spring-boot-starter

#1、properties 配置类
RedisProperties 
#2、bean 配置类，需要引入properties 配置
RedisAutoConfiguration 

#3、创建文件夹   MEAT-INF,创建 spring.factories 文件，配置bean配置类的路径
#-------starter自动装配---------
org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.example.redisspringbootstater.RedisAutoConfiguration

# StaterTestApplication 引入 自动配置的bean (redisService)
# 需要在pom 文件引入自定义的starter   redis-spring-boot-stater 依赖
StaterTestApplication