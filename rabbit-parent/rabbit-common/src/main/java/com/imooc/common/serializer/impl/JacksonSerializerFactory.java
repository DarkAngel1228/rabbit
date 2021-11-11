package com.imooc.common.serializer.impl;

import com.imooc.api.Message;
import com.imooc.common.serializer.Serializer;
import com.imooc.common.serializer.SerializerFactory;


public class JacksonSerializerFactory implements SerializerFactory {
    public static final SerializerFactory INSTANCE = new JacksonSerializerFactory();
    @Override
    public Serializer create() {
        return JacksonSerializer.createParametricType(Message.class);
    }
}
