package com.day.usagicardadapter.model.uc;

import com.day.usagicardadapter.model.DifficultyType;
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

    public boolean isHas(DifficultyType type) {
        if (difficulties == null) return false;
        return switch (type) {
            case STANDARD -> difficulties.getStandard() != null && !difficulties.getStandard().isEmpty();
            case DX -> difficulties.getDx() != null && !difficulties.getDx().isEmpty();
            case UTAG -> difficulties.getUtage() != null && !difficulties.getUtage().isEmpty();
        };
    }
}
