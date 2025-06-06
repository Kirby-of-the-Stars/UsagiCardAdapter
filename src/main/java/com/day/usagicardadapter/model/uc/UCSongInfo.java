package com.day.usagicardadapter.model.uc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UCSongInfo {
    private Integer id;
    private String title;
    private String artist;
    private String genre;
    private Integer bpm;
    private String map;
    private Integer version;
    private String rights;
    private List<String> aliases;
    private Boolean disabled;
    private SongDifficulties difficulties;
    private List<ScoreInfo> scores;
}
