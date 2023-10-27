package com.yao.ssyx.cart.service;

import com.yao.ssyx.model.order.CartInfo;

import java.util.List;

/**
 * @className: CartInfoService
 * @Description: TODO
 * @author: long
 * @date: 2023-10-22 18:38
 */
public interface CartInfoService {

    // 添加购物车 用户Id，商品Id，商品数量。
    void addToCart(Long skuId, Long userId, Integer skuNum);

    void deleteCart(Long skuId, Long userId);

    /**
     * 批量删除购物车
     * @param userId
     */
    void deleteAllCart(Long userId);

    void batchDeleteCart(List<Long> skuIdList, Long userId);

    /**
     * 通过用户Id 查询购物车列表
     * @param userId
     * @return
     */
    List<CartInfo> getCartList(Long userId);


    /**
     * 更新选中状态
     *
     * @param userId
     * @param isChecked
     * @param skuId
     */
    void checkCart(Long userId, Integer isChecked, Long skuId);

    void checkAllCart(Long userId, Integer isChecked);

    void batchCheckCart(List<Long> skuIdList, Long userId, Integer isChecked);

    List<CartInfo> getCartCheckedList(Long userId);

    void deleteCartChecked(Long userId);
}
