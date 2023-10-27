package com.yao.ssyx.activity.client;

import com.yao.ssyx.model.activity.CouponInfo;
import com.yao.ssyx.model.order.CartInfo;
import com.yao.ssyx.vo.order.CartInfoVo;
import com.yao.ssyx.vo.order.OrderConfirmVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @className: ActivityFeignClient
 * @Description: TODO
 * @author: long
 * @date: 2023-10-21 19:10
 */
@FeignClient(value = "service-activity")
public interface ActivityFeignClient {

    @PostMapping("/api/activity/inner/findActivityBySkuIds")
    public Map<Long, List<String>> findActivityBySkuIds(@RequestBody List<Long> skuIds);

    @ApiOperation(value = "根据skuId获取促销与优惠券信息")
    @GetMapping("/api/activity/inner/findActivityAndCoupon/{skuId}/{userId}")
    public Map<String, Object> findActivityAndCoupon(@PathVariable Long skuId, @PathVariable("userId") Long userId);

    @ApiOperation(value = "获取购物车满足条件的促销与优惠券信息")
    @PostMapping("/api/activity/inner/findCartActivityAndCoupon/{userId}")
    public OrderConfirmVo findCartActivityAndCoupon(@RequestBody List<CartInfo> cartInfoList, @PathVariable("userId") Long userId);

    @ApiOperation(value = "更新优惠券使用状态")
    @GetMapping(value = "/api/activity/inner/updateCouponInfoUseStatus/{couponId}/{userId}/{orderId}")
    public Boolean updateCouponInfoUseStatus(@PathVariable("couponId") Long couponId, @PathVariable("userId") Long userId, @PathVariable("orderId") Long orderId);

    @PostMapping("/api/activity/inner/findRangeSkuIdList/{couponId}")
    public CouponInfo findRangeSkuIdList(@RequestBody List<CartInfo> cartInfoList,
                                         @PathVariable("couponId") Long couponId);

    //获取购物车对应规则数据
    @PostMapping("/api/activity/inner/findCartActivityList")
    public List<CartInfoVo> findCartActivityList(@RequestBody List<CartInfo> cartInfoList);
}
