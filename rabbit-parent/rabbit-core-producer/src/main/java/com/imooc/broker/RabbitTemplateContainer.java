package com.imooc.broker;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
//import org.codehaus.jackson.map.SerializerFactory;
import com.imooc.common.serializer.SerializerFactory;
import com.imooc.common.serializer.impl.JacksonSerializerFactory;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.util.Map;

public class RabbitTemplateContainer implements RabbitTemplate.ConfirmCallback {
    private Map<String, RabbitTemplate> rabbitTemplateMap = Maps.newConcurrentMap();

    private Splitter splitter = Splitter.on("#");

    private SerializerFactory serializerFactory = JacksonSerializerFactory.INSTANCE;

    @Autowired
    private ConnectionFactory connectionFactory;

    //@Autowired
    //private MessageStoreService messageStoreService;

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {

    }
}
