package com.day.usagicardadapter.model.divingfish;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class UserRecordInfo {
    private Integer additional_rating;
    private String nickname;
    private String plate;
    private Integer rating;
    private List<FishRecord> fishRecords;
}
