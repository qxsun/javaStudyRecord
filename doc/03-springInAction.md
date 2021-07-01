#spring实战第四版
##Spring核心
###第一章 Spring之旅
> 简化java开发 
 * 依赖注入
    + 构造器注入（constructor injection）是依赖注入的一种方式。
 * 应用切面
    + 面向切面编程(aspect-oriented programming,AOP)允许你把遍布应用各处的功能分离出来形成可重用的组件。
 * 使用模板消除样式代码
    + 样板似的代码(boilerplate code) JDBC典型的样板似代码
> 容纳你的bean
 * 容器是Spring框架的核心
    + Spring容器使用DI管理构成应用的组件，它会创建相互协作的组件之间的关联
    + bean工厂：BeanFactory是最简单的容器，提供基本的DI支持，
    + 应用上下文（ApplicationContext接口定义）基于BeanFactory构建，并提供应用框架级别的服务，例如从属性文件解析文本信息以及发布应用事件给感兴趣的事件监听者。
    + bean工厂过于低级（基础），一般使用上下文
 * 使用应用上下文：Spring自带了多种类型的应用上下文
    + AnnotationConfigApplicationContext:从一个或者多个基于Java的配置类中加载Spring应用上下文；
    + AnnotationConfigWebApplicationContext:从一个或者多个基于Java的配置类中加载Spring Web应用上下文；
    + ClassPathXmlApplicationContext:从类路径下的一个或者多个xml配置文件中加载上下文定义，把应用上下文的定义文件作为类资源；
    + FileSystemXmlApplicationContext:从文件系统下的一个或者多个xml配置文件中加载上下文定义，把应用上下文的定义文件作为类资源；
    + XmlWebApplicationContext:从Web应用下的一个或者多个xml配置文件中加载上下文定义，把应用上下文的定义文件作为类资源
 * bean的生命周期
    + 1 spring对bean进行实例化
    + 2 spring将值和bean的引用注入到bean对应的属性中
    + 3 如果bean实现了BeanNameAware接口，spring将bean的ID传递给setBean-Name()方法；
    + 4 如果bean实现了BeanFactoryAware接口，spring将调用setBeanFactory()方法，将BeanFactory容器示例传入
    + 5 如果bean实现了ApplicationContextAware接口，spring将调用setApplicationContext()方法，将bean所在的应用上下文的引用传入进来；
    + 6 如果bean实现了BeanPostProcessor接口,调用PostProcessorBeforeInitialization()方法
    + 7 如果bean实现了InitializingBean接口，Spring将调用它们的afterPropertiesSet()方法。类似地，如果bean使用init-method声明了初始化方法，该方法也会被调用；
    + 8 如果bean实现了BeanPostProcessor接口,调用PostProcessorAfterInitialization()方法
    + 9 此时，bean已经准备就绪，可以使用直到应用上下文被销毁
    + 10 如果bean实现了DisposableBean接口，将调用它的destory()接口方法。同样，如果bean使用destory-method声明了销毁方法，该方法也会被调用。
> 俯瞰Spring风景线
 * Spring蓝图
    + Spring核心容器：管理Spring应用中bean的创建，配置和管理，包括了Spring bean工厂
    + Spring的AOP模块：
    + 数据访问与集成
    + Web与远程调用
    + Instrumentation：提供了JVM添加代理(agent)功能
 * Spring Portfolio
> Spring的新功能
 * 
 -----------------
 ###第二章 装配bean
> Spring可选的配置方案
 + 
> 自动化装备bean：Spring从两个角度来实现自动化装配，组件扫描和自动装配
 * 创建可被发现的bean 
    + @component
    + @componentScan
 * 为组件扫描的bean命名
    + @component("beanName") 可以指定bean的名字，或者默认是类名小写开头的驼峰风格名字
 * 设置组件扫描的基础包
    + @componentScan(basePackages={"beanName","baseName2"}) 或者有属性 basePackageClasses
 * 通过为bean添加注解实现自动装配
    + @Autowired注解不仅能够用在构造器上，还能用在属性的Setter方法上，它也可以用在类的任何方法上。在找不到或者找到多个匹配的bean的时候，都会抛异常。
    + @Autowired注解正常不设置属性，如果不设置，在自动转配的时候找不到会抛异常，为了避免抛异常，可设置(required=false)属性，但是这样可能会带来空指针问题，要注意。
  * 验证自动装配
    +    
