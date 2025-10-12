package com.day.usagicardadapter.model.uc;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BestPlayCountRecordInfo {
    private Integer additional_rating;
    private Object user_general_data;
    private String username;
    private String nickname;
    private String plate;
    private Integer rating;
    private BestPlayCountRecord charts;
}
