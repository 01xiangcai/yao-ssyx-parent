package com.yao.ssyx.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yao.ssyx.model.acl.Permission;
import com.yao.ssyx.model.acl.RolePermission;

import java.util.List;

/**
 * @className: RolePermissionService
 * @Description: TODO
 * @author: long
 * @date: 2023-10-18 15:13
 */
public interface RolePermissionService extends IService<RolePermission> {
    List<Permission> findRolePermissionByRoleId(Long roleId);

    void doAssign(Long roleId, Long[] permissionIds);
}
