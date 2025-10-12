package com.day.usagicardadapter.model;


import com.day.usagicardadapter.model.divingfish.FishUserInfo;
import lombok.Data;

@Data
public class SetupUserInfoReq {
    private String source;
    private String qq;
    private FishUserInfo info;
}
