package com.moran.controller.system.dict;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moran.conf.bean.PageResponseBean;
import com.moran.conf.bean.ResponseBean;
import com.moran.controller.system.dict.vo.DictDataDTO;
import com.moran.controller.system.dict.vo.DictDataVO;
import com.moran.controller.system.dict.vo.DictTypeDTO;
import com.moran.controller.system.dict.vo.DictTypeVO;
import com.moran.model.SysDictData;
import com.moran.model.SysDictType;
import com.moran.service.SysDictDataService;
import com.moran.service.SysDictTypeService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典
 */
@RestController
@RequestMapping("/system/dict")
@AllArgsConstructor
public class DictController {
    private final SysDictTypeService dictTypeService;
    private final SysDictDataService dictDataService;

    /**
     * 删除
     */
    @DeleteMapping("/del")
    @SaCheckPermission("system:dict:del")
    public ResponseBean<Boolean> delDictType(@Validated @NotNull(message = "请选择类型") Long id) {
        dictTypeService.removeById(id);
        return ResponseBean.ok(true);
    }

    /**
     * 更新数据
     */
    @PostMapping("/updateData")
    @SaCheckPermission("system:dict:update")
    public ResponseBean<Boolean> updateDictData(@Validated(Update.class) @RequestBody DictDataDTO dto) {
        dictDataService.updateDictData(dto);
        return ResponseBean.ok(true);
    }

    /**
     * 新增数据
     */
    @PostMapping("/createData")
    @SaCheckPermission("system:dict:create")
    public ResponseBean<Boolean> createDictData(@Validated @RequestBody DictDataDTO dto) {
        dictDataService.createDictData(dto);
        return ResponseBean.ok(true);
    }

    /**
     * 数据列表
     */
    @GetMapping("/data-list")
    @SaCheckPermission("system:dict:query")
    public ResponseBean<List<DictDataVO>> queryDictDataList(String dictType) {
        List<SysDictData> list = dictDataService.getDataByType(dictType);
        return ResponseBean.ok(list.stream().map(d -> BeanUtil.toBean(d, DictDataVO.class)).toList());
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    @SaCheckPermission("system:dict:update")
    public ResponseBean<Boolean> updateDictType(@Validated(Update.class) @RequestBody DictTypeDTO dto) {
        dictTypeService.updateDictType(dto);
        return ResponseBean.ok(true);
    }

    /**
     * 新增
     */
    @PostMapping("/create")
    @SaCheckPermission("system:dict:create")
    public ResponseBean<Boolean> createDictType(@Validated @RequestBody DictTypeDTO dto) {
        dictTypeService.createDictType(dto);
        return ResponseBean.ok(true);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    @SaCheckPermission("system:dict:query")
    public PageResponseBean<DictTypeVO> list(String name, String type) {
        Page<SysDictType> page = dictTypeService.dictPage(name, type);
        if (page.getTotal() <= 0) {
            return PageResponseBean.ok();
        }
        return PageResponseBean.ok(page.getTotal(),
                page.getRecords().stream().map(t -> BeanUtil.toBean(t, DictTypeVO.class)).toList());
    }
}
