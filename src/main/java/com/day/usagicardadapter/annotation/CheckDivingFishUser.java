package com.day.usagicardadapter.annotation;

import com.day.usagicardadapter.filter.CheckDivingFishFilter;
import org.noear.solon.annotation.Addition;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 检查UC用户
 */
@Addition(CheckDivingFishFilter.class)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckDivingFishUser {
}
