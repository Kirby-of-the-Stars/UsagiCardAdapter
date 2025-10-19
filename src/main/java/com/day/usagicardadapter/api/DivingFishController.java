package com.day.usagicardadapter.api;

import com.day.usagicardadapter.annotation.FallbackToFish;
import com.day.usagicardadapter.api.fallback.fish.*;
import com.day.usagicardadapter.exception.UsagiCardException;
import com.day.usagicardadapter.helper.fish.DivingFishHelper;
import com.day.usagicardadapter.helper.uc.UsagiCardHelper;
import com.day.usagicardadapter.model.divingfish.FishRecord;
import com.day.usagicardadapter.model.divingfish.FishUserInfo;
import com.day.usagicardadapter.model.divingfish.UserBestRecordInfo;
import com.day.usagicardadapter.model.divingfish.UserRecordInfo;
import com.day.usagicardadapter.model.divingfish.response.DivingFishVersionResp;
import com.day.usagicardadapter.model.uc.BestPlayCountRecordInfo;
import com.day.usagicardadapter.model.uc.BestScore;
import com.day.usagicardadapter.model.uc.ScoreInfo;
import com.day.usagicardadapter.model.uc.UsagiCardSong;
import com.day.usagicardadapter.utils.BeanConvent;
import com.day.usagicardadapter.utils.StrUtil;
import com.day.usagicardadapter.utils.UUIDMappingUtil;
import com.day.usagicardadapter.utils.VersionUtils;
import org.noear.snack.ONode;
import org.noear.snack.core.utils.StringUtil;
import org.noear.solon.annotation.*;
import org.noear.solon.core.handle.Context;
import org.noear.solon.validation.annotation.NotBlank;
import org.noear.solon.validation.annotation.NotEmpty;

import java.util.*;

@Controller
@Mapping("fish/maimaidxprober")
public class DivingFishController {
    //TODO username

    @Inject
    UsagiCardHelper ucHelper;
    @Inject
    UUIDMappingUtil uuidMappingUtil;
    @Inject
    DivingFishHelper divingFishHelper;
    @Inject
    VersionUtils versionUtils;

    /**
     * 查询用户简略成绩(通常来说指的是b50)
     * @param qq       qq号
     * @param b50      是否查询b50否则默认b35
     * @return {@link UserBestRecordInfo}
     */
    @FallbackToFish(fallback = FishGetBestScore.class)
    @Post
    @Mapping("query/player")
    public UserBestRecordInfo queryUserSimpleRecords(String username,@NotBlank(message = "qq不能为空") String qq, String b50) {
        boolean isB50 = !StringUtil.isEmpty(b50);
        FishUserInfo fishUserInfo = uuidMappingUtil.get(qq);
        return BeanConvent.toBestRecordInfo(ucHelper.queryUserSimpleRecords(fishUserInfo.getUuid()), isB50,fishUserInfo);
    }

    /**
     * 查询用户所有的成绩(DEV)
     *
     * @param username UsagiCard的UUID
     * @param qq       qq号
     * @return {@link UserRecordInfo}
     */
    @FallbackToFish(fallback = FishGetAllScoresDEV.class)
    @Get
    @Mapping("dev/player/records")
    public UserRecordInfo queryUserAllRecordsDEV(String username, @NotBlank(message = "qq不能为空") String qq) {
        FishUserInfo fishUserInfo = uuidMappingUtil.get(qq);
        List<ScoreInfo> scores = ucHelper.queryUserAllScores(fishUserInfo.getUuid());
        return BeanConvent.toRecordsInfo(scores, fishUserInfo);
    }

