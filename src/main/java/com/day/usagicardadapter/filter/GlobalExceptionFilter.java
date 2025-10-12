package com.day.usagicardadapter.filter;

import com.day.usagicardadapter.model.Result;
import org.noear.snack.ONode;
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
            log.info("request body: {}", ONode.stringify(ctx.body()));
            log.info("request param: {}", ONode.stringify(ctx.paramMap()));
            chain.doFilter(ctx);
        } catch (StatusException e) {
            ctx.status(e.getCode()); //可能的状态码为：4xxx
        } catch (Throwable e) {
            ctx.status(500);
            ctx.render(Result.fail("Server ERROR "+e.getLocalizedMessage()));
            log.error(e.getLocalizedMessage(), e);
        }
    }
}
