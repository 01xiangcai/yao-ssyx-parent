package com.yao.ssyx.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yao.ssyx.model.acl.Role;
import com.yao.ssyx.vo.acl.RoleQueryVo;

import java.util.Map;

/**
 * @className: RoleService
 * @Description: TODO
 * @author: long
 * @date: 2023-10-17 21:37
 */
public interface RoleService extends IService<Role> {


    IPage<Role> selectPage(Page<Role> pageParam, RoleQueryVo roleQueryVo);

    Map<String, Object> findRoleByUserId(Long adminId);

    void saveUserRoleRealtionShip(Long adminId, Long[] roleId);
}
