package com.day.usagicardadapter.api.fallback.fish;

import com.day.usagicardadapter.api.fallback.FallbackHandle;
import com.day.usagicardadapter.helper.fish.DivingFishHelper;
import org.noear.snack.ONode;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import org.noear.solon.core.handle.Context;

@Component
public class FishGetScore implements FallbackHandle {

    @Inject
    DivingFishHelper divingFishHelper;

    @Override
    public void handle(Context ctx, String identity) throws Throwable {
        ONode node = ONode.loadStr(ctx.body());
        ONode result = divingFishHelper.getScoreByDev(identity, node.get("music_id").getString(), ctx.header("developer-token"));
        ctx.render(result);
    }
}
