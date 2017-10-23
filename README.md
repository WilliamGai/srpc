# srpc即sonic-rpc
## 1. 介绍 
rpc是基于Netty + Springboot(仅IOC) + FastJson + Zookeeper的简单实现

## 2. 项目结构
``` xml
srpc-parent
    |
    |
    |-----srpc(所有核心组件)
    |		|
    |		|----srpc-core
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



学习交流请联系 346126185@.com

使用方法:
服务提供者引入srpc-provider，将自己的服务用SService注解标注
消费者引入srpc-consumer,将自己的属性添加注解@SReference即可
具体参考srpc-example的例子

# srpc