> 通过Java代码装配bean  
 首选通过自动化配置来装配bean，但是有些时候需要明确配置Spring，比如装配第三方库中的组件，没法在组件上加@Component和@Autowired，需要通过java或者xml显示配置。
 * 创建配置类
    + @Configuration 注解的是JavaConfig类，表明该类是一个配置类
 * 声明简单的bean
    + @Bean注解会告诉Spring这个方法将会返回一个对象，该对象要注册为Spring应用上下文的bean，方法体中包含了最终产生bean示例的逻辑。
    默认情况下，bean的ID与带有@Bean注解的方法名是一样的，也可以通过name属性设置名字
 * 借助JavaConfig实现注入
    + @Bean注解
> 通过XML装配bean
  * 简单熟悉即可，现在基本不用这种方式了，只是老代码还是这种
---------------
###第三章 高级装配
> 环境与profile
 * 配置profile bean
    + dev test prod 三种环境进行设置
 * 激活profile
    + spring.profiles.active和spring.profiles.default
    + 当设置了spring.profiles.active后，会采用这个值，不会用default值
> 条件化的bean
 * Spring4 引入了一个新的@Conditional注解，可以用于当满足某些条件才创建一些bean
 * @Profile本身也使用了@Conditional注解
> 处理自动装配的歧义性:自动装配时，对于属性setter方法的装配过程，如果入参是接口，可能匹配到过个实现类，这时候会报NoUniqueBeanDefinitionException异常
 * 标示首选的bean
    + @Primary + @Component 结合，或者@Primary + @Bean 结合，注意同一个接口的实现类，不能设置多个首选的，不然同样会歧义
 * 限定自动装配的bean
    + @Qualifier注解是使用限定符的主要方式，它可以与@Autowired和@Inject协同使用。
    + 利用@Qualifier注解定义自定义注解
> bean的作用域  
 * Spring定义了多种作用域，可以基于这些作用域创建bean，包括：
    + 单例Singleton：在整个应用中，只创建bean的一个实例，适用于不变的类，是默认的作用域
    + 原型Prototype: 每次注入或者通过Spring应用上下文获取的时候，都会创建一个新的bean实例
    + 会话Session：在Web应用中，为每个会话创建一个bean实例
    + 请求Request： 在Web应用中，为每个请求创建一个bean实例
 * @Scope注解和@Bean，@component一起使用来指定作用域 @Scope("prototype")
 * 使用会话和请求作用域
    + 主要涉及到一个proxyMode属性的设置， 是代理的接口ScopedProxyMode.INTERFACE还是实现类ScopedProxyMode.TARGET_CLASS.
> 运行时值注入： 
 * 为了让一些值在运行时再确定，Spring提供了两种在运行时求值的方式,这两种技术用法类似，目的和行为有差别，占位符简单点，表达式语言复杂。
    + 属性占位符(Property placeholder) 
    + Spring表达式语言(SpEL)
 * 注入外部的值：关键在于根据名称解析来自于Environment和属性源的属性
    + 最简单的方式是：声明属性源(@PropertySource注解)并通过Spring的Environment来检索属性 
    + 深入学习Spring的Environment：getProperty()方法并不是获取属性值的唯一方法，它有四种重载形式
        - String getProperty(String key)  
        - String getProperty(String key, String defaultValue)   设置默认值
        - T getProperty(String key, Class<T> type) 指定值读取的类型
        - T getProperty(String key, Class<T> type, T defaultValue)  
    + Environment还有其他一些方法
    + 解析属性占位符，
        - Spring一直支持将属性定义到外部的属性的文件中，并使用占位符值将其插入到bean中，占位符形式为
    "${ ... }",自动装配使用@Value注解和占位符配合使用。
        - 为了使用占位符，我们必须要配置一个PropertySourcePlaceholderConfigurer bean,因为它能够基于Environment及其属性源来解析占位符。
 * 使用Spring表达式语言进行装配 
    + SpEL样例 
        - SpEL表达式需要放到"#{ ... }"之中
        - \#{T(System).currentTimeMills()} 表示计算表达式那一刻当前时间的毫秒数，T()表达式会将System视为java中对应的类
        - 内容很丰富，具体可以看书，或者自己查
