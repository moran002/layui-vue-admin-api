package com.moran.controller.system.role;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.moran.conf.bean.OptionVO;
import com.moran.conf.bean.ResponseBean;
import com.moran.model.SysRole;
import com.moran.service.SysRoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/role")
@AllArgsConstructor
public class RoleController {
    private final SysRoleService sysRoleService;
    /**
     * 角色字典
     */
    @GetMapping("/simple-list")
    @SaCheckLogin
    public ResponseBean<List<OptionVO>> simpleList() {
        List<SysRole> roles = sysRoleService.findAll();
        return ResponseBean.ok(roles.stream().map(r -> OptionVO.convert(r.getId(), r.getName())).toList());
    }
}
