package com.day.usagicardadapter.utils;

public class StrUtil {

    public static String conventIntFc(Integer fc){
        return switch (fc) {
            case 3 -> "fc";
            case 2 -> "fcp";
            case 1 -> "ap";
            case 0 -> "app";
            case null, default -> "";
        };
    }
    public static String conventIntFs(Integer fs){
        return switch (fs) {
            case 0 -> "sync";
            case 1 -> "fs";
            case 2 -> "fsp";
            case 3 -> "fsd";
            case 4 -> "fsdp";
            case null, default -> "";
        };
    }
    public static String conventLevelStr(Integer level_index){
        return switch (level_index) {
            case 0 -> "Basic";
            case 1 -> "Advanced";
            case 2 -> "Expert";
            case 3 -> "Master";
            case 4 -> "Re:Master";
            case null, default -> "";
        };
    }
    public static String conventIntRate(Integer rate){
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
            case null, default -> "";
        };
    }
}
