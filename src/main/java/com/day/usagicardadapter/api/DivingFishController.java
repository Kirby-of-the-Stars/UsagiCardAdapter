package com.day.usagicardadapter.api;

import com.day.usagicardadapter.annotation.CheckDivingFishUser;
import com.day.usagicardadapter.helper.uc.UsagiCardHelper;
import com.day.usagicardadapter.model.Result;
import com.day.usagicardadapter.model.divingfish.FishRecord;
import com.day.usagicardadapter.model.divingfish.SongInfo;
import com.day.usagicardadapter.model.divingfish.UserRecordInfo;
import com.day.usagicardadapter.model.uc.ScoreInfo;
import com.day.usagicardadapter.model.uc.UsagiCardSong;
import com.day.usagicardadapter.utils.BeanConvent;
import com.day.usagicardadapter.utils.StrUtil;
import org.noear.snack.core.utils.StringUtil;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Get;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Param;
import org.noear.solon.annotation.Post;

import java.util.ArrayList;
import java.util.List;

@Controller
@Mapping("api/fish/mai")
public class DivingFishController {

    @Inject
    UsagiCardHelper ucHelper;

    /**
     * 查询用户简略成绩(通常来说指的是b50)
     *
     * @param username UsagiCard的UUID
     * @param qq   qq号
     * @param b50  是否查询b50否则默认b35
     * @return {@link UserRecordInfo}
     */
    @CheckDivingFishUser
    @Post
    @Mapping("query/player")
    public Result<UserRecordInfo> queryUserSimpleRecords(String username,String qq,String b50) {
        boolean isB50 = !StringUtil.isEmpty(b50);
        return Result.success(BeanConvent.toRecordInfo(ucHelper.queryUserSimpleRecords(isB50, username, qq), isB50));
    }

    /**
     * 查询用户所有的成绩
     *
     * @param username UsagiCard的UUID
     * @param qq   qq号
     * @return {@link UserRecordInfo}
     */
    @CheckDivingFishUser
    @Get
    @Mapping("player/records")
    public Result<UserRecordInfo> queryUserAllRecords(String username,String qq) {
        UserRecordInfo info = new UserRecordInfo();
        List<ScoreInfo> scores = ucHelper.queryUserAllScores(username, qq);
        info.setRecords(scores.stream().map(BeanConvent::toRecord).toList());
        return Result.success(info);
    }

    /**
     * 查询用户单个歌曲的成绩
     *
     * @param username    UsagiCard的UUID
     * @param qq      qq号
     * @param musicId 歌曲id
     * @return {@link FishRecord}
     */
    @CheckDivingFishUser
    @Post
    @Mapping("player/record")
    public Result<List<FishRecord>> queryUserSingleRecord(String username,String qq,@Param("music_id") String musicId) {
        UsagiCardSong song = ucHelper.queryUserSingleSongScore(username, qq, musicId);
        return Result.success(song.getScores().stream().map(BeanConvent::toRecord).toList());
    }
    /**
     * 查询用户某版本的成绩情况
     *
     * @param username    UsagiCard的UUID
     * @param qq      qq号
     * @param versions 版本列表
     * @return {@link UserRecordInfo}
     */
    @Post
    @Mapping("query/plate")
    public Result<UserRecordInfo> queryUserPlate(String username,String qq,List<String> versions) {
        UserRecordInfo info = new UserRecordInfo();
        List<FishRecord> records = new ArrayList<>();
        for(String version : versions) {
            String v = StrUtil.conventVersion(version);
            if(StringUtil.isEmpty(v)) continue;
            //future: 也许改成并发操作
            ucHelper.queryUserPlateInfo(username, qq, v)
                    .forEach(plateInfo -> records.addAll(BeanConvent.toRecord(plateInfo)));
        }
        info.setRecords(records);
        return Result.success(info);
    }

    /**
     * 获取全部乐曲数据(待定)
     */
    @Get
    @Mapping("/music_data")
    public Result<SongInfo> queryAllSongsInfo() {
        //TODO use maimai.py
        return Result.success(null);
    }
}
