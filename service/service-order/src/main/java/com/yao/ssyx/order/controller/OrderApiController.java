package com.yao.ssyx.order.controller;

import com.yao.ssyx.common.result.Result;
import com.yao.ssyx.common.security.AuthContextHolder;
import com.yao.ssyx.order.service.OrderInfoService;
import com.yao.ssyx.vo.order.OrderSubmitVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @className: OrderApiController
 * @Description: TODO
 * @author: long
 * @date: 2023-10-23 1:24
 */

@Api(value = "Order管理", tags = "Order管理")
@RestController
@RequestMapping(value="/api/order")
public class OrderApiController {

    @Resource
    private OrderInfoService orderService;

    @ApiOperation("确认订单")
    @GetMapping("auth/confirmOrder")
    public Result confirm() {
        return Result.ok(orderService.confirmOrder());
    }

    @ApiOperation("生成订单")
    @PostMapping("auth/submitOrder")
    public Result submitOrder(@RequestBody OrderSubmitVo orderParamVo, HttpServletRequest request) {
        // 获取到用户Id
        Long userId = AuthContextHolder.getUserId();
        return Result.ok(orderService.submitOrder(orderParamVo));
    }

    @ApiOperation("获取订单详情")
    @GetMapping("auth/getOrderInfoById/{orderId}")
    public Result getOrderInfoById(@PathVariable("orderId") Long orderId){
        return Result.ok(orderService.getOrderInfoById(orderId));
    }


}