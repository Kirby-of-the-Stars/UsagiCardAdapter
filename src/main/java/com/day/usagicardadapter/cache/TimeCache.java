package com.day.usagicardadapter.cache;

import java.util.concurrent.TimeUnit;

public abstract class TimeCache<T> {
    private T value;
    private long updatedTime;
    private final long expiredTime;

    public TimeCache(TimeUnit unit,long duration) {
        this.expiredTime = unit.toMillis(duration);
    }

    public void put(T value) {
        put(value,true);
    }

    public void put(T value,boolean updateExpiredTime) {
        this.value = value;
        if(updateExpiredTime) this.updatedTime = System.currentTimeMillis();
    }

    public T get() throws IllegalAccessException {
        return get(false);
    }

    public T get(boolean updateExpiredTime) throws IllegalAccessException {
        if(value == null||this.isExpired()) throw new IllegalAccessException("cache is empty or expired");
        if(updateExpiredTime) this.updatedTime = System.currentTimeMillis();
        return value;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > this.updatedTime+this.expiredTime;
    }
    public boolean isEmpty(){
        return value == null;
    }

}
