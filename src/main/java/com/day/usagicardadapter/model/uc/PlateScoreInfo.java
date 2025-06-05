package com.day.usagicardadapter.model.uc;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PlateScoreInfo {
    private Integer id;
    private Integer level_index;
    private BigDecimal achievement;
    private Integer fc;
    private Integer fs;
    private Integer rate;
    private String type;
}
