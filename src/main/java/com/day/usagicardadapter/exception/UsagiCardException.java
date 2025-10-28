package com.day.usagicardadapter.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.noear.snack.ONode;

@EqualsAndHashCode(callSuper = false)
@Data
public class UsagiCardException extends RuntimeException {

    private ONode node;//返回body
    private int code;//返回code
    private boolean exception;
    private Exception e;//请求异常

    public UsagiCardException() {
        super();
    }

    public UsagiCardException(String message) {
        super(message);
    }
    public UsagiCardException(String message,Exception cause) {
        super(message,cause);
        this.e = cause;
        this.exception = true;
    }

}
