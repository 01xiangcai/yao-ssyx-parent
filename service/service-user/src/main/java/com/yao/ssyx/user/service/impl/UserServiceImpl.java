package com.yao.ssyx.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yao.ssyx.client.sys.SysFeignClient;
import com.yao.ssyx.enums.UserType;
import com.yao.ssyx.user.mapper.LeaderMapper;
import com.yao.ssyx.user.mapper.UserDeliveryMapper;
import com.yao.ssyx.user.mapper.UserMapper;
import com.yao.ssyx.model.user.Leader;
import com.yao.ssyx.model.user.User;
import com.yao.ssyx.model.user.UserDelivery;
import com.yao.ssyx.user.service.UserService;
import com.yao.ssyx.vo.user.LeaderAddressVo;
import com.yao.ssyx.vo.user.UserLoginVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @className: UserServiceImpl
 * @Description: TODO
 * @author: long
 * @date: 2023-10-21 15:06
 */
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserDeliveryMapper userDeliveryMapper;

    @Resource
    private LeaderMapper leaderMapper;

    @Resource
    private SysFeignClient sysFeignClient;

    @Override
    public LeaderAddressVo getLeaderAddressVoByUserId(Long userId) {
        LambdaQueryWrapper<UserDelivery> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserDelivery::getUserId, userId);
        queryWrapper.eq(UserDelivery::getIsDefault, 1);
        UserDelivery userDelivery = userDeliveryMapper.selectOne(queryWrapper);
        if(null == userDelivery) return null;

        Leader leader = leaderMapper.selectById(userDelivery.getLeaderId());

        LeaderAddressVo leaderAddressVo = new LeaderAddressVo();
        BeanUtils.copyProperties(leader, leaderAddressVo);
        leaderAddressVo.setUserId(userId);
        leaderAddressVo.setLeaderId(leader.getId());
        leaderAddressVo.setLeaderName(leader.getName());
        leaderAddressVo.setLeaderPhone(leader.getPhone());
        leaderAddressVo.setWareId(userDelivery.getWareId());
        leaderAddressVo.setStorePath(leader.getStorePath());
        return leaderAddressVo;
    }

    @Override
    public User getByOpenid(String openId) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("open_id", openId));
    }

    @Override
    public UserLoginVo getUserLoginVo(Long userId) {
        UserLoginVo userLoginVo = new UserLoginVo();
        User user = this.getById(userId);
        userLoginVo.setNickName(user.getNickName());
        userLoginVo.setUserId(userId);
        userLoginVo.setPhotoUrl(user.getPhotoUrl());
        userLoginVo.setOpenId(user.getOpenId());
        userLoginVo.setIsNew(user.getIsNew());

        //如果是团长获取当前前团长id与对应的仓库id
        if(user.getUserType() == UserType.LEADER) {
            LambdaQueryWrapper<Leader> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Leader::getUserId, userId);
            queryWrapper.eq(Leader::getCheckStatus, 1);
            Leader leader = leaderMapper.selectOne(queryWrapper);
            if(null != leader) {
                userLoginVo.setLeaderId(leader.getId());
                Long wareId = sysFeignClient.getWareIdByRegionId(leader.getRegionId());
                userLoginVo.setWareId(wareId);
            }
        } else {
            //如果是会员获取当前会员对应的仓库id
            LambdaQueryWrapper<UserDelivery> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserDelivery::getUserId, userId);
            queryWrapper.eq(UserDelivery::getIsDefault, 1);
            UserDelivery userDelivery = userDeliveryMapper.selectOne(queryWrapper);
            if(null != userDelivery) {
                userLoginVo.setLeaderId(userDelivery.getLeaderId());
                userLoginVo.setWareId(userDelivery.getWareId());
            } else {
                userLoginVo.setLeaderId(1L);
                userLoginVo.setWareId(1L);
            }
        }
        return userLoginVo;
    }
}