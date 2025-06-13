package com.day.usagicardadapter.helper.uc;

import com.day.usagicardadapter.exception.UsagiCardException;
import com.day.usagicardadapter.model.DifficultyType;
import com.day.usagicardadapter.model.divingfish.SongInfo;
import com.day.usagicardadapter.model.divingfish.UserRecordInfo;
import com.day.usagicardadapter.model.uc.BestScore;
import com.day.usagicardadapter.model.uc.PlateInfo;
import com.day.usagicardadapter.model.uc.ScoreInfo;
import com.day.usagicardadapter.model.uc.UCSongInfo;
import com.day.usagicardadapter.model.uc.UsagiCardSong;
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
    private static final String API_HOST = "https://uc.turou.fun/api/maimai/v1";
    //About api doc : https://uc.turou.fun/api/docs#/

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

    public UsagiCardSong queryUserSingleSongScore(String UUID, String qq, String songId){
        boolean useUUID = !StringUtil.isEmpty(UUID);
        try {
            String identity = useUUID ? UUID : qq;
            String body = HttpUtils.http(API_HOST + "/songs")
                    .data(useUUID ? "uuid" : "qq", identity)
                    .data("id",songId)
                    .get();
            if (body == null || body.isEmpty()) throw new UsagiCardException("empty result");
            UsagiCardSong obj = ONode.loadStr(body).toObject(UsagiCardSong.class);
            if(obj.getSong()==null || obj.getSong().getId() == null) throw new UsagiCardException("unexpected result:"+body);
            return obj;
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
            BestScore obj = ONode.loadStr(body).toObject(BestScore.class);
            if(obj.getAll_rating()==null) throw new UsagiCardException("unexpected result:"+body);
            return obj;
        } catch (HttpException e) {
            throw new UsagiCardException("request exception", e);
        }
    }

    public List<PlateInfo> queryUserPlateInfo(String UUID,String qq,String version){
        boolean useUUID = !StringUtil.isEmpty(UUID);
        try {
            String identity = useUUID ? UUID : qq;
            String body = HttpUtils.http(API_HOST + "/plates")
                    .data(useUUID ? "uuid" : "qq", identity)
                    .data("plate",version + "将")
                    .get();
            if (body == null || body.isEmpty()) throw new UsagiCardException("empty result");
            List<PlateInfo> list = ONode.loadStr(body).toObjectList(PlateInfo.class);
            if(list==null) throw new UsagiCardException("unexpected result:"+body);
            return list;
        } catch (HttpException e) {
            throw new UsagiCardException("request exception", e);
        }
    }
}
