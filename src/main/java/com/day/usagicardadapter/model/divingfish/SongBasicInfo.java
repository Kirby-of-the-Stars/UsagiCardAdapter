package com.day.usagicardadapter.model.divingfish;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SongBasicInfo {
    private String title;
    private String artist;
    private String genre;
    private Integer bpm;
    private String release_date;
    private String from;
    private Boolean is_new;
}
