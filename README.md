# xxwd-server-master
熊熊问答服务端

## 核心配置
> application.yml
```yml
spring:
  thymeleaf:
    mode: HTML5
logging:
  level:
    org:
      springframework: error
    com:
      xlx:
        xxwd:
          dao: debug
server:
  port: 8888

 # port: ${port:8888}

# 记得替换成你的id和secret
wechat:
  appid: 
  secret:

# h2
db:
  url: jdbc:h2:~/xxwd
  username: sa
  password: 123
  driver: org.h2.Driver

# mysql
#db:
#  url: jdbc:mysql://localhost:3306/jiuask?reconnect=true
#  username: pig
#  password: 123456
#  driver: com.mysql.jdbc.Driver
```