    /**
     * 查询用户单个歌曲的成绩(DEV)
     *
     * @param qq       qq号
     * @param musicId  歌曲id
     * @return {@link FishRecord}
     */
    @FallbackToFish(fallback = FishGetScore.class)
    @Post
    @Mapping("dev/player/record")
    public Map<String, List<FishRecord>> queryUserSingleRecord(String username, String qq, @Param("music_id") String musicId) {
        int mid = Integer.parseInt(musicId);
        UsagiCardSong song = ucHelper.queryUserSingleSongScore(uuidMappingUtil.get(qq).getUuid(),musicId);
        Map<String, List<FishRecord>> map = new HashMap<>();
        if (song == null) {
            //返回空数据
            return map;
        }
        //区分是不是dx
        boolean isDx = mid > 10000;
        List<FishRecord> list = song.getScores().stream().map(BeanConvent::toRecord).toList();
        map.put(musicId,list.stream().filter(r->{
            if(isDx) return Objects.equals(r.getType(), "DX");
            else return Objects.equals(r.getType(), "SD");
        }).peek(r-> r.setSong_id(mid)).toList());
        return map;
    }
    /**
     * 查询用户所有的成绩 (Personal Verify)
     *
     * @param qq       qq号
     * @return {@link UserRecordInfo}
     */
    @FallbackToFish(fallback = FishGetAllScores.class)
    @Get
    @Mapping("player/records")
    public UserRecordInfo queryUserAllRecords(String username, @NotBlank(message = "qq不能为空") String qq) {
        //Cookie Login not support
        return queryUserAllRecordsDEV(username, qq);
    }

    /**
     * 查询用户某版本的成绩情况
     *
     * @param qq       qq号
     * @param version 版本列表
     * @return {@link UserRecordInfo}
     */
    @FallbackToFish(fallback = FishGetScoresByVersions.class)
    @Post
    @Mapping("query/plate")
    public DivingFishVersionResp queryUserPlate(String username, String qq, @NotEmpty(message = "请填写版本列表") List<String> version) {
        List<FishRecord> records = new ArrayList<>();
        for (String _version : version) {
            String v = StrUtil.conventVersion(_version);
            if (StringUtil.isEmpty(v)) continue;
            //TODO future: 也许改成并发操作
            ucHelper.queryUserPlateInfo(uuidMappingUtil.get(qq).getUuid(), v)
                    .forEach(plateInfo -> records.addAll(BeanConvent.toRecord(plateInfo)));
        }
        return new DivingFishVersionResp(records);
    }

    /**
     * 获取全部乐曲数据
     */
    @Get
    @Mapping("music_data")
    public ONode queryAllSongsInfo() {
        return divingFishHelper.getMusicData();
    }

    @Get
    @Mapping("chart_stats")
    public ONode getChartStats() {
        return divingFishHelper.getChartStats();
    }

    @Get
    @Mapping("rating_ranking")
    public ONode getRatingRanking() {
        return divingFishHelper.getRatingRank();
    }


    /**
     * 查询用户游玩次数最高的前50
     * @param qq       qq号
     * @return {@link BestPlayCountRecordInfo}
     */
    @Get
    @Mapping("query/player/playcount")
    public BestPlayCountRecordInfo queryUserBestPlayCountRecords(Context ctx, @NotBlank(message = "qq不能为空") String qq) throws Throwable {
        FishUserInfo fishUserInfo = uuidMappingUtil.get(qq);
        if(fishUserInfo == null){
            ONode node = new ONode();
            node.set("message", "only usagi user can use");
            ctx.status(400);
            ctx.render(node);
            return null;
        }
        String uuid = fishUserInfo.getUuid();

        //TODO temperately using all scores
        List<ScoreInfo> top50Pc = ucHelper.queryUserAllScores(uuid)
                .stream()
                .sorted(Comparator.comparing(a -> Optional.ofNullable(a.getPlay_count()).orElse(0)))
                .toList()
                .reversed();
        int size = top50Pc.size();
        top50Pc = top50Pc.subList(0, Math.min(size, 50));
        //BestScore Wrapper
        BestScore bs = new BestScore();
        bs.setB35(top50Pc.subList(0,Math.min(size, 35)));
        if(size > 35){
            bs.setB15(top50Pc.subList(35,Math.min(size, 50)));
        }else{
            bs.setRating_b15(0);
            bs.setScores_b15(new ArrayList<>());
        }
        return BeanConvent.toBestPlayCountInfo(bs, fishUserInfo);
    }
}
