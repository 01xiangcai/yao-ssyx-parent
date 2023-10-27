package com.yao.ssyx.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yao.ssyx.model.product.SkuPoster;

import java.util.List;

/**
 * @className: SkuPosterService
 * @Description: TODO
 * @author: long
 * @date: 2023-10-19 11:12
 */
public interface SkuPosterService extends IService<SkuPoster> {
    List<SkuPoster> findBySkuId(Long skuId);
}