------------
### 第四章 面向切面的Spring
> 什么是面向切面编程
 * 定义aop术语
    + 通知(Advice)：定义了切面是什么以及何时使用，有5种
        - 前置通知Before ：目标方法调用前执行
        - 后置通知After ： 目标方法调用后，不关心返回输出
        - 返回通知After-returning ：目标方法成功执行后调用
        - 异常通知After-throwing ： 目标方法抛出异常后调用执行
        - 环绕通知Around ：目标方法执行之前之后调用自定义行为
    + 连接点(Join point): 是在应用执行过程中能够插入切面的一个点，可以是调用方法时，抛出异常时，甚至修改一个字段时。
    + 切点(Pointcut): 切点的定义会匹配通知索要织入的一个或者多个连接点
    + 切面(Aspect): 通知和切点的结合，通知和切点共同定义了切面的全部内容，它是什么，在何时和何处完成其功能。
    + 引入(Introduction): 允许我们向现有的类添加新方法或属性
    + 织入(Weaving): 织入是把切面应用到目标对象并创建新的代理对象的过程，织入的点有：
        - 编译器：需要特殊的编译器，AspectJ织入就是这种
        - 类加载期：切面在目标类加载到JVM时被织入，需要特殊的类加载期，AspectJ5是这种方式
        - 运行期：切面在应用运行的某个时刻被织入。AOP容器会为目标对象动态地创建一个代理对象，Spring AOP就是以这种方式织入切面的。
 * Spring对AOP的支持
    + Spring AOP框架的关键知识
        - Spring通知是Java编写的：标准Java类编写，而且通知所应用的切点通常会使用注解或者XML
        - Spring在运行时通知对象：
        - Spring只支持方法级别的连接点：方法拦截之外的其他需求，可以通过AspectJ来补充
> 通过切点来选择连接点
 * 编写切点
    + 切点表达式 execution(* consert.Performance.perform(..)) && within(concert.*) 表名执行perform方法且在concert包下面的任意方法被调用时
 * 在切点中选择bean
    + bean()使用beanID或者bean名称作为参数来限制切点只匹配特定的bean
    
> 使用注解创建切面
 *  定义切面
    + 
 *  环绕通知 
 *  处理通知种的参数
    + 
 *  通过注解引入新功能:切面可以为Spring bean添加新方法
    +      
> 在XML中声明切面
 * 好处是XML不用给通知方法加注入，适用于无通知源码的场景
> 注入AspectJ切面
 * 
 ---------
 ---------
 ##第二部分 Web中的Spring
 ### 构建Spring Web应用程序
> Spring MVC起步
 * 跟踪Spring MVC的请求
    + DispatcherServlet
 * 搭建Spring MVC
    + AbstractAnnotationConfigDispatcherServletInitializer剖析
        - 扩展AbstractAnnotationConfigDispatcherServletInitializer的任意类都会自动地配置DispatcherServlet和Spring的应用上下文
> 编写基本的控制器
 * 常用注解
    + @Controller 声明控制器，跟Component类似
    + @RequestMapping("value") 定义映射路径
> 接受请求的输入   
 * 常用注解
    + @RequestParam注解可以记录请求参数
    + 路径占位符 和 @PathVariable 搭配使用 
> 处理表单
 * 编写处理表单的控制器
    + 在处理POST类型的请求时，在请求处理完成后，最好重定向一下，这样浏览器的刷新就不会重复提交表单了。
    +
 * 校验表单
    + Spring对Java校验API（Java Validation API,又称JSR-303)提供支持，定义的注解都位于javax.validation.constraints包中
----
### 渲染Web视图
> 理解视图解析
 * 将控制器中请求处理的逻辑和视图中的渲染实现解耦是Spring MVC的一个重要特性
> 创建JSP视图
 * Spring提供了两种支持JSP视图的方式
    + InternalResourceViewResolver将视图名解析为JSP文件
    + 如果在JSP页面中使用了JSP标准标签库(JSTL)
 * 配置适用于JSP的视图解析器
 * 使用Spring的JSP库
> 使用Apache Tiles视图定义布局
 * 
