package com.day.usagicardadapter.model.uc;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class ScoreInfo {
    private Integer song_id;
    private String song_name;
    private String level;
    private Integer level_index;
    private Float level_value;
    private Integer level_dx_score;
    private BigDecimal achievements;
    private Integer fc;
    private Integer fs;
    private Integer dx_score;
    private Integer dx_rating;
    private Integer rate;
    private String type;
}
