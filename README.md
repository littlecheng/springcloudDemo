# springcloudDemo项目使用说明

1.注册中心集群部署 可以访问 http://localhost:8761/  ，http://localhost:8762/  ，http://localhost:8763/  

2.提供者集群部署  可以访问 http://eureka001.com:8001/dept/getInfo，http://localhost:8002/dept/getInfo，http://localhost:8003/dept/getInfo

3.消费者ribbon ，可以负载均衡 可以访问 http://localhost:8040/dept/info

4.消费者fegin ,可以负载均衡 可以访问  http://localhost:8050/handle/select/1，http://localhost:8050/handle/getInfo

5.配置映射文件  C:\Windows\System32\drivers\etc\hosts
>127.0.0.1	eureka000.com

>127.0.0.1	eureka001.com

>127.0.0.1	eureka002.com
>

6.启动注册中心，三个提供者，网关
* 访问http://localhost:9001/user/select/2 
* 访问http://localhost:9001/dept/select/1 
* http://localhost:9001/dept/getInfo  会发现端口不停交替轮询，可以发现网关具有负载均衡作用，其他作用待发现。。。

7.注册中心添加了安全防护

    7.1添加 pom.xml
         <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        
    7.2 注册中心启动类添加如下代码
    @EnableWebSecurity
    static class WebSecurityConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception{
            http.csrf().ignoringAntMatchers("/eureka/**");
            super.configure(http);
        }
    }
    
    7.3 bootstrap.xml
    security: #开启 http basic 的安全认证
    user:
      name: user
      password: 123456
    eureka:
      client:
         defaultZone: http://user:123456@localhost:8761/eureka/
         
     7.4其他微服务需要在注册中心注册时，必须加上user:12345@
     
     7.5在刷新actuator/bus-refresh时，一定要把你的host文件关于github.com注释掉，不然启动配置中心时会报  Connect to github.com:443 [github.com/13.229.188.59] failed: Read timed out
     #140.82.112.3 github.com
     #140.82.113.9 codeload.github.com
     #199.232.69.194  github.global.ssl.fastly.net
     
    7.6 先启动注册中心，再启动配置中心，最后启动客户端  
