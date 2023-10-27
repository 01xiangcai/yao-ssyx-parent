package com.yao.ssyx.product;

import com.rabbitmq.client.Channel;
import com.yao.ssyx.mq.constant.MqConst;
import com.yao.ssyx.mq.service.RabbitService;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;

/**
 * @className: Test
 * @Description: TODO
 * @author: long
 * @date: 2023-10-20 1:09
 */
public class productServiceTest {

    @Autowired
    private RabbitService rabbitService;

    @Autowired
    private AmqpTemplate amqpTemplate;


    @Test
    public void test(){
        rabbitService.sendMessage(MqConst.EXCHANGE_GOODS_DIRECT,MqConst.ROUTING_GOODS_UPPER,1l);
    }

    @Test
    public void send3(){
        amqpTemplate.convertAndSend("myQueue3", "3 now " + new Date());
    }


}
