package com.yao.ssyx.activity.api;

import com.yao.ssyx.activity.service.ActivityInfoService;
import com.yao.ssyx.activity.service.CouponInfoService;
import com.yao.ssyx.model.activity.CouponInfo;
import com.yao.ssyx.model.order.CartInfo;
import com.yao.ssyx.vo.order.CartInfoVo;
import com.yao.ssyx.vo.order.OrderConfirmVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @className: ActivityApiController
 * @Description: TODO
 * @author: long
 * @date: 2023-10-21 19:11
 */
@RestController
@RequestMapping("/api/activity")
public class ActivityApiController {

    @Autowired
    private ActivityInfoService activityInfoService;

    @Autowired
    private CouponInfoService couponInfoService;

    @PostMapping("/inner/findActivityBySkuIds")
    public Map<Long, List<String>> findActivityBySkuIds(@RequestBody List<Long> skuIds){
        return activityInfoService.findActivityBySkuIds(skuIds);
    }

    @ApiOperation(value = "根据skuId获取促销与优惠券信息")
    @GetMapping("inner/findActivityAndCoupon/{skuId}/{userId}")
    public Map<String, Object> findActivityAndCoupon(@PathVariable Long skuId, @PathVariable("userId") Long userId) {
        return activityInfoService.findActivityAndCoupon(skuId, userId);
    }

    @ApiOperation(value = "获取购物车满足条件的促销与优惠券信息")
    @PostMapping("inner/findCartActivityAndCoupon/{userId}")
    public OrderConfirmVo findCartActivityAndCoupon(@RequestBody List<CartInfo> cartInfoList, @PathVariable("userId") Long userId, HttpServletRequest request) {
        return activityInfoService.findCartActivityAndCoupon(cartInfoList, userId);
    }

    @ApiOperation(value = "更新优惠券使用状态")
    @GetMapping(value = "inner/updateCouponInfoUseStatus/{couponId}/{userId}/{orderId}")
    public Boolean updateCouponInfoUseStatus(@PathVariable("couponId") Long couponId, @PathVariable("userId") Long userId, @PathVariable("orderId") Long orderId) {
        couponInfoService.updateCouponInfoUseStatus(couponId, userId, orderId);
        return true;
    }

    //获取购物车对应规则数据
    @PostMapping("inner/findCartActivityList")
    public List<CartInfoVo> findCartActivityList(@RequestBody List<CartInfo> cartInfoList) {
        return activityInfoService.findCartActivityList(cartInfoList);
    }

    //获取购物车对应优惠卷
    @PostMapping("inner/findRangeSkuIdList/{couponId}")
    public CouponInfo findRangeSkuIdList(@RequestBody List<CartInfo> cartInfoList,
                                         @PathVariable("couponId") Long couponId) {
        return couponInfoService.findRangeSkuIdList(cartInfoList,couponId);
    }

}
