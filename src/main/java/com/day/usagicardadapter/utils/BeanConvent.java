package com.day.usagicardadapter.utils;

import com.day.usagicardadapter.model.divingfish.FishRecord;
import com.day.usagicardadapter.model.divingfish.SongBasicInfo;
import com.day.usagicardadapter.model.divingfish.SongInfo;
import com.day.usagicardadapter.model.divingfish.UserRecordInfo;
import com.day.usagicardadapter.model.uc.BestScore;
import com.day.usagicardadapter.model.uc.ScoreInfo;
import com.day.usagicardadapter.model.uc.UCSongInfo;

import java.util.ArrayList;
import java.util.List;

public class BeanConvent {

    private static final String TYPE_DX = "DX";
    private static final String TYPE_STD = "SD";

    public static SongInfo toSongInfo(UCSongInfo ucSongInfo){
        SongInfo songInfo = new SongInfo();
        SongBasicInfo basicInfo = new SongBasicInfo();
        basicInfo.setArtist(ucSongInfo.getArtist());
        basicInfo.setTitle(ucSongInfo.getTitle());
        basicInfo.setBpm(ucSongInfo.getBpm());
        basicInfo.setGenre(ucSongInfo.getGenre());
        songInfo.setId(ucSongInfo.getId()==null?null:String.valueOf(ucSongInfo.getId()));
        songInfo.setTitle(ucSongInfo.getTitle());
        songInfo.setType(ucSongInfo.getDifficulties().getDx().isEmpty()?TYPE_STD:TYPE_DX);
        songInfo.setDs(ucSongInfo.getDifficulties().getLevelVales());//TODO 有歧义
        songInfo.setLevel(ucSongInfo.getDifficulties().getLevelStr())
    }

    /**
     * 用户简略成绩信息(会缺失用户信息)
     * @param b50 是否为b50，否则为b40
     */
    public static UserRecordInfo toRecordInfo(BestScore score, boolean b50){
        UserRecordInfo info = new UserRecordInfo();
        if(b50){
            List<FishRecord> fishRecords = new ArrayList<>(50);
            fishRecords.addAll(score.getB35_scores().stream().map(BeanConvent::toRecord).toList());
            fishRecords.addAll(score.getB15_scores().stream().map(BeanConvent::toRecord).toList());
            info.setFishRecords(fishRecords);
            info.setRating(score.getAll_rating());
            return info;
        }else {
            //TODO b40
            return info;
        }
    }
    public static FishRecord toRecord(ScoreInfo score){
        FishRecord record = new FishRecord();
        record.setAchievements(score.getAchievements());
        record.setDs(score.getLevel_value());
        record.setDxScore(score.getDx_score());
        record.setLevel(score.getLevel());
        record.setFc(StrUtil.conventIntFc(score.getFc()));
        record.setFs(StrUtil.conventIntFs(score.getFs()));
        record.setLevel_label(StrUtil.conventLevelStr(score.getLevel_index()));
        record.setLevel_index(score.getLevel_index());
        record.setRa(score.getDx_rating());
        record.setRate(StrUtil.conventIntRate(score.getRate()));
        record.setSong_id(score.getSong_id());
        record.setTitle(score.getSong_name());
        record.setType(score.getType());
        return record;
    }
}
