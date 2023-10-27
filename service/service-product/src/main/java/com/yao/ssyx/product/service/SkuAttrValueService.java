package com.yao.ssyx.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yao.ssyx.model.product.SkuAttrValue;

import java.util.List;

/**
 * @className: SkuAttrValueService
 * @Description: TODO
 * @author: long
 * @date: 2023-10-19 11:13
 */
public interface SkuAttrValueService extends IService<SkuAttrValue> {
    List<SkuAttrValue> findBySkuId(Long skuId);
}
