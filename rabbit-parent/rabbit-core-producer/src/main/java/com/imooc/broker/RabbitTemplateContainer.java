package com.imooc.broker;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.imooc.api.Message;
import com.imooc.api.MessageRunTimeException;
import com.imooc.api.MessageType;
import com.imooc.common.convert.GenericMessageConverter;
import com.imooc.common.convert.RabbitMessageConverter;
import com.imooc.common.serializer.Serializer;
import com.imooc.common.serializer.SerializerFactory;
import com.imooc.common.serializer.impl.JacksonSerializerFactory;
import com.imooc.service.MessageStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * 池化封装
 * 每一个
 */
@Slf4j
@Component
public class RabbitTemplateContainer implements RabbitTemplate.ConfirmCallback {
    private Map<String, RabbitTemplate> rabbitTemplateMap = Maps.newConcurrentMap();

    private Splitter splitter = Splitter.on("#");

    private SerializerFactory serializerFactory = JacksonSerializerFactory.INSTANCE;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private MessageStoreService messageStoreService;

    public RabbitTemplate getTemplate(Message message) throws MessageRunTimeException {
        Preconditions.checkNotNull(message);
        String topic = message.getTopic();
        RabbitTemplate rabbitTemplate = rabbitTemplateMap.get(topic);
        if (rabbitTemplate != null) {
            return rabbitTemplate;
        }

        log.info("#RabbitTemplateContainer.getTemplate# topic: {} is not exists, create one", topic);

        ConnectionFactory connectionFactory1;
        RabbitTemplate newTemplate = new RabbitTemplate(connectionFactory);
        newTemplate.setExchange(topic);
        newTemplate.setRoutingKey(message.getRoutingKey());
        newTemplate.setRetryTemplate(new RetryTemplate());

        // 添加序列化、反序列化和converter对象
        Serializer serializer = serializerFactory.create();
        GenericMessageConverter gmc = new GenericMessageConverter(serializer);
        RabbitMessageConverter rmc = new RabbitMessageConverter(gmc);
        newTemplate.setMessageConverter(rmc);

        String messageType = message.getMessageType();
        if (!MessageType.RAPID.equals(messageType)) {
            newTemplate.setConfirmCallback(this);
        }

        rabbitTemplateMap.putIfAbsent(topic, newTemplate);

        return rabbitTemplateMap.get(topic);
    }


    /**
     * 无论是confirm消息还是reliant消息，发送消息以后broker都会去回调confirm
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        List<String> strings = splitter.splitToList(correlationData.getId());
        String messageId = strings.get(0);
        Long sendTime = Long.parseLong(strings.get(1));
        String messageType = strings.get(2);

        if (ack) {
            // 当Broker返回ACK成功时，就是更新一下日志表里对应的消息发送状态为SEND_OK
            // 如果当前消息类型为reliant，我们就去数据库查找并更新
            if (MessageType.RELIANT.endsWith(messageType)) {
                this.messageStoreService.success(messageId);
            }
            log.info("send message is OK, confirm messageId: {}, sendTime: {}", messageId, sendTime);
        } else {
            log.error("send message is Fail, confirm messageId: {}, sendTime: {}", messageId, sendTime);
        }



    }
}
