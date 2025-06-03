package com.day.usagicardadapter.model.divingfish;

import lombok.Data;

import java.util.List;

/**
 * 歌曲的谱面信息
 */
@Data
public class SongChart {
    private List<String> notes;
    private String charter;
}
