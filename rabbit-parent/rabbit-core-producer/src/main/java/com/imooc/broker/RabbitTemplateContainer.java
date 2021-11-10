package com.imooc.broker;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
//import org.codehaus.jackson.map.SerializerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Map;

public class RabbitTemplateContainer implements RabbitTemplate.ConfirmCallback {
    private Map<String, RabbitTemplate> rabbitTemplateMap = Maps.newConcurrentMap();

    private Splitter splitter = Splitter.on("#");

    //private SerializerFactory serializerFactory = JacksonSerializerFactory.INSTANCE;


    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {

    }
}
