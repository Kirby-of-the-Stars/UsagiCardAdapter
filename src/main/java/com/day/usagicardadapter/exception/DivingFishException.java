package com.day.usagicardadapter.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.noear.snack.ONode;


@EqualsAndHashCode(callSuper = false)
@Data
public class DivingFishException extends RuntimeException {
    private ONode node;//返回body
    private int code;//返回code
    private boolean exception;
    private Exception e;//请求异常

    public DivingFishException() {
        super("水鱼返回非预期状态");
    }

    public DivingFishException(ONode node, int code) {
        super("水鱼返回非预期状态");
        this.node = node;
        this.code = code;
    }

    public DivingFishException(Exception e) {
        super("水鱼返回非预期状态",e);
        this.e = e;
        this.exception = true;
    }
}
