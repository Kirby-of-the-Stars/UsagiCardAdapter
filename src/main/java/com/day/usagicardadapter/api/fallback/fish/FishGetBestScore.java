package com.day.usagicardadapter.api.fallback.fish;

import com.day.usagicardadapter.api.fallback.FallbackHandle;
import com.day.usagicardadapter.helper.fish.DivingFishHelper;
import org.noear.snack.ONode;
import org.noear.snack.core.utils.StringUtil;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import org.noear.solon.core.handle.Context;

@Component
public class FishGetBestScore implements FallbackHandle {

    @Inject
    DivingFishHelper divingFishHelper;

    @Override
    public void handle(Context ctx, String identity) throws Throwable {
        boolean isB50 = StringUtil.isEmpty(ONode.loadStr(ctx.body()).get("b50").getString());
        ONode result = divingFishHelper.getBestScore(identity,isB50);
        ctx.render(result);
    }
}
