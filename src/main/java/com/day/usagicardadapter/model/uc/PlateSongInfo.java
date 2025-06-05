package com.day.usagicardadapter.model.uc;

import lombok.Data;

import java.util.List;

@Data
public class PlateSongInfo {
    private Integer id;
    private String title;
    private String artist;
    private List<Integer> levels;
}
