package com.day.usagicardadapter.helper.uc;

import cn.hutool.core.map.MapUtil;
import com.day.usagicardadapter.exception.UsagiCardException;
import com.day.usagicardadapter.model.divingfish.SongInfo;
import com.day.usagicardadapter.model.uc.BestScore;
import com.day.usagicardadapter.model.uc.PlateInfo;
import com.day.usagicardadapter.model.uc.ScoreInfo;
import com.day.usagicardadapter.model.uc.SongData;
import com.day.usagicardadapter.model.uc.UCSongInfo;
import com.day.usagicardadapter.model.uc.UsagiCardSong;
import com.day.usagicardadapter.utils.UUIDMappingUtil;
import org.noear.snack.ONode;
import org.noear.snack.core.utils.StringUtil;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Init;
import org.noear.solon.annotation.Inject;
import org.noear.solon.net.http.HttpException;
import org.noear.solon.net.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@Component
public class UsagiCardHelper {

    private static final Logger log = LoggerFactory.getLogger(UsagiCardHelper.class);

    //About api doc : https://uc.turou.fun/api/docs#/
    private static final String API_HOST = "https://uc.turou.fun/api";

    @Inject("${app.usagi.dev-token}")
    private String usageDevToken;

    private Map<String,String> defaultHeaders;
    @Init
    public void init(){
        log.info("Loading Usagi Dev Token :{}",usageDevToken);
        defaultHeaders = MapUtil.of("x-developer-token",usageDevToken);
    }

    public List<ScoreInfo> queryUserAllScores(String UUID){
        try {
            String body = HttpUtils.http(API_HOST + "/v1/maimai/scores")
                    .headers(defaultHeaders)
                    .data("uuid",UUID)
                    .get();
            if (body == null || body.isEmpty()) throw new UsagiCardException("empty result");
            return ONode.loadStr(body).toObjectList(ScoreInfo.class);
        } catch (HttpException e) {
            throw new UsagiCardException("request exception", e);
        }
    }

    public UsagiCardSong queryUserSingleSongScore(String UUID, String songId){
        try {
            String body = HttpUtils.http(API_HOST + "/v1/maimai/minfo")
                    .headers(defaultHeaders)
                    .data("uuid", UUID)
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

    public BestScore queryUserSimpleRecords(String UUID) {
        try {
            String body = HttpUtils.http(API_HOST + "/v1/maimai/bests")
                    .headers(defaultHeaders)
                    .data("uuid", UUID)
                    .get();
            if (body == null || body.isEmpty()) throw new UsagiCardException("empty result");
            BestScore obj = ONode.loadStr(body).toObject(BestScore.class);
            if(obj.getRating()==null) throw new UsagiCardException("unexpected result:"+body);
            return obj;
        } catch (HttpException e) {
            log.error("request error,uuid:{}",UUID);
            throw new UsagiCardException("request exception", e);
        }
    }

    public List<PlateInfo> queryUserPlateInfo(String UUID,String version){
        try {
            String body = HttpUtils.http(API_HOST + "/v1/maimai/plates")
                    .headers(defaultHeaders)
                    .data("uuid",UUID)
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

    public List<UCSongInfo> queryMusicData(){
        try {
            String body = HttpUtils.http(API_HOST + "/maimai/songs")
                    .data("page_size","10000")
                    .get();
            if (body == null || body.isEmpty()) throw new UsagiCardException("empty result");
            List<UCSongInfo> list = ONode.loadStr(body).toObjectList(UCSongInfo.class);
            if(list==null || list.isEmpty()) throw new UsagiCardException("unexpected result:"+body);
            return list;
        } catch (HttpException e) {
            throw new UsagiCardException("request exception", e);
        }
    }

}
