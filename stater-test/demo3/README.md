# demo-spring-boot-starter
自定义redis-spring-boot-starter  包含3个模块
module-1  redis-spring-boot-starter 对外配置提供模块，封装了autoconfig模块，达到隐藏的作用
module-2  spring-boot-autoconfig  真正的自动配置模块，内容如下
#1、properties 配置类
RedisProperties  JedisPoolConfigProperties
#2、bean 配置类，需要引入properties 配置
MyRedisAutoConfigulation 

#3、创建文件夹   MEAT-INF,创建 spring.factories 文件，配置bean配置类的路径
#-------starter自动装配---------
org.springframework.boot.autoconfigure.EnableAutoConfiguration=org.example.config.MyRedisAutoConfigulation
# StaterTestApplication 引入 自动配置的bean (redisService)
# 需要在pom 文件引入自定义的starter   redis-spring-boot-stater 依赖
StaterTestApplication

module-3 stater-test 引入自定义的stater,也即引入redis-spring-boot-starter依赖，进行测试