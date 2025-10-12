package com.day.usagicardadapter.api.fallback;


import org.noear.solon.core.handle.Context;

@FunctionalInterface
public interface FallbackHandle {
    /**
     * 处理回退逻辑。
     * @param ctx 当前请求的上下文，可以从中获取所有请求信息。
     */
    void handle(Context ctx, String identity) throws Throwable;
}
