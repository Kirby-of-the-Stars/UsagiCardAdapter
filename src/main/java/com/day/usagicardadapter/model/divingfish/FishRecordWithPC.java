package com.day.usagicardadapter.model.divingfish;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class FishRecordWithPC extends FishRecord {
    private Integer play_count;
}
