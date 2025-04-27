package com.moran.controller.auth.model;

import cn.hutool.core.bean.BeanUtil;
import com.moran.model.SysMenu;
import lombok.Data;

import java.util.List;

/**
 * @author : moran
 */
@Data
public class RouterVO {
    private String path;
    private String component;
    private Meta meta;
    private List<RouterVO> children;

    public static List<RouterVO> convert(List<SysMenu> menus) {
        return menus.stream()
                .filter(m -> m.getParentId() == null && m.getType() == 1)
                .map(m -> {
                    RouterVO vo = BeanUtil.toBean(m, RouterVO.class);
                    vo.setMeta(BeanUtil.toBean(m, Meta.class));
                    vo.setChildren(findChildren(m.getId(), menus));
                    return vo;
                }).toList();
    }

    private static List<RouterVO> findChildren(Long parentId, List<SysMenu> menus) {
        return menus.stream()
                .filter(m -> parentId.equals(m.getParentId()) && m.getType() == 1)
                .map(m -> {
                    RouterVO vo = BeanUtil.toBean(m, RouterVO.class);
                    vo.setMeta(BeanUtil.toBean(m, Meta.class));
                    vo.setChildren(findChildren(m.getId(), menus));
                    return vo;
                }).toList();
    }

    @Data
    public static class Meta {
        private String title;
        private String icon;
    }
}
