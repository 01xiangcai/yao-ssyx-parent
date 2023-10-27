package com.yao.ssyx.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yao.ssyx.model.product.SkuImage;

import java.util.List;

/**
 * @className: SkuImageService
 * @Description: TODO
 * @author: long
 * @date: 2023-10-19 11:13
 */
public interface SkuImageService extends IService<SkuImage> {
    List<SkuImage> findBySkuId(Long skuId);
}
