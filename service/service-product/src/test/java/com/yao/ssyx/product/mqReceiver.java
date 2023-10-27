package com.yao.ssyx.product;

import com.rabbitmq.client.Channel;
import com.yao.ssyx.mq.constant.MqConst;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @className: mqReceiver
 * @Description: TODO
 * @author: long
 * @date: 2023-10-20 1:28
 */
@Component
public class mqReceiver {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MqConst.QUEUE_GOODS_UPPER, durable = "true"),
            exchange = @Exchange(value = MqConst.EXCHANGE_GOODS_DIRECT),
            key = {MqConst.ROUTING_GOODS_UPPER}
    ))
    public void upperSku(Long skuId, Message message, Channel channel) throws IOException {
        System.out.println("skuId = " + skuId);
        /**
         * 第一个参数：表示收到的消息的标号
         * 第二个参数：如果为true表示可以签收多个消息
         */
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    //3. 自动创建队列，Exchange 与 Queue绑定
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue3"),
            exchange = @Exchange("testExChange")
    ))
    public void process3(String message){
        System.out.println("message = " + message);
    }

}
