package com.winter.cloud.common.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUser implements Serializable {

    private static final long serialVersionUID = 3051641262948412612L;

    @JsonIgnore
    private String password;
    private String username;
    @JsonIgnore
    private Set<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Long userId;
    private String avatar;
    private String email;
    private String mobile;
    private String sex;
    private Long deptId;
    private String deptName;
    private String roleId;
    private String roleName;
    @JsonIgnore
    private Date lastLoginTime;
    private String description;
    private String status;
    private String deptIds;
}
