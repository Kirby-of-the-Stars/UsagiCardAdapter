package com.day.usagicardadapter.cache;

import com.day.usagicardadapter.exception.UsagiCardException;
import com.day.usagicardadapter.helper.uc.UsagiCardHelper;
import com.day.usagicardadapter.model.divingfish.SongInfo;
import com.day.usagicardadapter.model.uc.SongData;
import com.day.usagicardadapter.model.uc.UCSongInfo;
import com.day.usagicardadapter.utils.BeanConvent;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class MusicDataCache extends TimeCache<SongData> {

    static Logger log = LoggerFactory.getLogger(MusicDataCache.class);

    @Inject
    UsagiCardHelper ucHelper;

    public MusicDataCache() {
        super(TimeUnit.DAYS, 1);
    }

    public SongData getMusicData() {
        try {
            if (this.isExpired() || this.isEmpty()) {
                List<UCSongInfo> songInfos = ucHelper.queryMusicData();
                List<SongInfo> list = songInfos.stream().flatMap(info -> BeanConvent.toSongInfo(info).stream()).toList();
                SongData data = new SongData(list, list.size());
                put(data);
                log.info("更新乐曲数据成功：共{}首", data.getTotal());
            }
            return this.get();
        } catch (IllegalAccessException e) {
            log.warn("获取缓存失败{}", e.getLocalizedMessage(),e);
        } catch (UsagiCardException e) {
            log.warn("从服务端获取失败{}", e.getLocalizedMessage(),e);
        }
        return null;
    }
}
