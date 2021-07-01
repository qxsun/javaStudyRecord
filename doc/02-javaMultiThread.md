#java多线程知识学习
>本文为书籍《java并发编程的艺术》的阅读简单记录，不算笔记。
##java并发的底层实现原理
>volatile的应用    
*  volatile修饰的变量在写操作的时候，编译器会给加上lock指令，lock指令的主要功能是：
   + （1）将当前处理器[CPU]的缓存行的数据写回到系统内存；
   + （2）使其他处理器的缓存了该内存地址的数据无效。 
>synchronized的实现原理与应用 
*  synchronized同步主要分为同步代码块，跟方法同步  
   + （1） 代码块同步使用monitorenter,monitorexit两个指令来实现； 
   + （2） 方法同步的实现，据说也是这两个指令，只是跟代码块细节不一样？？？？ 遗留问题 ,依靠方法修饰符上的ACC_SYNCHRONIZED来完成的
   + （3） 任何对象都有一个monitor与之对应，当线程执行到该代码段（或者说对象，方法）的monitorenter指令会尝试获取此段代码对应的monitor所有权
*  Java对象头  
   + （1） synchronized用的锁是存在Java对象头里的 
   + （2） CAS是什么？
   + （3） 偏向锁，轻量锁，重量锁 需要搞清楚？
* 原子操作的实现原理 
   + （1） CAS compara and swap 比较并交换，就是缓存更新到内存的时候，会先比较旧的值跟自己的是否一致，如果不一致就不更新，因为这样说明已经有其他线程改变了
   + （2） 处理器保证原子性：总线锁，独占共享内存； 缓存锁
   + （3） java通过循环CAS和锁来实现原子操作，CAS 三大问题：aba问题；循环时间长开销大；只能保证一个共享变量的原子操作
------
##Java内存模型
>Java内存模型的基础  
*  并发编程的两个关键问题  
   + 线程之间的通信
   + 线程之间的同步
* 重排序
   +  简单过了一下
* 顺序一致性
   +  简单过了一下
* volatile的内存语义 
   + volatile变量特性：可见性，一个volatile变量的读，总是能看到（任意线程）对这个volatile变量最后的写入；
   原子性，对任意耽搁volatile变量的读写具有原子性
   + 内存语义的实现  
     - （1）在每个volatile写操作的前面插入一个StoreStore屏障
     - （2，3，4） 。。。 具体见书籍，就是通过屏障实现内存语义
* 锁的内存语义
   + AQS AbstractQueuedSynchronizer 一个java同步器框架，使用一个整形的volatile变量（名字是state）来维护同步状态
* final域的内存语义 
   + 写final域的重排序规则禁止把final域的写重排序到构造函数之外  
     - （1）JMM禁止编译器把final域的写重排序到构造函数之外
     - （2）编译器会在final域的写之后，构造函数的return之前，插入一个StoreStore屏障，这个屏障禁止处理器把final域的写重排序到构造函数之外。 
     - （3）关于final域是引用类型，写final域的约束为，在构造函数内对一个final引用的对象的成员域的写入，与随后在构造函数外把这个被构造对象的引用赋值给一个引用变量，这两个操作之间不能重排序
   + 读final域的重排序规则   
      -  （1）在一个线程中，初次读对象引用与初次读改对象包含的final域，JMM禁止处理器重排序这两个操作，编译器会在读final域操作的前面插入一个LoadLoad屏障
   + 上述规则需要保证：在构造函数内部，不能让这个被构造对象的引用为其他线程所见
* happens-before
    + 
* 双重检查锁定与延迟初始化
    +  
* java内存模型综述
    + 
--------------
##Java并发编程基础 
>线程简介 
* 线程状态
    + 线程状态有 NEW RUNNABLE BLOCKED WAITING TIME_WAITING TERMINATED 六种,其中WAITING TIME_WAITING两种的区别在于TIME_WAITING指定了具体的时间，进入等待状态的线程需要依靠其他线程的通知才能够返回到运行状态，而超时等待状态相当于在等待状态的基础上增加了超时限制。
* Daemon线程  
    +  一种支持型线程，
>并发编程基础
* 安全地终止线程
    + 
>线程间通信     
*  volatile和synchronized关键字 
    + 
*  等待/通知机制:相关函数都是超类Object自带的方法
    + notify() 通知一个在对象上等待的线程，使其从wait()方法返回，返回的前提是该线程获得了对象的锁
    + wait() 调用该方法的线程进入WAITING状态，只有等待另外线程的通知或被中断才会返回。需要注意的是，调用wait()方法后，**会释放对象的锁**  
    + 其他比如notifyAll(),wait(long),wait(long,int)衍生方法
*  管道输入/输出流  
   管道输入/输出流和普通文件以及网络的输入输出流的不同之处在于，它主要用于线程之间的数据传输，而传输的媒介为内存。  
   + 管道输入/输出流主要包括了如下4种具体实现：PipedOutputStream、PipedInputStream、PipedReader和PipedWriter，前两种面向**字节**，而后两种面向**字符**。
