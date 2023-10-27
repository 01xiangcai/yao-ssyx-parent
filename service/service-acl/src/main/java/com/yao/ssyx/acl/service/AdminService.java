package com.yao.ssyx.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yao.ssyx.model.acl.Admin;
import com.yao.ssyx.vo.acl.AdminQueryVo;

/**
 * @className: AdminService
 * @Description: TODO
 * @author: long
 * @date: 2023-10-18 11:12
 */
public interface AdminService extends IService<Admin> {
    IPage<Admin> selectPage(Page<Admin> pageParam, AdminQueryVo userQueryVo);
}
