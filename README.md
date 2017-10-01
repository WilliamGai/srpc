# srpc即sonic-rpc
## 1. 介绍 
rpc是基于Netty + Springboot + FastJson + zookeeper的简单实现

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

使用方法
1.一个api里的接口,在服务提供者那边只能有1个类去实现，为了简化开发，接口和实现类是1:1

# srpc
