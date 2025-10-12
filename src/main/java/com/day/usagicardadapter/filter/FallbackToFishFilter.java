package com.day.usagicardadapter.filter;

import com.day.usagicardadapter.annotation.FallbackToFish;
import com.day.usagicardadapter.api.fallback.FallbackHandle;
import com.day.usagicardadapter.utils.UUIDMappingUtil;
import org.noear.snack.ONode;
import org.noear.solon.Solon;
import org.noear.solon.core.handle.Action;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Filter;
import org.noear.solon.core.handle.FilterChain;

public class FallbackToFishFilter implements Filter {

    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        Action action = ctx.action();
        if(action == null){chain.doFilter(ctx);return;}
        FallbackToFish fish = action.method().getAnnotation(FallbackToFish.class);
        FallbackHandle handlerBean = Solon.context().getBean(fish.fallback());
        String id = switch (ctx.method()){
            case "GET" -> ctx.param(fish.checkProp());
            case "POST" -> ONode.loadStr(ctx.body()).get(fish.checkProp()).getString();
            default -> null;
        };
        UUIDMappingUtil uuidMappingUtil = Solon.context().getBean(UUIDMappingUtil.class);
        if(!uuidMappingUtil.has(id) && handlerBean != null) {
            handlerBean.handle(ctx,id);
        }else {
            chain.doFilter(ctx);
        }
    }
}
