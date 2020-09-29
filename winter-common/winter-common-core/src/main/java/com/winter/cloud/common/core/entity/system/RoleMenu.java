package com.winter.cloud.common.core.entity.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("t_role_menu")
@Data
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = -2011747160860028020L;

    @TableId(value = "ROLE_ID")
    private Long roleId;
    @TableId(value = "MENU_ID")
    private Long menuId;
}
