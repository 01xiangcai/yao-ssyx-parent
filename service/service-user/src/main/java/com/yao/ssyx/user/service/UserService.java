package com.yao.ssyx.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yao.ssyx.model.user.User;
import com.yao.ssyx.vo.user.LeaderAddressVo;
import com.yao.ssyx.vo.user.UserLoginVo;

/**
 * @className: UserService
 * @Description: TODO
 * @author: long
 * @date: 2023-10-21 15:06
 */
public interface UserService extends IService<User> {

    LeaderAddressVo getLeaderAddressVoByUserId(Long userId);

    /**
     * 根据微信openid获取用户信息
     * @param openId
     * @return
     */
    User getByOpenid(String openId);

    /**
     * 获取当前登录用户信息
     * @param userId
     * @return
     */
    UserLoginVo getUserLoginVo(Long userId);
}
