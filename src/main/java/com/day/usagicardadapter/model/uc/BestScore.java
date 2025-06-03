package com.day.usagicardadapter.model.uc;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BestScore {
    private List<ScoreInfo> b35_scores;
    private List<ScoreInfo> b15_scores;
    private Integer b35_rating;
    private Integer b15_rating;
    private Integer all_rating;


    public List<ScoreInfo> getAllScores(){
        List<ScoreInfo> list = new ArrayList<>(b35_scores.size() + b15_scores.size());
        list.addAll(b35_scores);
        list.addAll(b15_scores);
        return list;
    }
}
