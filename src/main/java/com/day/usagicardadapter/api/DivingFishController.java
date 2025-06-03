package com.day.usagicardadapter.api;

import com.day.usagicardadapter.annotation.CheckDivingFishUser;
import com.day.usagicardadapter.model.Result;
import com.day.usagicardadapter.model.divingfish.SongInfo;
import com.day.usagicardadapter.model.divingfish.UserRecordInfo;
import org.noear.snack.core.utils.StringUtil;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Get;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
import org.noear.solon.core.handle.Context;

@Controller
@Mapping("api/fish/mai")
public class DivingFishController {

    /**
     *
     * @param username 水鱼用户名
     * @param qq qq号
     * @param b50 是否查询b50否则默认b35
     * @return {@link UserRecordInfo}
     */
    @CheckDivingFishUser
    @Post
    @Mapping("query/player")
    public Result<UserRecordInfo> queryUserSimpleRecords(Context ctx, String username,String qq, String b50){
        boolean isB50 = !StringUtil.isEmpty(b50);

        return Result.success(null);
    }
    /**
     * 获取全部乐曲数据
     */
    @Get
    @Mapping("/music_data")
    public Result<SongInfo> queryAllSongsInfo(){

        return Result.success(null);
    }
}
