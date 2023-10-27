package com.yao.ssyx.cart.controller;

import com.yao.ssyx.activity.client.ActivityFeignClient;
import com.yao.ssyx.cart.service.CartInfoService;
import com.yao.ssyx.common.result.Result;
import com.yao.ssyx.common.security.AuthContextHolder;
import com.yao.ssyx.model.order.CartInfo;
import com.yao.ssyx.vo.order.OrderConfirmVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @className: CartApiController
 * @Description: TODO
 * @author: long
 * @date: 2023-10-22 18:36
 */
@RestController
@RequestMapping("/api/cart")
public class CartApiController {

    @Autowired
    private CartInfoService cartInfoService;

    @Resource
    private ActivityFeignClient activityFeignClient;


    /**
     * 添加购物车
     *
     * @param skuId
     * @param skuNum
     * @return
     */
    @GetMapping("addToCart/{skuId}/{skuNum}")
    public Result addToCart(@PathVariable("skuId") Long skuId,
                            @PathVariable("skuNum") Integer skuNum) {
        // 如何获取userId
        Long userId = AuthContextHolder.getUserId();
        cartInfoService.addToCart(skuId, userId, skuNum);
        return Result.ok();
    }

    /**
     * 删除
     *
     * @param skuId
     * @param request
     * @return
     */
    @DeleteMapping("deleteCart/{skuId}")
    public Result deleteCart(@PathVariable("skuId") Long skuId,
                             HttpServletRequest request) {
        // 如何获取userId
        Long userId = AuthContextHolder.getUserId();
        cartInfoService.deleteCart(skuId, userId);
        return Result.ok();
    }

    @ApiOperation(value="清空购物车")
    @DeleteMapping("deleteAllCart")
    public Result deleteAllCart(HttpServletRequest request){
        // 如何获取userId
        Long userId = AuthContextHolder.getUserId();
        cartInfoService.deleteAllCart(userId);
        return Result.ok();
    }

    @ApiOperation(value="批量删除购物车")
    @PostMapping("batchDeleteCart")
    public Result batchDeleteCart(@RequestBody List<Long> skuIdList, HttpServletRequest request){
        // 如何获取userId
        Long userId = AuthContextHolder.getUserId();
        cartInfoService.batchDeleteCart(skuIdList, userId);
        return Result.ok();
    }

    /**
     * 查询购物车列表
     *
     * @param request
     * @return
     */
    @GetMapping("cartList")
    public Result cartList(HttpServletRequest request) {
        // 获取用户Id
        Long userId = AuthContextHolder.getUserId();
        List<CartInfo> cartInfoList = cartInfoService.getCartList(userId);
        return Result.ok(cartInfoList);
    }

    /**
     * 查询带优惠卷的购物车
     *
     * @param request
     * @return
     */
    @GetMapping("activityCartList")
    public Result activityCartList(HttpServletRequest request) {
        // 获取用户Id
        Long userId = AuthContextHolder.getUserId();
        List<CartInfo> cartInfoList = cartInfoService.getCartList(userId);

        OrderConfirmVo orderTradeVo = activityFeignClient.findCartActivityAndCoupon(cartInfoList, userId);
        return Result.ok(orderTradeVo);
    }


    /**
     * 更新选中状态
     *
     * @param skuId
     * @param isChecked
     * @param request
     * @return
     */
    @GetMapping("checkCart/{skuId}/{isChecked}")
    public Result checkCart(@PathVariable(value = "skuId") Long skuId,
                            @PathVariable(value = "isChecked") Integer isChecked, HttpServletRequest request) {
        // 获取用户Id
        Long userId = AuthContextHolder.getUserId();
        // 调用更新方法
        cartInfoService.checkCart(userId, isChecked, skuId);
        return Result.ok();
    }

    @GetMapping("checkAllCart/{isChecked}")
    public Result checkAllCart(@PathVariable(value = "isChecked") Integer isChecked, HttpServletRequest request) {
        // 获取用户Id
        Long userId = AuthContextHolder.getUserId();
        // 调用更新方法
        cartInfoService.checkAllCart(userId, isChecked);
        return Result.ok();
    }

    @ApiOperation(value="批量选择购物车")
    @PostMapping("batchCheckCart/{isChecked}")
    public Result batchCheckCart(@RequestBody List<Long> skuIdList, @PathVariable(value = "isChecked") Integer isChecked, HttpServletRequest request){
        // 如何获取userId
        Long userId = AuthContextHolder.getUserId();
        cartInfoService.batchCheckCart(skuIdList, userId, isChecked);
        return Result.ok();
    }


    /**
     * 根据用户Id 查询购物车列表
     *
     * @param userId
     * @return
     */
    @GetMapping("inner/getCartCheckedList/{userId}")
    public List<CartInfo> getCartCheckedList(@PathVariable("userId") Long userId) {
        return cartInfoService.getCartCheckedList(userId);
    }

}
