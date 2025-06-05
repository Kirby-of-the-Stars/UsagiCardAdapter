package com.day.usagicardadapter.model.uc;

import lombok.Data;

import java.util.List;
@Data
public class PlateInfo {
    private PlateSongInfo song;
    private List<PlateScoreInfo> scores;
}
