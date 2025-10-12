package com.day.usagicardadapter.model.uc;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BestScore {
    private List<ScoreInfo> scores_b35;
    private List<ScoreInfo> scores_b15;
    private Integer rating_b35;
    private Integer rating_b15;
    private Integer rating;


    public List<ScoreInfo> getAllScores(){
        List<ScoreInfo> list = new ArrayList<>(scores_b35.size() + scores_b15.size());
        list.addAll(scores_b35);
        list.addAll(scores_b15);
        return list;
    }
}
