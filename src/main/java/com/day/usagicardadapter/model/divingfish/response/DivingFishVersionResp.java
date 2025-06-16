package com.day.usagicardadapter.model.divingfish.response;

import com.day.usagicardadapter.model.divingfish.FishRecord;
import lombok.Data;

import java.util.List;

@Data
public class DivingFishVersionResp {
    private List<FishRecord> verlist;

    public DivingFishVersionResp(List<FishRecord> verlist) {
        this.verlist = verlist;
    }
}
