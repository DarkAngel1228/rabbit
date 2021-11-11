package com.imooc.broker;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.imooc.api.Message;
import com.imooc.common.serializer.SerializerFactory;
import com.imooc.common.serializer.impl.JacksonSerializerFactory;
import com.imooc.service.MessageStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * 池化封装
 * 每一个topi对应一个RabbitTemplate
 * 1. 提高发送的效率
 * 2. 可以根据不同的需求制定不同的RabbitTemplate，比如每一个topic都有自己的routingKey规则
 */
@Slf4j
@Component
public class RabbitBrokerImpl implements RabbitBroker {

    @Autowired
    private RabbitTemplateContainer rabbitTemplateContainer;

    @Autowired
    private MessageStoreService messageStoreService;





    @Override
    public void rapidSend(Message message) {

    }

    @Override
    public void confirmSend(Message message) {

    }

    @Override
    public void reliantSend(Message message) {

    }

    @Override
    public void sendMessages() {

    }
}
