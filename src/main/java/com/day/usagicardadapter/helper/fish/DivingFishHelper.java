package com.day.usagicardadapter.helper.fish;

import com.day.usagicardadapter.exception.DivingFishException;
import org.noear.snack.ONode;
import org.noear.solon.annotation.Component;
import org.noear.solon.net.http.HttpResponse;
import org.noear.solon.net.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Component
public class DivingFishHelper {

    private static final Logger log = LoggerFactory.getLogger(DivingFishHelper.class);
    private static final String API_HOST = "https://www.diving-fish.com/api/maimaidxprober/";

    private static ONode sendFishReq(HttpUtils request,String method){
        try(HttpResponse res = request.exec(method)){
            if(res.code() != 200){
                throw new DivingFishException(ONode.loadStr(res.bodyAsString()), res.code());
            }
            return ONode.loadStr(res.bodyAsString());
        }catch (Exception ex){
            log.error("水鱼请求失败:{}",ex.getLocalizedMessage(),ex);
            throw new DivingFishException(ex);
        }
    }


    public ONode getChartStats() {
        return sendFishReq(HttpUtils.http(API_HOST + "chart_stats"),"GET");
    }

    public ONode getRatingRank(){
        return sendFishReq(HttpUtils.http(API_HOST + "rating_ranking"),"GET");
    }

    public ONode getMusicData(){
        return sendFishReq(HttpUtils.http(API_HOST + "music_data"),"GET");
    }

    public ONode getBestScore(String qq,boolean isB50){
        HttpUtils req = HttpUtils.http(API_HOST + "query/player");
        Map<String,Object> map = new HashMap<>();
        map.put("qq",qq);
        if(isB50){
            map.put("b50","1");
        }
        req.bodyOfBean(map);
        return sendFishReq(req,"POST");
    }

    public ONode getAllScoresByDev(String qq,String devToken){
        HttpUtils req = HttpUtils.http(API_HOST + "dev/player/records");
        req.data("qq",qq);
        req.headerAdd("Developer-Token",devToken);
        return sendFishReq(req,"GET");
    }
    public ONode getAllScores(String qq,String importToken){
        HttpUtils req = HttpUtils.http(API_HOST + "dev/player/records");
        req.data("qq",qq);
        req.headerAdd("Import-Token",importToken);
        return sendFishReq(req,"GET");
    }
    public ONode getScoreByDev(String qq,String music_id,String devToken){
        HttpUtils req = HttpUtils.http(API_HOST + "dev/player/record");
        Map<String,Object> map = Map.of("qq",qq,"music_id",music_id);
        req.bodyOfBean(map);
        req.headerAdd("Developer-Token",devToken);
        return sendFishReq(req,"POST");
    }
    public ONode getScoresByVersions(String qq,String[] versions) {
        HttpUtils req = HttpUtils.http(API_HOST + "query/plate");
        Map<String,Object> map = Map.of("qq",qq,"version",versions);
        req.bodyOfBean(map);
        return sendFishReq(req,"POST");
    }
}
