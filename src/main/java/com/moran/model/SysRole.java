package com.moran.model;

import com.moran.conf.mybatis.handler.LongListTypeHandler;
import io.mybatis.provider.Entity;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

/**
 * sys_role - 角色表
 *
 * @author 系统自动生成
 */
@Getter
@Setter
@Entity.Table(value = "sys_role", remark = "角色表", autoResultMap = true)
public class SysRole {
    @Entity.Column(value = "id", id = true, remark = "角色ID", updatable = false, insertable = false, useGeneratedKeys = true)
    private Long id;

    @Entity.Column(value = "name", remark = "角色名称")
    private String name;

    @Entity.Column(value = "remark", remark = "备注")
    private String remark;

    @Entity.Column(value = "menu_ids", remark = "菜单ID集合", typeHandler = LongListTypeHandler.class)
    private List<Long> menuIds;

    @Entity.Column(value = "create_time", remark = "创建时间", jdbcType = JdbcType.TIMESTAMP)
    private Date createTime;

    @Entity.Column(value = "create_by", remark = "创建人")
    private Long createBy;

    @Entity.Column(value = "update_time", remark = "更新时间", jdbcType = JdbcType.TIMESTAMP)
    private Date updateTime;

    @Entity.Column(value = "update_by", remark = "更新人")
    private Long updateBy;

    @Entity.Column(value = "deleted", remark = "删除")
    private Boolean deleted;

}
