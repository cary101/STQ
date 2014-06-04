/**
 * 
 */
package com.cary.scheduler.core;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 任务描述
 * @author liuk
 * 
 */
@Target({ java.lang.annotation.ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TaskBean {
	public abstract String description() default "";

	public abstract String cron() default "";
}
