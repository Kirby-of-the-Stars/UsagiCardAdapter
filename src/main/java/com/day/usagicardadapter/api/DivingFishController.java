package com.day.usagicardadapter.api;

import com.day.usagicardadapter.annotation.CheckDivingFishUser;
import com.day.usagicardadapter.helper.uc.UsagiCardHelper;
import com.day.usagicardadapter.model.Result;
import com.day.usagicardadapter.model.divingfish.FishRecord;
import com.day.usagicardadapter.model.divingfish.SongInfo;
import com.day.usagicardadapter.model.divingfish.UserRecordInfo;
import com.day.usagicardadapter.model.uc.ScoreInfo;
import com.day.usagicardadapter.model.uc.UCSongInfo;
import com.day.usagicardadapter.utils.BeanConvent;
import org.noear.snack.core.utils.StringUtil;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Get;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Param;
import org.noear.solon.annotation.Post;
import org.noear.solon.core.handle.Context;

import java.util.List;

@Controller
@Mapping("api/fish/mai")
public class DivingFishController {

    @Inject
    UsagiCardHelper ucHelper;

    /**
     * 查询用户简略成绩(通常来说指的是b50)
     *
     * @param uuid UsagiCard的UUID
     * @param qq   qq号
     * @param b50  是否查询b50否则默认b35
     * @return {@link UserRecordInfo}
     */
    @CheckDivingFishUser
    @Post
    @Mapping("query/player")
    public Result<UserRecordInfo> queryUserSimpleRecords(Context ctx, String uuid, String qq, String b50) {
        boolean isB50 = !StringUtil.isEmpty(b50);
        return Result.success(BeanConvent.toRecordInfo(ucHelper.queryUserSimpleRecords(isB50, uuid, qq), isB50));
    }

    /**
     * 查询用户所有的成绩
     *
     * @param uuid UsagiCard的UUID
     * @param qq   qq号
     * @return {@link UserRecordInfo}
     */
    @CheckDivingFishUser
    @Get
    @Mapping("player/records")
    public Result<UserRecordInfo> queryUserAllRecords(Context ctx, String uuid, String qq) {
        UserRecordInfo info = new UserRecordInfo();
        List<ScoreInfo> scores = ucHelper.queryUserAllScores(uuid, qq);
        info.setFishRecords(scores.stream().map(BeanConvent::toRecord).toList());
        return Result.success(info);
    }

    /**
     * 查询用户单个歌曲的成绩
     *
     * @param uuid    UsagiCard的UUID
     * @param qq      qq号
     * @param musicId 歌曲id
     * @return {@link UserRecordInfo}
     */
    @CheckDivingFishUser
    @Post
    @Mapping("player/record")
    public Result<List<FishRecord>> queryUserAllRecords(Context ctx, String uuid, String qq, @Param("music_id") String musicId) {
        UCSongInfo scoreInfo = ucHelper.queryUserSingleSongScore(uuid, qq, musicId);
        return Result.success(scoreInfo.getScores().stream().map(BeanConvent::toRecord).toList());
    }

    /**
     * 获取全部乐曲数据(TODO)
     */
    @Get
    @Mapping("/music_data")
    public Result<SongInfo> queryAllSongsInfo() {
        //TODO use maimai.py
        return Result.success(null);
    }
}
