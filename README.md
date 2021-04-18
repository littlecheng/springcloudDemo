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

6.启动注册中心，提供者，网关
* 访问http://localhost:9001/user/select/2 
* 访问http://localhost:9001/dept/select/1
