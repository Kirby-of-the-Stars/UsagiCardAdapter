package com.day.usagicardadapter.annotation;

import com.day.usagicardadapter.api.fallback.FallbackHandle;
import com.day.usagicardadapter.filter.FallbackToFishFilter;
import org.noear.solon.annotation.Addition;

import java.lang.annotation.*;

@Addition(FallbackToFishFilter.class)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FallbackToFish {
    String checkProp() default "qq";
    Class<? extends FallbackHandle> fallback();
}
