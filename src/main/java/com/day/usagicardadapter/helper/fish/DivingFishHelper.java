package com.day.usagicardadapter.helper.fish;

import com.day.usagicardadapter.exception.UsagiCardException;
import org.noear.snack.ONode;
import org.noear.solon.annotation.Component;
import org.noear.solon.net.http.HttpException;
import org.noear.solon.net.http.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DivingFishHelper {

    private static final Logger log = LoggerFactory.getLogger(DivingFishHelper.class);
    private static final String API_HOST = "https://www.diving-fish.com/api/maimaidxprober/";


    public ONode getChartStats() {
        try{
            String body = HttpUtils.http(API_HOST + "chart_stats")
                    .get();
            if (body == null || body.isEmpty()) throw new UsagiCardException("empty result");
            return ONode.loadStr(body);
        }catch (HttpException e){
            throw new RuntimeException("request exception", e);
        }
    }

    public ONode getRatingRank(){
        try{
            String body = HttpUtils.http(API_HOST + "rating_ranking")
                    .get();
            if (body == null || body.isEmpty()) throw new UsagiCardException("empty result");
            return ONode.loadStr(body);
        }catch (HttpException e){
            throw new RuntimeException("request exception", e);
        }
    }

    public ONode getMusicData(){
        try{
            String body = HttpUtils.http(API_HOST + "music_data")
                    .get();
            if (body == null || body.isEmpty()) throw new UsagiCardException("empty result");
            return ONode.loadStr(body);
        }catch (HttpException e){
            throw new RuntimeException("request exception", e);
        }
    }

    public ONode getBestScore(String qq,boolean isB50){
        try{
            HttpUtils request = HttpUtils.http(API_HOST + "query/player");
            Map<String,Object> map = new HashMap<>();
            map.put("qq",qq);
            if(isB50){
                map.put("b50","1");
            }
            request.bodyOfBean(map);
            String body = request.post();
            if (body == null || body.isEmpty()) throw new UsagiCardException("empty result");
            return ONode.loadStr(body);
        }catch (HttpException e){
            throw new RuntimeException("request exception", e);
        }
    }

    public ONode getAllScoresByDev(String qq,String devToken){
        try{
            String body = HttpUtils.http(API_HOST + "dev/player/records")
                    .headerAdd("Developer-Token",devToken)
                    .data("qq",qq)
                    .get();
            if (body == null || body.isEmpty()) throw new UsagiCardException("empty result");
            return ONode.loadStr(body);
        }catch (HttpException e){
            throw new RuntimeException("request exception", e);
        }
    }
    public ONode getAllScores(String qq,String importToken){
        try{
            String body = HttpUtils.http(API_HOST + "dev/player/records")
                    .headerAdd("Import-Token",importToken)
                    .data("qq",qq)
                    .get();
            if (body == null || body.isEmpty()) throw new UsagiCardException("empty result");
            return ONode.loadStr(body);
        }catch (HttpException e){
            throw new RuntimeException("request exception", e);
        }
    }
    public ONode getScoreByDev(String qq,String music_id,String devToken){
        try{
            Map<String,Object> map = Map.of("qq",qq,"music_id",music_id);
            String body = HttpUtils.http(API_HOST + "query/player")
                    .headerAdd("Developer-Token",devToken)
                    .data(map)
                    .post();
            if (body == null || body.isEmpty()) throw new UsagiCardException("empty result");
            return ONode.loadStr(body);
        }catch (HttpException e){
            throw new RuntimeException("request exception", e);
        }
    }

    public ONode getScoresByVersions(String qq,String[] versions){
        try{
            Map<String,Object> map = Map.of("qq",qq,"version",versions);
            String body = HttpUtils.http(API_HOST + "query/plate")
                    .data(map)
                    .post();
            if (body == null || body.isEmpty()) throw new UsagiCardException("empty result");
            return ONode.loadStr(body);
        }catch (HttpException e){
            throw new RuntimeException("request exception", e);
        }
    }
}
