package com.day.usagicardadapter.model.uc;

import com.day.usagicardadapter.model.divingfish.FishRecordWithPC;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BestPlayCountRecord {
    private List<FishRecordWithPC> dx;
    private List<FishRecordWithPC> sd;
}
