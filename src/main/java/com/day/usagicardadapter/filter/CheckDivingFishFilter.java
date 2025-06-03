package com.day.usagicardadapter.filter;


import com.day.usagicardadapter.model.Result;
import org.noear.snack.ONode;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Filter;
import org.noear.solon.core.handle.FilterChain;

public class CheckDivingFishFilter implements Filter {

    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        String contentType = ctx.contentType();
        if(contentType.equals("application/json") || contentType.equals("text/json")) {
            ONode node = ONode.loadStr(ctx.body());
            if(node.contains("username") || node.contains("token")) {
                //TODO check valid
                chain.doFilter(ctx);
            }else {
                ctx.status(403);
                ctx.render(Result.fail("user not found"));
            }
        }else {
            ctx.status(406);
        }

    }
}
