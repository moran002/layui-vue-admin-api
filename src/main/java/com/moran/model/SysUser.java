package com.moran.model;

import com.moran.conf.mybatis.handler.LongListTypeHandler;
import io.mybatis.provider.Entity;
import org.apache.ibatis.type.JdbcType;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * sys_user - 用户表
 *
 * @author 系统自动生成
 */
@Getter
@Setter
@Entity.Table(value = "sys_user", remark = "用户表", autoResultMap = true)
public class SysUser {
    @Entity.Column(value = "id", id = true, remark = "用户ID", updatable = false, insertable = false, useGeneratedKeys = true)
    private Long id;

    @Entity.Column(value = "account", remark = "账号")
    private String account;

    @Entity.Column(value = "password", remark = "密码")
    private String password;

    @Entity.Column(value = "nick_name", remark = "用户昵称")
    private String nickName;

    @Entity.Column(value = "avatar", remark = "头像")
    private String avatar;

    @Entity.Column(value = "mobile", remark = "手机号")
    private String mobile;

    @Entity.Column(value = "email", remark = "邮箱")
    private String email;

    @Entity.Column(value = "remark", remark = "备注")
    private String remark;

    @Entity.Column(value = "role_ids", remark = "角色ID集合", typeHandler = LongListTypeHandler.class)
    private List<Long> roleIds;

    @Entity.Column(value = "status", remark = "状态")
    private Boolean status;

    @Entity.Column(value = "error_count", remark = "密码错误次数")
    private Integer errorCount;

    @Entity.Column(value = "create_time", remark = "创建时间", jdbcType = JdbcType.TIMESTAMP)
    private Date createTime;

    @Entity.Column(value = "create_by", remark = "创建人")
    private Long createBy;

    @Entity.Column(value = "update_time", remark = "更新时间", jdbcType = JdbcType.TIMESTAMP)
    private Date updateTime;

    @Entity.Column(value = "update_by", remark = "更新人")
    private Long updateBy;

}
