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

    public static String conventVersion(String version){
        return switch (version) {
            case "maimai PLUS" -> "真";
            case "maimai GreeN" -> "超";
            case "maimai GreeN PLUS" -> "檄";
            case "maimai ORANGE" -> "橙";
            case "maimai ORANGE PLUS" -> "暁";
            case "maimai PiNK" -> "桃";
            case "maimai PiNK PLUS" -> "櫻";
            case "maimai MURASAKi" -> "紫";
            case "maimai MURASAKi PLUS" -> "菫";
            case "maimai MiLK" -> "白";
            case "MiLK PLUS" -> "雪";
            case "maimai FiNALE" -> "輝";
            case "ALL FiNALE" -> "舞";
            case "maimai でらっくす" -> "熊";
            case "maimai でらっくす PLUS" -> "華";
            case "maimai でらっくす Splash" -> "爽";
            case "maimai でらっくす Splash PLUS" -> "煌";
            case "maimai でらっくす UNiVERSE" -> "宙";
            case "maimai でらっくす UNiVERSE PLUS" -> "星";
            case "maimai でらっくす FESTiVAL" -> "祭";
            case "maimai でらっくす FESTiVAL PLUS" -> "祝";
            case null, default -> "";
        };
    }
    public static String conventDXType(String type){
      return switch (type){
            case "stander" -> "SD";
            case "dx" ->"DX";
            case null, default -> "";
        };
    }
}
