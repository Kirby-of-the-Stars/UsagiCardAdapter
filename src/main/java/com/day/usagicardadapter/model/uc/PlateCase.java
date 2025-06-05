package com.day.usagicardadapter.model.uc;

import lombok.Getter;
import org.noear.snack.annotation.ONodeAttr;

public enum PlateCase {
    REMAINED(0,"remained"),
    CLEARED(1,"cleared"),
    PLAYED(2,"played"),
    ALL(3,"all");

    private final int code;
    @Getter
    @ONodeAttr
    private final String name;

    PlateCase(int v,String str) {
        code=v;
        name=str;
    }
}
