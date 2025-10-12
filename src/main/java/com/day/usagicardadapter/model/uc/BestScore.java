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

    public void setB15(List<ScoreInfo> scores_b15){
        this.scores_b15 = scores_b15;
        this.rating_b15 = scores_b15.stream().map(ScoreInfo::getDx_rating).reduce(0,Integer::sum);
        if(this.rating == null) this.rating = 0;
        this.rating+=this.rating_b15;
    }

    public void setB35(List<ScoreInfo> scores_b35){
        this.scores_b35 = scores_b35;
        this.rating_b35 = scores_b35.stream().map(ScoreInfo::getDx_rating).reduce(0,Integer::sum);
        if(this.rating == null) this.rating = 0;
        this.rating+=this.rating_b35;
    }
}
