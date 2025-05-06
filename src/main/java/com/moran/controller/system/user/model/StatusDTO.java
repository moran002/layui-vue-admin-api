package com.moran.controller.system.user.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StatusDTO {
    /**
     * ID
     */
    @NotNull(message = "请选择用户")
    private Long id;
    /**
     * 状态
     */
    @NotNull(message = "请输入状态")
    private Boolean status;
}
