package com.day.usagicardadapter.helper.uc;

import com.day.usagicardadapter.exception.UsagiCardException;
import com.day.usagicardadapter.model.DifficultyType;
import com.day.usagicardadapter.model.divingfish.SongInfo;
import com.day.usagicardadapter.model.divingfish.UserRecordInfo;
import com.day.usagicardadapter.model.uc.BestScore;
import com.day.usagicardadapter.model.uc.ScoreInfo;
import com.day.usagicardadapter.model.uc.UCSongInfo;
import com.day.usagicardadapter.utils.BeanConvent;
import org.noear.snack.ONode;
import org.noear.snack.core.utils.StringUtil;
import org.noear.solon.annotation.Component;
import org.noear.solon.net.http.HttpException;
import org.noear.solon.net.http.HttpUtils;

import java.util.List;

@Component
public class UsagiCardHelper {
    //TODO 接口缺少用户信息
    private static final String API_HOST = "https://uc.turou.fun/api/v1/maimai/";


    public List<SongInfo> queryAllSongs() {
        try {
            String body = HttpUtils.http(API_HOST + "/songs")
                    .data("page", "1")
                    .data("page_size", String.valueOf(Integer.MAX_VALUE))
                    .get();
            if (body == null || body.isEmpty()) throw new UsagiCardException("empty result");
            List<UCSongInfo> songs = ONode.loadStr(body).toObjectList(UCSongInfo.class);
            return songs.stream().map(song->{
                boolean isDx = song.getId() > 10_000;//TODO 宴谱识别
                return BeanConvent.toSongInfo(song,isDx? DifficultyType.DX:DifficultyType.STANDARD);
            }).toList();
        } catch (HttpException e) {
            throw new UsagiCardException("request exception", e);
        }
    }

    public List<ScoreInfo> queryUserAllScores(String UUID,String qq){
        boolean useUUID = !StringUtil.isEmpty(UUID);
        try {
            String identity = useUUID ? UUID : qq;
            String body = HttpUtils.http(API_HOST + "/scores")
                    .data(useUUID ? "uuid" : "qq", identity)
                    .get();
            if (body == null || body.isEmpty()) throw new UsagiCardException("empty result");
            return ONode.loadStr(body).toObjectList(ScoreInfo.class);
        } catch (HttpException e) {
            throw new UsagiCardException("request exception", e);
        }
    }

    public UCSongInfo queryUserSingleSongScore(String UUID,String qq,String songId){
        boolean useUUID = !StringUtil.isEmpty(UUID);
        try {
            String identity = useUUID ? UUID : qq;
            String body = HttpUtils.http(API_HOST + "/songs")
                    .data(useUUID ? "uuid" : "qq", identity)
                    .data("id",songId)
                    .get();
            if (body == null || body.isEmpty()) throw new UsagiCardException("empty result");
            return ONode.loadStr(body).toObject(UCSongInfo.class);
        } catch (HttpException e) {
            throw new UsagiCardException("request exception", e);
        }
    }

    public BestScore queryUserSimpleRecords(boolean b50, String UUID, String qq) {
        boolean useUUID = !StringUtil.isEmpty(UUID);
        try {
            String identity = useUUID ? UUID : qq;
            String body = HttpUtils.http(API_HOST + "/bests")
                    .data(useUUID ? "uuid" : "qq", identity)
                    .get();
            if (body == null || body.isEmpty()) throw new UsagiCardException("empty result");
            return ONode.loadStr(body).toObject(BestScore.class);
        } catch (HttpException e) {
            throw new UsagiCardException("request exception", e);
        }
    }

}
