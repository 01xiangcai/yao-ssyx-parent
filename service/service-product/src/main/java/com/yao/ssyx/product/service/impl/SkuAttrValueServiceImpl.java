package com.yao.ssyx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yao.ssyx.model.product.SkuAttrValue;
import com.yao.ssyx.product.mapper.SkuAttrValueMapper;
import com.yao.ssyx.product.service.SkuAttrValueService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className: SkuAttrValueServiceImpl
 * @Description: TODO
 * @author: long
 * @date: 2023-10-19 11:34
 */
@Service
public class SkuAttrValueServiceImpl extends ServiceImpl<SkuAttrValueMapper,SkuAttrValue> implements SkuAttrValueService {


    @Override
    public List<SkuAttrValue> findBySkuId(Long skuId) {
        return baseMapper.selectList(new LambdaQueryWrapper<SkuAttrValue>().eq(SkuAttrValue::getSkuId,skuId));
    }
}
