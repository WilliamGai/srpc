package org.srpc.http.utils;

import org.sonic.rpc.core.LogCore;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component  
public class SpringUtil implements ApplicationContextAware {  
    private static ApplicationContext context = null;  
    @Override  
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {  
        context = applicationContext;  
        LogCore.BASE.info("init globle context:{}", context.getClass());
    }  
    public static ApplicationContext getContext() {  
        return context;  
    }  
}  
