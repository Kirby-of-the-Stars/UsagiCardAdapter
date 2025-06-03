package com.day.usagicardadapter.model.uc;

import lombok.Data;

import java.util.List;

@Data
public class DifficultyInfo {
    private String type;
    private String level;
    private Float level_value;
    private Integer level_index;
    private String note_designer;
    private Integer version;
    private Integer tap_num;
    private Integer hold_num;
    private Integer slide_num;
    private Integer touch_num;
    private Integer break_num;
    private DifficultyCurve curve;


}
