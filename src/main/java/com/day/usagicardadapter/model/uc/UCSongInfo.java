package com.day.usagicardadapter.model.uc;

import lombok.Data;

import java.util.List;

@Data
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

}