* Thread.join()的使用  
  如果一个线程A执行了thread.join()语句，其含义是：当前线程A等待thread线程终止之后才从thread.join()返回。
* ThreadLocal的使用  
    + 
---
##Java中的锁
> 队列同步器     
*  队列同步器AbstractQueuedSynchronizer
    + AQS是用来构建锁或者其他同步组件的基础框架，主要的同步组件有ReentrantLock,ReentrantReadWriteLock,CountDownLatch等
*  队列同步器的实现分析  
    + 独占式同步状态获取和释放过程：在获取同步状态时，同步器维护一个同步队列，获取状态失败的线程都在被加入到队列中并在队列中进行自旋；移出队列（或者停止自旋）的条件是前驱节点为头节点并且成功获取了同步状态。在释放同步状态时，同步器调用tryRelease（int arg）方法释放同步状态，然后唤醒头节点的后继节点。
    + 共享式同步状态获取和释放：...
    + 独占式超时获取同步状态：...
    + 自定义组件-TwinsLock：一个示例
*  重入锁  
    + 实现重进入：
    + 公平与非公平获取锁的区别：公平的顺序按照同步器的fifo顺序，非公平只要CAS设置同步状态成功，则表示当前线程获取了锁；公平锁保证fifo原则，代价是大量的线程切换，非公平锁造成了饥饿，但是保证了更大的吞吐量。 
*  LockSupport工具
*  Condition接口  
    + 
    + Condition的实现分析 ConditionObject是同步器AbstractQueuedSynchronizer的内部类，因为Condition的操作需要获取相关联的锁，所以作为同步器的内部类也较为合理。每个Condition对象都包含着一个队列（以下称为等待队列），该队列是Condition对象实现等待/通知功能的关键。
 ----------------
 ##Java并发容器和框架   
> ConcurrentHashMap的实现原理与使用  
*  使用ConcurrentHashMap的理由
    +  ConcurrentHashMap使用锁分段技术，可以保证并发，同时提高并发访问效率
*  ConcurrentHashMap的结构
    +  ConcurrentHashMap由Segment数组结构和HashEntry数组结构组成，一个ConcurrentHashMap里面包含着一个Segment数组，其是一种可冲入锁，在ConcurrentHashMap中扮演着锁的角色，每个Segment包含一个HashEntry数组，Segment守护着一个HashEntry数组里面的元素。
*  ConcurrentHashMap的初始化  
*  ConcurrentHashMap中定位Segment
*  ConcurrentHashMap的操作
> ConcurrentLinkedQueue   
*  定义：ConcurrentLinkedQueue是一个基于链接节点的无界线程安全队列，它采用先进先出的规
  则对节点进行排序，当我们添加一个元素的时候，它会添加到队列的尾部；当我们获取一个元
  素时，它会返回队列头部的元素。它采用了“wait-free”算法（即CAS算法）来实现，该算法在
  Michael&Scott算法上进行了一些修改。
*  入队列：入队列就是将入队节点添加到队列的尾部
    + 
*  出队列：
> Fork/Join框架
*  内容有点水
----------------------
##Java中的13个原子操作类(内容不多，主要介绍了13各类的基础使用)
> 原子更新基本类型类  
* 
> 原子更新数组 

> 原子更新引用类型 
 
> 原子更新字段类  
---------------
##Java中的并发工具类
> 等待多线程完成的CountDownlatch  
----------
##Java中的线程池
> 线程池的实现原理  
* 
---------
##Executor框架
> Executor框架简介
* 结构  
    + 任务。包括被执行任务需要实现的接口：Runnable接口或Callable接口
    +  任务的执行。包括任务执行机制的核心接口Executor，以及继承自Executor的
       ExecutorService接口。Executor框架有两个关键类实现了ExecutorService接口
       （ThreadPoolExecutor和ScheduledThreadPoolExecutor）。
    +  异步计算的结果。包括接口Future和实现Future接口的FutureTask类。
> ThreadPoolExecutor详解
* FixedThreadPool详解 固定线程数的线程池
     + 
> FutureTask详解 
* FutureTask简介
    + FutureTask除了实现Future接口外，还实现了Runnable.因此，FutureTask可以交给Executor执行，也可以由调用线程直接执行（FutureTask.run()）。
    根据FutureTask.run()方法被执行的时机，FutureTask可以处于三种状态：未启动，已启动，已完成。
-------------
##Java并发编程实践
>生产者和消费者模式
* 生产者和消费者模式  
    + 生产者和消费者模式是通过一个容器来解决生产者和消费者的强耦合问题。阻塞队列用来给生产者和消费者解耦。纵观大多数设计模式，都会找一个第三者进行解耦，例如工厂模式
    的第三者是工厂类，模板模式的第三者是模板类。
    + 多生产者和多消费者使用场景：
    + 线程池与生产消费者模式：
>线上问题定位：
* 很多问题只有在线上或者预发环境才能发现，而此时不能调试代码，所以线上问题定位就只能看日志，系统日志和dunp线程，本节主要介绍一些工具  
    + top命令
    + jstat
    + jstack
>性能测试
* 
>异步任务池
    

    
    


