package com.rabbit.rabbitconsumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.messaging.Message;

@Component
public class RabbitReceive {

    /**
     * 组合使用监听
     * @param message
     * @param channel
     * @throws Exception
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "queue-1", durable = "true"), exchange = @Exchange(name = "exchange-1", durable = "true", type = "topic", ignoreDeclarationExceptions = "true"), key = "springboot.*"))
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception{
        // 1.收到消息以后进行业务端消息处理
        System.err.println("------------------------");
        System.err.println("消费信息：" + message.getPayload());

        // 2.处理成功之后 获取deliveryTag 并进行手工的ack操作，因为我们配置文件里配置的是手工签收
        Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }
}
