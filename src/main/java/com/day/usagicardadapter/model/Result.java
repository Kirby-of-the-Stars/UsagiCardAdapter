package com.day.usagicardadapter.model;

import lombok.Data;

@Data
public class Result<T> {
    private String message;
    private T data;

    public static <T> Result<T> success(T obj){
        return new Result<>("ok",obj);
    }

    public static Result fail(String message){
        return new Result(message, null);
    }

    private Result(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
