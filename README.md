# SSM实现高并发秒杀系统

SSM指的是Spring+SpringMVC+MyBatis整合框架

该项目源于[慕课网](https://www.imooc.com/)上的一个课程，使用SSM框架实现高并发秒杀系统。此外还涉及到的技术有：。

**为什么选择SSM框架：**

1. 框架易于使用和轻量级
2. 低代码侵入性
3. 成熟的社区和用户群

**为什么选择秒杀类业务场景：**

1. 具有典型事物特性
2. 秒杀/红包类需求越来越常见

该系列课程主要分为四个子课程，在慕课网上都可以搜到：

1. [Java高并发秒杀API之业务分析与DAO层](https://www.imooc.com/view/587)
2. [Java高并发秒杀API之Service层](https://www.imooc.com/view/631)
3. [Java高并发秒杀API之web层](https://www.imooc.com/view/630)
4. [Java高并发秒杀API之高并发优化](https://www.imooc.com/view/632)

 项目效果图如下：



下面开始介绍如何一步步从零开始搭建该项目。

## 项目环境搭建

- **操作系统**：Windows7
- **Java IDE**：IntelliJ IDEA Ultimate 2017.1.5
- **JDK**：1.7.0_79
- **数据库**：MySql 5.7
- **项目管理工具**：Maven 3.5.2

打开IDEA，新建工程 new project

![新建工程](https://github.com/ChaoZeyi/seckill/blob/master/images/newProject.png?raw=true)

使用maven项目管理工具

![maven](https://github.com/ChaoZeyi/seckill/blob/master/images/newMaven.png?raw=true)

根据自己的习惯，设置GroupId

![groupId](https://github.com/ChaoZeyi/seckill/blob/master/images/groupId.png?raw=true)

选择Maven版本及地址

![maven](https://github.com/ChaoZeyi/seckill/blob/master/images/mavenLocation.png?raw=true)

选择项目名和项目路径

![projectFinish](https://github.com/ChaoZeyi/seckill/blob/master/images/projectName.png?raw=true)

点击finish就完成了！

这时候Maven默认生成的文件目录可能并不全，我们需要open module settings >> modules 补全文件。最后的项目文件目录如下：

![projectStructure](https://github.com/ChaoZeyi/seckill/blob/master/images/projectStructure.png?raw=true)

首先，我们可以看一下pom.xml文件，这是Maven工程自带的，用来添加第三包jar包的依赖信息，这就免了我们手动导jar包的工作，非常方便。在后续的编码步骤，根据需要，会慢慢补充该文件！

## 项目业务分析

![system](https://github.com/ChaoZeyi/seckill/blob/master/images/system.png?raw=true)

整个系统都依赖于对库存的处理，商家可以调整库存大小，根据库存信息来核对发货情况；用户可以通过秒杀行为来减少库存，退款行为会来增加库存。

在一个完整的秒杀过程中，主要做两件事情，减库存、记录购买明细，这两个事情组成一个完整的事务。

![transaction](https://github.com/ChaoZeyi/seckill/blob/master/images/transaction.png?raw=true)

**为什么我们的系统需要事务？**看如下这些故障:

1.若是用户成功秒杀商品我们记录了其购买明细却没有减库存。导致商品的超卖

2.减了库存却没有记录用户的购买明细。导致商品的少卖

对于上述两个故障，若是没有事务的支持，损失最大的无疑是我们的用户和商家

整个项目涉及到两个表：库存表和用户秒杀信息表

**库存表**主要包含商品Id，商品名称，商品库存，商品开始秒杀时间，商品结束秒杀时间，秒杀行为创建时间等字段

**用户秒杀信息表**主要包含商品Id，用户信息（手机号），秒杀行为创建时间，秒杀状态

为了防止一个用户对同一商品进行多次秒杀，可以考虑建立唯一联合主键（用户信息和商品Id）

## 项目编码步骤

一般的步骤：项目建立--配置依赖--dao-service-controller--单元测试--异常检测--日志统一

按照四个子课程分开进行总结

- [Java高并发秒杀API之DAO层](https://github.com/ChaoZeyi/seckill/blob/master/notes/DAO.md)
- [Java高并发秒杀API之Service层](https://github.com/ChaoZeyi/seckill/blob/master/notes/Service.md)
- [Java高并发秒杀API之web层](https://github.com/ChaoZeyi/seckill/blob/master/notes/web.md)
- [Java高并发秒杀API之高并发优化](https://github.com/ChaoZeyi/seckill/blob/master/notes/Optimization.md)
