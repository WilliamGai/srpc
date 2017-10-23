# SRPC
## SRPC即sonic-rpc
## 1. 介绍 
SRPC是基于Netty + Springboot(仅IOC) + FastJson + Zookeeper的最简单实现，可以轻松扩展成自己需要的RPC实现或者作为学习使用

## 2. 项目结构
``` xml
srpc-parent
    |
    |
    |-----srpc(所有核心组件)
    |		|
    |		|----srpc-core(核心代码)
    |		|
    |		|----srpc-consumer
    |		|
    |		|----srpc-provider
    |
    |-----srpc-excample(使用示例)
    		|
    		|----srpc-excample-api
    		|
    		|----srpc-excample-consumer
    		|	
    		|----srpc-excample-provider
    
```





使用方法:  
服务提供者引入srpc-provider，将自己的服务用@SService注解标注  
消费者引入srpc-consumer,将自己的属性添加注解@SReference即可  
具体参考srpc-example的例子

学习交流请联系 346126185@qq.com

其他:  
@SService和@SReference均不依赖Spring