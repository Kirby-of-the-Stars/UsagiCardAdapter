package com.day.usagicardadapter.model.divingfish;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FishRecord {
    private BigDecimal achievements;
    private Float ds;
    private Integer dxScore;
    private String fc;
    private String fs;
    private String level;
    private Integer level_index;
    private String level_label;
    private Integer ra;
    private String rate;
    private Integer song_id;
    private String title;
    private String type;
}
