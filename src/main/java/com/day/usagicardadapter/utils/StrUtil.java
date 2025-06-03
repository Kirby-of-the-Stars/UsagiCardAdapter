package com.day.usagicardadapter.utils;

public class StrUtil {

    public static String conventIntFc(int fc){
        return switch (fc) {
            case 1 -> "fc";
            case 2 -> "fcp";
            case 3 -> "ap";
            case 4 -> "app";
            default -> "";
        };
    }
    public static String conventIntFs(int fs){
        return switch (fs) {
            case 1 -> "fs";
            case 2 -> "fsp";
            case 3 -> "fsdp";
            default -> "";
        };
    }
    public static String conventLevelStr(int level_index){
        return switch (level_index) {
            case 0 -> "Basic";
            case 1 -> "Advanced";
            case 2 -> "Expert";
            case 3 -> "Master";
            case 4 -> "Re:Master";
            default -> "";
        };
    }
    public static String conventIntRate(int rate){
        return switch (rate){
            case 0 -> "sssp";
            case 1 -> "sss";
            case 2 -> "ssp";
            case 3 -> "ss";
            case 4 -> "sp";
            case 5 -> "s";
            case 6 -> "aaa";
            case 7 -> "aa";
            case 8 -> "a";
            case 9 -> "b";
            case 10 -> "c";
            case 11 -> "d";
            default -> "";
        };
    }
}
