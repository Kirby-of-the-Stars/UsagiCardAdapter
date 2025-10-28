package com.day.usagicardadapter.api.fallback.fish;

import com.day.usagicardadapter.api.fallback.FallbackHandle;
import com.day.usagicardadapter.helper.fish.DivingFishHelper;
import org.noear.snack.ONode;
import org.noear.snack.core.Feature;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import org.noear.solon.core.handle.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Component
public class FishGetScoresByVersions implements FallbackHandle {

    @Inject
    DivingFishHelper divingFishHelper;
    private static final Logger log = LoggerFactory.getLogger(FishGetScoresByVersions.class);

    @Override
    public void handle(Context ctx, String identity) throws Throwable {
        ONode body = ONode.loadStr(ctx.body(), Feature.ArrayNullAsEmpty).get("version");
        ONode node;
        if(body.isArray()) {
            List<String> versions = body.toObjectList(String.class);
            node = divingFishHelper.getScoresByVersions(identity, versions.toArray(new String[0]));
            ctx.render(node);
            return;
        }
        log.warn("Request Version List is Empty, request body:{}",body.toJson());
        node = ONode.newObject().set("verlist", new String[0]);
        ctx.render(node);
    }
}
