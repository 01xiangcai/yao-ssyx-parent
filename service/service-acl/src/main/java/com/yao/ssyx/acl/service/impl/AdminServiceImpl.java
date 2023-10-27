package com.yao.ssyx.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yao.ssyx.acl.mapper.AdminMapper;
import com.yao.ssyx.acl.service.AdminService;
import com.yao.ssyx.acl.service.RoleService;
import com.yao.ssyx.model.acl.Admin;
import com.yao.ssyx.vo.acl.AdminQueryVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: AdminServiceImpl
 * @Description: TODO
 * @author: long
 * @date: 2023-10-18 11:12
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {


    @Autowired
    private AdminMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Override
    public IPage<Admin> selectPage(Page<Admin> pageParam, AdminQueryVo userQueryVo) {
        //获取用户名称条件值
        String username = userQueryVo.getUsername();
        //创建条件构造器
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        if(!StringUtils.isEmpty(username)) {
            //封装条件
            wrapper.like(Admin::getUsername,username);
        }
        //调用mapper方法
        IPage<Admin> pageModel = baseMapper.selectPage(pageParam,wrapper);
        return pageModel;
    }


}
