package com.moran.controller.system.dict.model;

import com.mzt.logapi.starter.annotation.DiffLogAllFields;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.apache.ibatis.annotations.Update;

@Data
@DiffLogAllFields
public class DictTypeDTO {

    /**
     * ID
     */
    @NotNull(message = "请选择字典", groups = Update.class)
    private Long id;
    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;
    /**
     * 类型
     */
    @NotBlank(message = "类型不能为空")
    private String type;
}
