package com.yao.ssyx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yao.ssyx.model.product.SkuImage;
import com.yao.ssyx.product.mapper.SkuImageMapper;
import com.yao.ssyx.product.service.SkuImageService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: SkuImageServiceImpl
 * @Description: TODO
 * @author: long
 * @date: 2023-10-19 11:32
 */
@Service
public class SkuImageServiceImpl extends ServiceImpl<SkuImageMapper,SkuImage> implements SkuImageService {

    @Override
    public List<SkuImage> findBySkuId(Long skuId) {
        List<SkuImage> skuImages = baseMapper.selectList(new LambdaQueryWrapper<SkuImage>().eq(SkuImage::getSkuId, skuId));
        return skuImages;
    }

}
