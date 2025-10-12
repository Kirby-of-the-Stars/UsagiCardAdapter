package com.day.usagicardadapter.api;

import com.day.usagicardadapter.model.SetupUserInfoReq;
import com.day.usagicardadapter.model.divingfish.FishUserInfo;
import com.day.usagicardadapter.utils.UUIDMappingUtil;
import org.noear.solon.annotation.*;

import java.util.Map;

@Controller
@Mapping("adapter/cfg")
public class AdapterConfigController {

    @Inject
    UUIDMappingUtil uuidMappingUtil;

    @Get
    @Mapping("mapping")
    public FishUserInfo getMapping(String source, String qq){
        return switch (source) {
            case "qq" -> uuidMappingUtil.get(qq);
            default -> throw new IllegalStateException("Unexpected source value: " + source);
        };
    }

    @Post
    @Mapping("mapping")
    public boolean postMapping(SetupUserInfoReq req){
         switch (req.getSource()) {
            case "qq" -> uuidMappingUtil.set(req.getQq(),req.getInfo());
            default -> throw new IllegalStateException("Unexpected source value: " + req.getSource());
        };
         return true;
    }

    @Get
    @Mapping("mapping/all")
    public Map<String, FishUserInfo> getAllMapping(String source){
        return uuidMappingUtil.getUserUuidMap();
    }
}
