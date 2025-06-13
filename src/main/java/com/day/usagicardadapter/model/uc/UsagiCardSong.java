package com.day.usagicardadapter.model.uc;

import lombok.Data;

import java.util.List;

@Data
public class UsagiCardSong {
    private UCSongInfo song;
    private List<ScoreInfo> scores;
}
