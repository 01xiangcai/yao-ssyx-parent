package com.yao.ssyx.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yao.ssyx.model.sys.Region;
import com.yao.ssyx.sys.mapper.RegionMapper;
import com.yao.ssyx.sys.service.RegionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 地区表 服务实现类
 * </p>
 *
 * @author long
 * @since 2023-10-18
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements RegionService {

    //根据关键字获取地区列表
    @Override
    public List<Region> findRegionByKeyword(String keyword) {
        LambdaQueryWrapper<Region> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Region::getName, keyword);
        return baseMapper.selectList(queryWrapper);
    }

}
