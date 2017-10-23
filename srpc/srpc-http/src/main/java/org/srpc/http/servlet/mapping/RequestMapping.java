package org.srpc.http.servlet.mapping;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface RequestMapping {//RequestMappingHandlerMapping

	String name() default "";

	@AliasFor("path")
	String[] value() default {};

	@AliasFor("value")
	String[] path() default {};

	RequestMethod[] method() default {};

	String[] params() default {};

	String[] headers() default {};

	String[] consumes() default {};

	String[] produces() default {};

}
