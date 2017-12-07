### 设计数据库

根据需求，设计库存表和秒杀成功明细表

### 实体类

一个表对应一个实体类，表的每一列对应实体类的一个属性

### DAO

每个DAO类对应一个表的增删改查，通常是先创建接口，再对它进行实现

### MyBatis

提供两种方式编写SQL

1. **XML形式**
2. 注解形式

推荐在xml文件中编写SQL，有两个原因：注解本身还是java源码，修改之后还是要重新编译类；编写复杂的SQL逻辑时，注解处理起来就会非常繁琐，而xml本身提供了大量的标签，更方便

#### 实现DAO接口

提供了两种方式：

1. **Mapper自动实现**
2. API编程方式实现（比如JDBC）

推荐使用Mapper自动实现，省了我们实现接口的工作，让我们只关注SQL的编写以及如何设计DAO接口

其实MyBatis3已经可以实现，在设计好数据表后，自动生成entity，再根据dao直接利用mapper实现dao接口

### MyBatis编码实现

在resources目录下新建mybatis-config.xml文件，配置mybatis的一些属性

在resources目录下新建mapper文件夹，在文件夹下每个DAO类对应一个xml文件，使用标签来执行SQL语句

### MyBatis与Spring整合

目标：

1. 更少的编码：只写DAO接口，不写实现
2. 更少的配置：DAO接口实现类可以自动注入到spring容器中
3. 足够的灵活性：自己定制SQL

编辑spring-dao.xml

### 单元测试

### 出现的问题

> 	java.lang.NoSuchMethodError:org.springframework.core.annotation.AnnotatedElementUtils.findMergedAnnotationAttributes(Ljava/lang/reflect/AnnotatedElement;Ljava/lang/String;ZZ)Lorg/springframework/core/annotation/AnnotationAttributes;
> 	at org.springframework.test.util.MetaAnnotationUtils$AnnotationDescriptor.<init>(MetaAnnotationUtils.java:290)
> 	at org.springframework.test.util.MetaAnnotationUtils$UntypedAnnotationDescriptor.<init>(MetaAnnotationUtils.java:365)
> 	at org.springframework.test.util.MetaAnnotationUtils$UntypedAnnotationDescriptor.<init>(MetaAnnotationUtils.java:360)
> 	at org.springframework.test.util.MetaAnnotationUtils.findAnnotationDescriptorForTypes(MetaAnnotationUtils.java:191)
> 	at org.springframework.test.util.MetaAnnotationUtils.findAnnotationDescriptorForTypes(MetaAnnotationUtils.java:208)
> 	at org.springframework.test.util.MetaAnnotationUtils.findAnnotationDescriptorForTypes(MetaAnnotationUtils.java:208)
> 	at org.springframework.test.util.MetaAnnotationUtils.findAnnotationDescriptorForTypes(MetaAnnotationUtils.java:208)
> 	at org.springframework.test.util.MetaAnnotationUtils.findAnnotationDescriptorForTypes(MetaAnnotationUtils.java:166)
> 	at org.springframework.test.context.support.AbstractTestContextBootstrapper.buildMergedContextConfiguration(AbstractTestContextBootstrapper.java:274)
> 	at org.springframework.test.context.support.AbstractTestContextBootstrapper.buildTestContext(AbstractTestContextBootstrapper.java:110)
> 	at org.springframework.test.context.TestContextManager.<init>(TestContextManager.java:120)
> 	at org.springframework.test.context.TestContextManager.<init>(TestContextManager.java:105)
> 	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.createTestContextManager(SpringJUnit4ClassRunner.java:154)
> 	at org.springframework.test.context.junit4.SpringJUnit4ClassRunner.<init>(SpringJUnit4ClassRunner.java:145)
> 	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
> 	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
> 	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
> 	at java.lang.reflect.Constructor.newInstance(Constructor.java:408)
> 	at org.junit.internal.builders.AnnotatedBuilder.buildRunner(AnnotatedBuilder.java:104)
> 	at org.junit.internal.builders.AnnotatedBuilder.runnerForClass(AnnotatedBuilder.java:86)
> 	at org.junit.runners.model.RunnerBuilder.safeRunnerForClass(RunnerBuilder.java:59)
> 	at org.junit.internal.builders.AllDefaultPossibilitiesBuilder.runnerForClass(AllDefaultPossibilitiesBuilder.java:26)
> 	at org.junit.runners.model.RunnerBuilder.safeRunnerForClass(RunnerBuilder.java:59)
> 	at org.junit.internal.requests.ClassRequest.getRunner(ClassRequest.java:33)
> 	at org.eclipse.jdt.internal.junit4.runner.JUnit4TestLoader.createUnfilteredTest(JUnit4TestLoader.java:84)
> 	at org.eclipse.jdt.internal.junit4.runner.JUnit4TestLoader.createTest(JUnit4TestLoader.java:70)
> 	at org.eclipse.jdt.internal.junit4.runner.JUnit4TestLoader.loadTests(JUnit4TestLoader.java:43)
> 	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:444)
> 	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:675)
> 	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:382)
> 	at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:192)

问题原因：org.springframework其他依赖的版本与spring-test的版本不一致，全部改为一样就好了

> Exception in thread "main" java.lang.AbstractMethodError: org.mybatis.spring.transaction.SpringManagedTransaction.getTimeout()Ljava/lang/Integer;
> at org.apache.ibatis.executor.SimpleExecutor.prepareStatement(SimpleExecutor.java:85)
> at org.apache.ibatis.executor.SimpleExecutor.doQuery(SimpleExecutor.java:62)
> at org.apache.ibatis.executor.BaseExecutor.queryFromDatabase(BaseExecutor.java:325)
> at org.apache.ibatis.executor.BaseExecutor.query(BaseExecutor.java:156)
> at org.apache.ibatis.executor.CachingExecutor.query(CachingExecutor.java:109)
> at org.apache.ibatis.executor.CachingExecutor.query(CachingExecutor.java:83)
> at org.apache.ibatis.session.defaults.DefaultSqlSession.selectList(DefaultSqlSession.java:148)
> at org.apache.ibatis.session.defaults.DefaultSqlSession.selectList(DefaultSqlSession.java:141)
> at org.apache.ibatis.session.defaults.DefaultSqlSession.selectOne(DefaultSqlSession.java:77)
> at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
> at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
> at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
> at java.lang.reflect.Method.invoke(Unknown Source)
> at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:358)
> at com.sun.proxy.$Proxy8.selectOne(Unknown Source)
> at org.mybatis.spring.SqlSessionTemplate.selectOne(SqlSessionTemplate.java:163)
> at com.platform.core.dao.DaoImpl.get(DaoImpl.java:35)
> at com.platform.app.dao.SysUsersDAOImpl.checkUser(SysUsersDAOImpl.java:23)
> at com.test.Test.main(Test.Java:30)

问题原因：mybatis的依赖版本与mybatis-spring的依赖版本不匹配，mybatis改为3.4.0，mybatis-spring改为1.3.1就好了

> 返回的结果集一直为0

问题原因：查看网上的解决方法，说的都是因为数据库字段和实体类变量不一致，但我排查之后发现不存在这个情况，而且SQL语句确定没有语法错误，最后把原因确定为：比如数据表字段为product_id，实体类中变量也是product_id，并且在mybatis配置中开启了驼峰转换

<setting name="mapUnderscoreToCamelCase" value="true"/>

所以数据表字段会被字段转换成productId，无法与实体类中的product_id变量匹配。所以可以关闭驼峰转换，或者修改实体类中的命名。