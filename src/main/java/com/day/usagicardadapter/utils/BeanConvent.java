package com.day.usagicardadapter.utils;

import com.day.usagicardadapter.model.DifficultyType;
import com.day.usagicardadapter.model.divingfish.*;
import com.day.usagicardadapter.model.uc.BestScore;
import com.day.usagicardadapter.model.uc.DifficultyInfo;
import com.day.usagicardadapter.model.uc.PlateInfo;
import com.day.usagicardadapter.model.uc.PlateScoreInfo;
import com.day.usagicardadapter.model.uc.ScoreInfo;
import com.day.usagicardadapter.model.uc.UCSongInfo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BeanConvent {

    //TODO lxn style id → divingfish id
    //TODO 特判Link同名歌

    private static final int CURRENT_VERSION = 25000;

    public static List<SongInfo> toSongInfo(UCSongInfo ucSongInfo) {
        List<SongInfo> result = new ArrayList<>(3);
        SongBasicInfo basicInfo = new SongBasicInfo();
        basicInfo.setArtist(ucSongInfo.getArtist());
        basicInfo.setTitle(ucSongInfo.getTitle());
        basicInfo.setBpm(ucSongInfo.getBpm());
        basicInfo.setGenre(ucSongInfo.getGenre());
        basicInfo.setIs_new(isNew(ucSongInfo.getVersion()));
        if (ucSongInfo.isHas(DifficultyType.STANDARD)) {
            result.add(toSongInfo(ucSongInfo, basicInfo, DifficultyType.STANDARD));
        }
        if (ucSongInfo.isHas(DifficultyType.DX)) {
            result.add(toSongInfo(ucSongInfo, basicInfo, DifficultyType.DX));
        }
        if (ucSongInfo.isHas(DifficultyType.UTAG)) {
            result.add(toSongInfo(ucSongInfo, basicInfo, DifficultyType.UTAG));
        }
        //TODO need check
        return result;
    }


    private static SongInfo toSongInfo(UCSongInfo ucSongInfo, SongBasicInfo basicInfo, DifficultyType type) {
        SongInfo songInfo = new SongInfo();
        songInfo.setBasic_info(basicInfo);
        //notice id is different
        songInfo.setId(ucSongInfo.getId() == null ? null : String.valueOf(ucSongInfo.getId()));
        songInfo.setTitle(ucSongInfo.getTitle());
        songInfo.setType(StrUtil.conventDXType(type));
        songInfo.setDs(ucSongInfo.getDifficulties().getLevelVales(type));
        songInfo.setLevel(ucSongInfo.getDifficulties().getLevelStr(type));
        songInfo.setCids(new ArrayList<>());
        List<SongChart> charts = new ArrayList<>();
        for (DifficultyInfo dif : ucSongInfo.getDifficulties().getDiffs(type)) {
            SongChart chart = new SongChart();
            chart.setCharter(dif.getNote_designer());
            chart.setNotes(StrUtil.toList(
                    dif.getTap_num(),
                    dif.getHold_num(),
                    dif.getSlide_num(),
                    dif.getTouch_num(),
                    dif.getBreak_num()
            ));
            charts.add(chart);
        }
        songInfo.setCharts(charts);
        return songInfo;
    }

    private static boolean isNew(Integer version){
        return version != null && version >= CURRENT_VERSION;
    }

    /**
     * 用户简略成绩信息 (b50)
     *
     * @param b50 是否为b50，否则为b40
     */
    public static UserBestRecordInfo toBestRecordInfo(BestScore score, boolean b50, FishUserInfo fishUserInfo) {
        UserBestRecordInfo info = new UserBestRecordInfo();
        info.setNickname(fishUserInfo.getNickname());
        info.setPlate(fishUserInfo.getPlate());
        info.setUser_general_data(fishUserInfo.getUser_general_data());
        info.setUsername(fishUserInfo.getUsername());
        info.setAdditional_rating(fishUserInfo.getAdditional_rating());
        if (b50) {
            info.setCharts(new BestFishRecord(
                    score.getScores_b15().stream().map(BeanConvent::toRecord).toList()
                    ,score.getScores_b35().stream().map(BeanConvent::toRecord).toList()
            ));
            info.setRating(score.getRating());
        } else {
            List<FishRecord> b40 = new ArrayList<>(40);
            List<FishRecord> b15 = score.getScores_b15().stream().map(BeanConvent::toRecord).toList();
            List<FishRecord> b25 = score.getScores_b35().subList(0,25).stream().map(BeanConvent::toRecord).toList();
            info.setCharts(new BestFishRecord(b15,b25));
            b40.addAll(b15);
            b40.addAll(b25);
            info.setRating(b40.stream().map(FishRecord::getRa).reduce(0, Integer::sum));
        }
        return info;
    }

    /**
     * 用户成绩信息
     */
    public static UserRecordInfo toRecordsInfo(List<ScoreInfo> scores, FishUserInfo fishUserInfo) {
        UserRecordInfo info = new UserRecordInfo();
        info.setNickname(fishUserInfo.getNickname());
        info.setPlate(fishUserInfo.getPlate());
        info.setUsername(fishUserInfo.getUsername());
        info.setAdditional_rating(fishUserInfo.getAdditional_rating());
        List<FishRecord> fishRecords = scores.stream().map(BeanConvent::toRecord).toList();
        info.setRating(scores.stream().map(ScoreInfo::getDx_rating).reduce(0, Integer::sum));
        info.setRecords(fishRecords);
        return info;
    }

    public static FishRecord toRecord(ScoreInfo score) {
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
        record.setSong_id(toFishStyleId(score.getId()));
        record.setTitle(score.getTitle());
        record.setType(StrUtil.conventDXType(score.getType()));
        return record;
    }

    /*
        TODO 缺少：定数 rating dx分
     */
    public static List<FishRecord> toRecord(PlateInfo plateInfo) {
        List<PlateScoreInfo> scores = plateInfo.getScores();
        List<FishRecord> fishRecords = new ArrayList<>(scores.size());
        for (PlateScoreInfo score : scores) {
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


    public static Integer toFishStyleId(Integer lxnsStyleId) {
        if (lxnsStyleId == null) return null;
        int id = lxnsStyleId;
        if (id > 100000) {
            //宴谱取后四位
            int sid = id % 10000;
            //如果是DX还得转换
            if (sid > 1000) {
                //DX谱为1xxxx
                return sid + 10000;
            }
            return sid;
        }
        if (id > 1000) {
            //DX谱为1xxxx
            return id + 10000;
        }
        //标谱id一致
        return id;
    }
}