---
### Spring mvc的高级技术
> 处理multipart形式的数据
 * 配置multipart解析器
    + 
> 处理异常
 * 将异常映射为HTTP状态码
> 为控制器添加通知
 * @ControllerAdvice 注解
> 跨重定向请求传递数据
 * 
---
### 使用Spring Web Flow
### 保护Web应用 Spring Security 这个组件
上述两个章节，暂时不看了
-------
## 第三部分 后端中的Spring
### 通过Spring和JDBC征服数据库
> Spring的数据访问哲学
 * 前言
    + DAO data access object 数据访问对象
 * 了解Spring的数据访问异常体系
    + 可能导致SQLException的常见问题包括：
        - 应用程序无法连接数据库；
        - 要执行的查询存在语法错误
        - 查询中所使用的的表或者列不存在
        - 试图插入或者更新的数据违反了数据库约束
    + Spring所提供的平台无关的持久化异常
        - 问题是JDBC提供的异常过于简单，Hibernate提供的异常会侵入到应用程序的其他部分
        - Spring提供的异常台体系并没有与特定的持久化方式关联，我们可以使用Spring抛出一致的异常，有助于将所选择的持久化机制与数据访问层隔离开来
        - Spring的所有异常都继承自DataAccessException,它是一个非检查型异常，也就是没有必要捕获这个异常
 * 数据访问模板化
    +  模板方法模式：定义过程的主要框架，模板方法将过程中与特定实现相关的部分委托给接口，而这个接口的不同实现定义了过程中的具体行为。
    +  Spring将数据访问过程中固定的和可变的部分明确划分为两个不同的类：模板（template）和回调（callback），模板管理过程中固定的部分，而回调处理自定义的数据访问代码。
    +  针对不同的持久化平台，Spring提供了多个可选的模板，直接使用JDBC，选择JdbcTemplate，选择关系映射框架，有HibernateTemplate和JpaTemplate
> 配置数据源
 * 前言：Spring提供了在Spring上下文中配置数据源bean的多种方式，包括
    + 通过JDBC驱动程序定义的数据源
    + 通过JNDI查找的数据源
    + 连接池的数据源
 * 使用JNDI数据源
    +  使用jee命名空间下的<jee:jndi-lookup>
    +  java配置方法，借助JndiObjectFactoryBean从JNDI中查找DataSource
 * 使用数据源连接池
    + Spring没有提供数据源连接池实现， 但是有很多第三方的方案
 * 基于JDBC驱动的数据源
    + Spring中，通过JDBC驱动定义数据源是最简单的配置方式，Spring提供了三个这样的数据源类，均位于org.springframework.jdbc.datasource中
    与具备池功能的数据源相比，唯一的区别是这些实现的数据源bean都没有提供连接池功能
        - DriverManagerDataSource 每次请求返回一个新建的连接
        - SimpleDriverDataSource 类似上面
        - SingleConnectionDataSource 每次返回同一个连接
 * 使用嵌入式的数据源 embeded database
    +  适用于开发环境，数据库作为应用的一部分       
 * 使用profile选择数据源
    +  对于开发，测试，生产系统分别配置不同的数据源 
> 在Spring中使用JDBC
 * JDBC给用户提供了更底层的访问数据库的方法
 * 应对失控的JDBC代码
    +  需要大量的重复的建立连接，清理资源的代码，很冗余但很重要
 * 使用JDBC模板
    + JdbcTemplate：最基本的Spring JDBC模板，这个模板支持简单的JDBC数据库访问功能以及基于索引参数的查询
    + NamedParameterJdbcTemplate：需要使用命名参数的时候，使用。
---
### 使用对象关系映射持久化
> 前言
 * 对象/关系映射 object-relational mapping 
> 在Spring中集成Hibernate
 * 声明Hibernate的Session工厂
    + 主要接口是org.hibernate.Session,提供了基本的数据访问功能，如保存，更新，删除，查询等
    + 获取Session对象的标准方式是借助Hibernate SessionFactory接口的实现类，主要负责Session的打开，关闭以及管理
    + 我们要通过Spring的某一个HibernateSession工厂bean来获取Hibernate SessionFactory
 * 构建不依赖于Spring的Hibernate代码
> Spring与Java持久化API
 * 配置实体管理器工厂
    
  
     