package com.day.usagicardadapter.model.divingfish;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BestFishRecord {
    private List<FishRecord> dx;
    private List<FishRecord> sd;
}
