package com.yao.ssyx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yao.ssyx.model.product.SkuPoster;
import com.yao.ssyx.product.mapper.SkuPosterMapper;
import com.yao.ssyx.product.service.SkuPosterService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: SkuPosterServiceimpl
 * @Description: TODO
 * @author: long
 * @date: 2023-10-19 11:31
 */
@Service
public class SkuPosterServiceimpl extends ServiceImpl<SkuPosterMapper,SkuPoster> implements SkuPosterService {


    @Override
    public List<SkuPoster> findBySkuId(Long skuId) {
        return baseMapper.selectList(new LambdaQueryWrapper<SkuPoster>().eq(SkuPoster::getSkuId,skuId));
    }

}
