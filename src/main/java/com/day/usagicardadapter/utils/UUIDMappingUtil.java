package com.day.usagicardadapter.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import com.day.usagicardadapter.model.divingfish.FishUserInfo;
import lombok.Getter;
import org.noear.snack.ONode;
import org.noear.snack.core.Feature;
import org.noear.snack.core.Options;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Init;
import org.noear.solon.annotation.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UUIDMappingUtil {

    private static final Logger log = LoggerFactory.getLogger(UUIDMappingUtil.class);

    @Inject("${app.usagi.fish.uuid-mapping-file-path}")
    private String fishMappingPath;

    @Getter
    private Map<String, FishUserInfo> userUuidMap;

    private File fishMappingFile;

    @Init
    public void init() {
        log.info("水鱼uuid映射json路径: {}",fishMappingPath);
        try{
            fishMappingFile = new File(fishMappingPath);
            log.info("水鱼uuid映射json绝对路径: {}",fishMappingFile.getAbsolutePath());
            if(!fishMappingFile.exists()) {
                log.warn("水鱼uuid映射json不存在，正在创建");
                FileUtil.touch(fishMappingFile);
                FileUtil.writeUtf8String("{}",fishMappingFile);
            }
            //read mapping file
            Map<String,ONode> nodeMap = ONode.loadStr(FileUtil.readUtf8String(fishMappingFile), Feature.SerializeNulls).obj();
            userUuidMap = new ConcurrentHashMap<>();
            nodeMap.forEach((s,o)->{
                userUuidMap.put(s,o.toObject(FishUserInfo.class));
            });
            return;
        }catch (IORuntimeException e){
            log.error("IO异常",e);
        }
        userUuidMap = new ConcurrentHashMap<>();
    }
    /**
     * 检查是否存在指定用户名的映射
     * @param username 用户名
     * @return 如果存在则返回true，否则返回false
     */
    public boolean has(String username) {
        return userUuidMap.containsKey(username);
    }
    /**
     * 获取指定用户的UUID
     * @param username 用户名
     * @return 对应的UUID，如果不存在则返回null
     */
    public FishUserInfo get(String username) {
        try {
            return userUuidMap.get(username);
        }catch (Exception e){
            log.error("无法获取水鱼用户信息:{}",username);
            throw e;
        }
    }
    /**
     * 设置或更新一个用户的UUID。此操作是线程安全的，并且会立即持久化到JSON文件。
     * @param username 用户名
     * @param userInfo 水鱼的用户信息+UUID
     */
    public synchronized void set(String username, FishUserInfo userInfo) {
        userUuidMap.put(username, userInfo);
        persistToFile();
    }
    /**
     * 删除一个用户的UUID映射。
     * @param username 用户名
     */
    public synchronized void remove(String username) {
        if (userUuidMap.remove(username) != null) {
            persistToFile();
            log.info("已删除用户 '{}' 的UUID并持久化到文件。", username);
        }
    }

    /**
     * 将内存中的Map持久化到JSON文件。
     * 这是一个私有方法，由写操作（set, remove）同步调用。
     */
    private void persistToFile() {
        try {
            FileUtil.writeUtf8String(ONode.stringify(userUuidMap, Options.of(Feature.SerializeNulls)), fishMappingFile);
        } catch (IORuntimeException e) {
            log.error("无法将用户UUID数据持久化到文件: {}", fishMappingFile.getAbsolutePath(), e);
        }
    }

}
