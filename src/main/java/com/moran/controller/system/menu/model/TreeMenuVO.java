package com.moran.controller.system.menu.model;

import com.moran.model.SysMenu;
import lombok.Data;

import java.util.List;

@Data
public class TreeMenuVO {
    private Object id;
    private String title;
    private String icon;
    private List<TreeMenuVO> children;

    public static List<TreeMenuVO> convert(List<SysMenu> list) {
        return list.stream().filter(m -> m.getType() == 1 && m.getParentId() == null)
                .map(m -> {
                    TreeMenuVO vo = new TreeMenuVO();
                    vo.setId(m.getId());
                    vo.setTitle(m.getTitle());
                    vo.setIcon(m.getIcon());
                    vo.setChildren(findChildren(m.getId(), list));
                    return vo;
                }).toList();
    }

    private static List<TreeMenuVO> findChildren(Long parentId, List<SysMenu> list) {
        return list.stream().filter(m -> parentId.equals(m.getParentId()))
                .map(m -> {
                    TreeMenuVO vo = new TreeMenuVO();
                    vo.setId(m.getId());
                    vo.setTitle(m.getTitle());
                    vo.setIcon(m.getIcon());
                    vo.setChildren(findChildren(m.getId(), list));
                    return vo;
                }).toList();
    }
}
