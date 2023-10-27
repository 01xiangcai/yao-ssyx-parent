package com.yao.ssyx.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yao.ssyx.model.order.OrderItem;
import com.yao.ssyx.order.mapper.OrderItemMapper;
import com.yao.ssyx.order.service.OrderItemService;
import org.springframework.stereotype.Service;

/**
 * @className: OrderItemServiceImpl
 * @Description: TODO
 * @author: long
 * @date: 2023-10-23 2:41
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}
