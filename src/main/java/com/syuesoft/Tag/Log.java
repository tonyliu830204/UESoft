package com.syuesoft.Tag;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Log{
	
    // 模块名
    String systemName() default "";
    // 模块名
    String moduleName() default "";
    // 描述
    String content() default "";
    // 操作类型
    String opertype() default "";
    
}