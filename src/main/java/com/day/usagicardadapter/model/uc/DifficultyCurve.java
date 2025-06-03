package com.day.usagicardadapter.model.uc;

import lombok.Data;

@Data
public class DifficultyCurve {
    private Integer sample_size;
    private Integer fit_level_value;
    private Integer avg_achievements;
    private Integer avg_dx_score;
    private SampleSize rate_sample_size;
    private SampleSize fc_sample_size;
}
