package com.day.usagicardadapter.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import org.noear.snack.ONode;
import org.noear.snack.core.Feature;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Init;
import org.noear.solon.annotation.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

@Component
public class VersionUtils {

    private static final Logger log = LoggerFactory.getLogger(VersionUtils.class);

    @Inject("${app.usagi.version-file-path}")
    private String versionFilePath;

    private List<String> versionList;

    @Init
    public void init() {
        log.info("Version json路径: {}",versionFilePath);
        try{
            File versionFile = new File(versionFilePath);
            log.info("version json绝对路径: {}", versionFile.getAbsolutePath());
            if(!versionFile.exists()) {
                log.warn("version json不存在，正在创建默认列表");
                FileUtil.touch(versionFile);
                FileUtil.writeUtf8String(ONode.load(DEFAULT_VERSIONS).toJson(), versionFile);
            }
            versionList = ONode.loadStr(FileUtil.readUtf8String(versionFile), Feature.SerializeNulls).toObjectList(String.class);
            return;
        }catch (IORuntimeException e){
            log.error("IO异常",e);
        }
        versionList = DEFAULT_VERSIONS;
    }

    public List<String> getAllVersion(){
        return versionList;
    }

    public List<String> getCurrentVersions(){
        return versionList.subList(versionList.size()-2, versionList.size()-1);
    }
    public List<String> getOldVersions(){
        return versionList.subList(0, versionList.size()-2);
    }


    private final static List<String> DEFAULT_VERSIONS =
            List.of(
                    "maimai",
                    "maimai PLUS",
                    "maimai GreeN",
                    "maimai GreeN PLUS",
                    "maimai ORANGE",
                    "maimai ORANGE PLUS",
                    "maimai PiNK",
                    "maimai PiNK PLUS",
                    "maimai MURASAKi",
                    "maimai MURASAKi PLUS",
                    "maimai MiLK",
                    "maimai MiLK PLUS",
                    "maimai FiNALE",
                    "ALL FiNALE",
                    "舞萌DX",
                    "舞萌DX2021",
                    "舞萌DX2022",
                    "舞萌DX2023",
                    "舞萌DX2024",
                    "舞萌DX2025"
            );
}
