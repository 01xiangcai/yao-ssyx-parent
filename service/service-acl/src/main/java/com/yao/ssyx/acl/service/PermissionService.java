package com.yao.ssyx.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yao.ssyx.model.acl.Permission;

import java.util.List;

/**
 * @className: PermissionService
 * @Description: TODO
 * @author: long
 * @date: 2023-10-18 14:36
 */
public interface PermissionService extends IService<Permission> {
    //获取所有菜单列表
    List<Permission> queryAllMenu();

    //递归删除
    boolean removeChildById(Long id);

}
