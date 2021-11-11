package com.imooc.common.convert;

import com.google.common.base.Preconditions;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

public class RabbitMessageConverter implements MessageConverter {
    private GenericMessageConverter delegate;

    public RabbitMessageConverter(GenericMessageConverter genericMessageConverter) {
        Preconditions.checkNotNull(genericMessageConverter);
        this.delegate = genericMessageConverter;
    }


    @Override
    public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
        com.imooc.api.Message message = (com.imooc.api.Message) o;
        messageProperties.setDelay(message.getDelayMills());
        return this.delegate.toMessage(o, messageProperties);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        com.imooc.api.Message msg = (com.imooc.api.Message) this.delegate.fromMessage(message);
        return msg;
    }
}
