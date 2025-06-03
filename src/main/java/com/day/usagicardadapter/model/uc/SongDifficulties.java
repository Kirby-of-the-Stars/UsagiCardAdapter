package com.day.usagicardadapter.model.uc;

import com.day.usagicardadapter.model.DifficultyType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SongDifficulties {

    private List<DifficultyInfo> standard;

    private List<DifficultyInfo> dx;

    private List<DifficultyInfo> utage;

    public List<Float> getLevelVales(DifficultyType type){
        List<Float> result = new ArrayList<>();
        switch (type){
            case STANDARD:
                standard.forEach(diff->result.add(diff.getLevel_value()));break;
            case DX:
                dx.forEach(diff->result.add(diff.getLevel_value()));break;
            case UTAG:
                utage.forEach(diff->result.add(diff.getLevel_value()));break;
        }
        return result;
    }

    /**
     * 获取定数列表(小数点)
     * 按SD DX UTAGE的顺序获取
     */
    public List<Float> getLevelVales(){
        List<Float> result = new ArrayList<>();
        if(standard!=null && !standard.isEmpty()){
            standard.forEach(difficulty-> result.add(difficulty.getLevel_value()));
            return result;
        }
        if(dx != null && !dx.isEmpty()){
            dx.forEach(difficulty-> result.add(difficulty.getLevel_value()));
            return result;
        }
        if(utage != null && !utage.isEmpty()){
            utage.forEach(difficulty-> result.add(difficulty.getLevel_value()));
        }
        return result;
    }

    /**
     * 获取显式等级列表(即不带小数点)
     */
    public List<String> getLevelStr(DifficultyType type){
        List<String> result = new ArrayList<>();
        switch (type){
            case STANDARD:
                standard.forEach(diff->result.add(diff.getLevel()));break;
            case DX:
                dx.forEach(diff->result.add(diff.getLevel()));break;
            case UTAG:
                utage.forEach(difficulty-> result.add(difficulty.getLevel()));break;
        }
        return result;
    }
}
