package com.day.usagicardadapter.model.divingfish;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.noear.solon.validation.annotation.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
public class FishUserInfo {
    @NotBlank(message = "uuid不能为空")
    private String uuid;
    private Integer additional_rating;
    @NotBlank(message = "nickname不能为空")
    private String nickname;
    private String plate;//水鱼好像不传这玩意
    private Object user_general_data;
    private String username;
}
