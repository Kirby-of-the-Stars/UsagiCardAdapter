package com.day.usagicardadapter.model.divingfish;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class SongInfo {
    private String id;
    private String title;
    private String type;
    private List<Float> ds;
    private List<String> level;
    private List<Integer> cids;
    private List<SongChart> charts;
    private SongBasicInfo basic_info;
}
