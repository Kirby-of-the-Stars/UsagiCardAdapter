package com.day.usagicardadapter.filter;

import org.noear.solon.annotation.Component;
import org.noear.solon.core.exception.StatusException;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Filter;
import org.noear.solon.core.handle.FilterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class GlobalExceptionFilter implements Filter {

    static Logger log = LoggerFactory.getLogger(GlobalExceptionFilter.class);

    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        try {
            log.debug("request body: {}", ctx.body());
            log.debug("request param: {}", ctx.paramMap());
            chain.doFilter(ctx);
        } catch (StatusException e) {
            ctx.status(e.getCode()); //可能的状态码为：4xxx
        } catch (Throwable e) {
            ctx.status(500);
            log.error(e.getLocalizedMessage(), e);
        }
    }
}
