### 可能用到的技术

Restful、SpringMVC、前端（Bootstrap、JS、jsp）

### 对整体框架的理解：

最开始让我比较困惑的是为什么Controller的代码要写在web的包里面，在我之前的印象中，整个后端框架可以很明确的分为三层，分别是web层：jsp页面，也就是我们常说的视图层；service层：业务逻辑层；dao层：数据持久化层，包含一系列对数据的操作，在web层和service层之间有一个转发控制器，控制每个请求转向哪个业务逻辑。

SSM框架里面分工也非常明确，但跟上面介绍的有所不同的是：SSM不关心jsp页面，而是使用Restful接口规范，Controller的工作任务也就从根据页面请求控制转向哪个业务逻辑，变成了针对给定的URL地址给出Handler，也就是基于注解的HandlerMapping (@RequestMapping)，返回数据model和页面view，交付给前端，然后前端工程师再进行页面渲染！

### 前端页面

前端页面流程：

![list.png](https://github.com/ChaoZeyi/seckill/blob/master/images/controller/list.png?raw=true)

详情页面流程：

![detail.png](https://github.com/ChaoZeyi/seckill/blob/master/images/controller/detail.png?raw=true)

#### Restful

是一种接口规范，是一种优雅的URL表述方式，是一种资源的状态和状态转移

GET --> 查询操作

POST --> 添加/修改操作   （非幂等操作）

PUT --> 修改操作   （幂等操作）

通常使用时，两者没有具体差异

DELETE --> 删除操作

**URL设计**：/模块/资源/{标示}/集合1/...

如：/user/{uid}/friends  好友列表

在该项目中的URL设计：

GET	/seckill/list	秒杀列表

GET	/seckill/{id}/detail	秒杀详情页

GET	/seckill/time/now	获取当前时间

POST	/seckill/id/exposure	暴露md5

POST	/seckill/{id}/{md5}/execution	执行秒杀

#### SpringMVC

1. 请求参数绑定
2. 请求方式限制
3. 请求转发和重定向
4. 数据模型赋值
5. 返回json数据
6. cookie访问

#### @RequestMapping

1. 支持标准的URL
2. Ant风格URL（？ * **等）
3. 带占位符{XXXXX}的URL

### 出现的错误：

> java.lang.UnsatisfiedLinkError: G:\ruanjian\tomcat\apache-tomcat-7.0.67\bin\tcnative-1.dll: Can't load AMD 64-bit .dll on a IA 32-bit platform

参考我在个人主页上的[解决办法](http://lucky365.xin/2017/12/12/cant-load-amd-64-bit-dll-on-a-ia-32-bit-platform/)

> 注意相对路径和绝对路径的使用

/工程名/Controller/方法路径 ------》   对应jsp的路径

在list.jsp中 href = "aa"，转向的是/工程名/Controller/aa

​		    href = "/aa"，转向的是/aa

**一定要注意路径的问题，因为各种路径的问题几乎调了一整天，很崩溃！**