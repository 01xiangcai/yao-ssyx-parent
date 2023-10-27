package com.yao.ssyx.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yao.ssyx.model.order.OrderInfo;
import com.yao.ssyx.vo.order.OrderConfirmVo;
import com.yao.ssyx.vo.order.OrderSubmitVo;

/**
 * @className: OrderInfoService
 * @Description: TODO
 * @author: long
 * @date: 2023-10-23 1:24
 */
public interface OrderInfoService extends IService<OrderInfo> {
    /**
     * 确认订单
     */
    OrderConfirmVo confirmOrder();

    //生成订单
    Long submitOrder(OrderSubmitVo orderParamVo);

    //订单详情
    OrderInfo getOrderInfoById(Long orderId);

}