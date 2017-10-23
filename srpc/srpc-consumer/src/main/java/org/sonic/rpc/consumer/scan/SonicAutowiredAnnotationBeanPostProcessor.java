package org.sonic.rpc.consumer.scan;

import org.sonic.rpc.core.annotation.SReference;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 自定义IOC {@link CommonAnnotationBeanPostProcessor} 是注入Resource.class的 <br>
 * {@link AutowiredAnnotationBeanPostProcessor} 是注入Autowired.class 和Value.class的 <br>
 * Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<Class<? extends Annotation>>();<br>
 */
@Component
public class SonicAutowiredAnnotationBeanPostProcessor extends AutowiredAnnotationBeanPostProcessor {
	public SonicAutowiredAnnotationBeanPostProcessor() {
		super();
		setAutowiredAnnotationType(SReference.class);// 用来设置自定义注解
	}
}
