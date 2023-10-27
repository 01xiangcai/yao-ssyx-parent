package com.yao.ssyx.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yao.ssyx.acl.mapper.RolePermissionMapper;
import com.yao.ssyx.acl.service.PermissionService;
import com.yao.ssyx.acl.service.RolePermissionService;
import com.yao.ssyx.model.acl.Permission;
import com.yao.ssyx.model.acl.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @className: RolePermissionServiceImpl
 * @Description: TODO
 * @author: long
 * @date: 2023-10-18 15:13
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Autowired
    private PermissionService permissionService;

    @Override
    public List<Permission> findRolePermissionByRoleId(Long roleId) {
        List<RolePermission> rolePermissions = baseMapper.selectList(new LambdaQueryWrapper<RolePermission>().eq(RolePermission::getRoleId, roleId));

        List<Permission> permissions = new ArrayList<>();
        if (!CollectionUtils.isEmpty(rolePermissions)) {
            List<Long> permissionIds = rolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());
            permissions = permissionService.listByIds(permissionIds);
        }


        return permissions;
    }


    @Override
    public void doAssign(Long roleId, Long[] permissionIds) {

            List<RolePermission> rolePermissionList = new ArrayList<RolePermission>();
            for (Long permissionId : permissionIds) {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setPermissionId(permissionId);
                rolePermission.setRoleId(roleId);
                rolePermissionList.add(rolePermission);
            }
            this.saveBatch(rolePermissionList);

    }




}
