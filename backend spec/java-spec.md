# 技术选型

spring cloud版本：Finchley.SR2

spring boot 版本：2.0.6.RELEASE

配置中心：spring cloud config - > git

服务发现：eureka

apm：听云

eventbus：内存：guava，跨服务：rabbitmq，redis

日志：logback 

trace：spring sleuth+elk+zipkin

网关：zuul

熔断：hystrix

数据库：mysql 5.7

缓存: redis  3.2.8

DB读写分离：阿里云

sharding: sharding-sphere

redisproxy：阿里云

版本管理：gradle 4

base项目：com.ytx.futures:base : http://gitlab.yintech.net/ytx/java-backend/futures/commons，version:1.2.1-SNAPSHOT

容器：docker 17.03.1

docker-compose：1.11.2

docker-image: daocloud.io/baidao/ytx-java-8:v1.3（trap信号）

Json:fastjson:1.2.31

单测：Junit:4.12

Mock:1.10.19

CI/CD : gitlab

JPA：spring-data-jpa

# 代码结构

- 贫血模型：
  - controller->service->dao
  - service层进行事务管理
- 充血模型（ddd optional）：
  - Application,domain,infrastructure,interface
  - infrastructure中包含dao和repository层。dao负责单表的数据库处理，repository负责模型的数据库操作
  - repository层进行事务管理
  - cqrs管理
- 模型命名规范
  业务模型：domain (User)
  PO：持久层模型 (UserPO)
  DTO：对外接口模型 (UserDTO)
  

# 数据库

库名：futures_开头

表名：t模块名表名 (t_sso_user)

表结构字段规范：

	主键：bigint,自增

	字段名：下划线分隔

	create_time：datetime

	update_time：timestamp CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

	deleted tineint(4)  0：未删除，1：删除

	每个字段需要加上注释

编码：utf8mb4_unicode_ci

连接池：HikariCP

Db migration：flyway，所有数据库变更记录在代码sql文件夹中，以时间戳结尾命名

where条件或者orderby需要加必要索引


## 中间件
- rabbitmq
- emqtt
- mysql
- redis
- mongodb
- elasticsearch

# 接口规范

1. 参数校验：javax.validator
2. 使用GET、POST、PUT、DELETE、PATCH 共5个HTTP Method，而非简单的GET和POST两者
3. Restful path规范：
   1. 资源使用复数表示，如果users
   2. GET,POST,PUT,PATCH的path中包含资源id，比如GET /users/{id}
4. 接口不能有全量接口，必须分页，pageSize<=200
5. 返回码规范：
   1. 使用httpcode标示接口返回情况
   2. 业务情况通过http body返回
   3. http code 说明
      1. API 正常工作 (200, 201,204)
      2. 客户端错误 (400, 401, 403, 404)
      3. 服务端错误 (500, 503)
   4. 超时时间：500ms内
   5. GET 请求单个不存在资源，返回404，请求多个资源，不存在返回200.  
6. 接口参数  
    - 普通接口
    ```
      入参：  
        所有参数requestDTO（id请求除外）  
      出参：
        http code：200
        http body：{
          "errorCode":"0"	//业务code
            "errorMessage":"OK"
            "data":{}
        } 
    ```

    - 下拉接口  
    ```
      入参
        pageSize,lastRecordTime,firstRecordTime
      出参
        http code：200
        http body：
        {
          "errorCode":"0"
          "errorMessage":"OK"
          "data":{
            "totalnumber":1,
            "list":[]
          }
        }
    ```
       
    - 分页接口  
    ```
      入参
       pageSize,pageNo,orderBy,order
      出参
        http code：200
        http body：
        {
          "errorCode":"0"
          "errorMessage":"OK"
          "data":{
            "totalnumber":1,
            "list":[]
          }
        }
    ```
    - 健康检查
    ```
      路径: /health
      返回值：
      {
        "status":"ok"
        "redis":{
          "ip":"redis ip",
          "status":"ok"
        }
        "db":{
          "ip":"db ip",
          "status":"ok"
        }
        "mq":{
          "ip":"mq ip",
          "status":"ok"
        }
        如果有外部依赖，写这里
      }
    ```
   

# 设计文档规范

1. plan meeting前产出，内容包括
- 数据库表结构
- 接口
- 流程图
2. 接口文档工具：http://swagger.io/swagger-editor/
3. 设计原则：SOLID


# spring cloud config配置：

configserver环境：开发测试、线上环境

git地址：http://gitlab.yintech.net/

分支管理：

	dev分支：用于开发测试

	master分支：用于staging和线上

文件名管理：

	模块名-dev.yml 开发环境

	模块名-test.yml 开发环境

	模块名-staging.yml staging环境

	模块名-prod.yml prod环境

**配置管理注意权限控制**


# 日志规范

    {
    "@timestamp":"%d{yyyy-MM-dd HH:mm:ss}"
    "severity": "%level",
    "service": "${springAppName:-}",
    "trace": "%X{X-B3-TraceId:-}",
    "span": "%X{X-B3-SpanId:-}",
    "parent": "%X{X-B3-ParentSpanId:-}"
    "pid": "${PID:-}",
    "thread": "%thread",
    "class": "%logger{40}",
    "rest": "%message"
    "time": '10ms',
    "code": '2xx/3xx/4xx/5xx'
    }
    

    2018-02-06 10:53:50.591  INFO [cms-content,00ccfd602a435a7a,8b2da15b09b75028,false] 8 --- [io-8082-exec-16] c.y.f.base.filter.RequestLoggingFilter   : After request [method=POST,After request [uri=/comment;payload={"roomId":"5","content":"我不会","varietyId":"5","userId":47842,"platformId":4,"auditState":"PENDING","authorType":"USER","contentType":"TEXT","interactionType":"COMMENT","variety":"studio"}],time=5(ms),code=201]
    
    时间 LEVEL [module,trace,spanid,exportable] pid --- [threadid] classname : payload, time, code



# 测试规范

1. 单测
   1. AAA规范（arrange-act-assert）
   2. mock，stub
   3. 用例说明: given，when，then
   4. 每个测试用例测试一个功能
   5. mockito,junit


# 远程调用

1. 线上C端接口响应 < 200ms，B端接口<1s
2. 重试次数：3次（GET请求自动重试，post客户端重试）
3. post,put,delete请求需要支持幂等



# 工程质量

sonar红绿灯控制

- 单测行覆盖: >=50%
- 函数行数：<=20
- 代码重复率：<1%
- 圈复杂度：平均<1.5
- 项目依赖：无循环依赖
- PMD, Findbug无P1 bug
- sql需要加索引
- 响应时间<=200ms

# 代码规范
https://gist.github.com/wojteklu/73c6914cc446146b8b533c0988cf8d29


