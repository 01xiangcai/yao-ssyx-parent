package com.yao.ssyx.home.service;

import java.util.Map;

/**
 * @className: ItemService
 * @Description: TODO
 * @author: long
 * @date: 2023-10-22 17:01
 */
public interface ItemService {

    //获取sku详细信息
    Map<String, Object> item(Long skuId, Long userId);

}
