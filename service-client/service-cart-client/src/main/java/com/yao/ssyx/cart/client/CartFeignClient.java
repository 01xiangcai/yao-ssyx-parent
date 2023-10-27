package com.yao.ssyx.cart.client;

import com.yao.ssyx.model.order.CartInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @className: CartFeignClient
 * @Description: TODO
 * @author: long
 * @date: 2023-10-23 1:34
 */
@FeignClient(value = "service-cart")
public interface CartFeignClient {
    /**
     * 根据用户Id 查询购物车列表
     * @param userId
     */
    @GetMapping("/api/cart/inner/getCartCheckedList/{userId}")
    List<CartInfo> getCartCheckedList(@PathVariable("userId") Long userId);
}