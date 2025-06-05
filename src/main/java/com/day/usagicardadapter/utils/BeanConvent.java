package com.day.usagicardadapter.utils;

import com.day.usagicardadapter.model.DifficultyType;
import com.day.usagicardadapter.model.divingfish.FishRecord;
import com.day.usagicardadapter.model.divingfish.SongBasicInfo;
import com.day.usagicardadapter.model.divingfish.SongInfo;
import com.day.usagicardadapter.model.divingfish.UserRecordInfo;
import com.day.usagicardadapter.model.uc.BestScore;
import com.day.usagicardadapter.model.uc.PlateInfo;
import com.day.usagicardadapter.model.uc.PlateScoreInfo;
import com.day.usagicardadapter.model.uc.PlateSongInfo;
import com.day.usagicardadapter.model.uc.ScoreInfo;
import com.day.usagicardadapter.model.uc.UCSongInfo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BeanConvent {

    private static final String TYPE_DX = "DX";
    private static final String TYPE_STD = "SD";

    public static SongInfo toSongInfo(UCSongInfo ucSongInfo, DifficultyType type){
        SongInfo songInfo = new SongInfo();
        SongBasicInfo basicInfo = new SongBasicInfo();
        basicInfo.setArtist(ucSongInfo.getArtist());
        basicInfo.setTitle(ucSongInfo.getTitle());
        basicInfo.setBpm(ucSongInfo.getBpm());
        basicInfo.setGenre(ucSongInfo.getGenre());
        songInfo.setBasic_info(basicInfo);
        songInfo.setId(ucSongInfo.getId()==null?null:String.valueOf(ucSongInfo.getId()));
        songInfo.setTitle(ucSongInfo.getTitle());
        songInfo.setType(type.equals(DifficultyType.STANDARD)?TYPE_STD:TYPE_DX);
        songInfo.setDs(ucSongInfo.getDifficulties().getLevelVales(type));//TODO 有歧义
        songInfo.setLevel(ucSongInfo.getDifficulties().getLevelStr(type));
        //TODO MORE
        return songInfo;
    }

    /**
     * 用户简略成绩信息(会缺失用户信息)
     * @param b50 是否为b50，否则为b40
     */
    public static UserRecordInfo toRecordInfo(BestScore score, boolean b50){
        UserRecordInfo info = new UserRecordInfo();
        if(b50){
            List<FishRecord> fishRecords = new ArrayList<>(50);
            fishRecords.addAll(score.getAllScores().stream().map(BeanConvent::toRecord).toList());
            info.setRecords(fishRecords);
            info.setRating(score.getAll_rating());
        }else {
            List<ScoreInfo> scores = new ArrayList<>(score.getAllScores());
            scores.sort(Comparator.comparing(ScoreInfo::getDx_rating));
            scores.reversed();
            List<ScoreInfo> b40 = scores.subList(0, 40);
            info.setRating(b40.stream().map(ScoreInfo::getDx_rating).reduce(0,Integer::sum));
            info.setRecords(b40.stream().map(BeanConvent::toRecord).toList());
        }
        return info;
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
        record.setType(StrUtil.conventDXType(score.getType()));
        return record;
    }
    /*
        TODO 缺少：定数 rating dx分
     */
    public static List<FishRecord> toRecord(PlateInfo plateInfo){
        List<PlateScoreInfo> scores = plateInfo.getScores();
        List<FishRecord> fishRecords = new ArrayList<>(scores.size());
        for(PlateScoreInfo score:scores){
            FishRecord Record = new FishRecord();
            Record.setAchievements(score.getAchievement());
            Record.setSong_id(score.getId());
            Record.setRate(StrUtil.conventIntRate(score.getRate()));
            Record.setFc(StrUtil.conventIntFc(score.getFc()));
            Record.setFs(StrUtil.conventIntFs(score.getFs()));
            Record.setLevel_index(score.getLevel_index());
            Record.setLevel_label(StrUtil.conventLevelStr(score.getLevel_index()));
            Record.setType(StrUtil.conventDXType(score.getType()));
            Record.setTitle(plateInfo.getSong().getTitle());
            fishRecords.add(Record);
        }
        return fishRecords;
    }
}
