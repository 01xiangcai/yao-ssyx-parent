package com.yao.ssyx.acl.controller;

import com.yao.ssyx.acl.service.PermissionService;
import com.yao.ssyx.acl.service.RolePermissionService;
import com.yao.ssyx.acl.service.RoleService;
import com.yao.ssyx.common.result.Result;
import com.yao.ssyx.model.acl.Permission;
import com.yao.ssyx.model.acl.RolePermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @className: PermissionAdminController
 * @Description: TODO
 * @author: long
 * @date: 2023-10-18 14:48
 */
@RestController
@RequestMapping("/admin/acl/permission")
@Api(tags = "菜单管理")
public class PermissionAdminController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @ApiOperation(value = "获取菜单")
    @GetMapping
    public Result index() {
        List<Permission> list = permissionService.queryAllMenu();
        return Result.ok(list);
    }

    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public Result save(@RequestBody Permission permission) {
        permission.setPid(permission.getParentId());
        permissionService.save(permission);
        return Result.ok();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public Result updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return Result.ok();
    }

    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        permissionService.removeChildById(id);
        return Result.ok();
    }


    @ApiOperation(value = "查看某个角色的权限列表")
    @GetMapping("toAssign/{roleId}")
    public Result findRolePermission(@PathVariable Long roleId){
        List<Permission> permissions = rolePermissionService.findRolePermissionByRoleId(roleId);
        return Result.ok(permissions);
    }

    @ApiOperation(value = "给某个角色授权")
    @PostMapping("doAssign")
    public Result doAssign(@RequestParam Long roleId ,@RequestParam  Long[] permissionId){
        rolePermissionService.doAssign(roleId,permissionId);
        return Result.ok();
    }

}
