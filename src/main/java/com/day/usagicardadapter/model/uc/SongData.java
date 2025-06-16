package com.day.usagicardadapter.model.uc;

import com.day.usagicardadapter.model.divingfish.SongInfo;
import lombok.Data;

import java.util.List;

@Data
public class SongData {
    private List<SongInfo> songs;
    private long total;

    public SongData(List<SongInfo> songs, long total) {
        this.songs = songs;
        this.total = total;
    }

    public SongData() {
    }
}
