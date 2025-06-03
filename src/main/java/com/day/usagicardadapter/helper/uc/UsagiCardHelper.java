package com.day.usagicardadapter.helper.uc;

import com.day.usagicardadapter.exception.UsagiCardException;
import com.day.usagicardadapter.model.divingfish.FishRecord;
import com.day.usagicardadapter.model.divingfish.SongInfo;
import com.day.usagicardadapter.model.uc.UCSongInfo;
import org.noear.snack.ONode;
import org.noear.solon.annotation.Component;
import org.noear.solon.net.http.HttpException;
import org.noear.solon.net.http.HttpUtils;

import java.util.List;

@Component
public class UsagiCardHelper {

    private static final String API_HOST = "https://uc.turou.fun/api/v1/maimai/";


    public List<SongInfo> queryAllSongs(){
        try{
            String body = HttpUtils.http(API_HOST + "/songs")
                    .data("page", "1")
                    .data("page_size", String.valueOf(Integer.MAX_VALUE))
                    .get();
            if(body ==null || body.isEmpty()) throw new UsagiCardException("empty result");
            List<UCSongInfo> songs = ONode.loadStr(body).toObjectList(UCSongInfo.class);

        } catch (HttpException e) {
            throw new UsagiCardException("request exception");
        }
    }

    public List<FishRecord>

}
