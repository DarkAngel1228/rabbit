package com.imooc.broker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProducerClient implements MessageProducer {

    @Autowired
    private RabbitBroker rabbitBroker;

    //@Override
    public void send(Message message, SendCallBack sendCallBack) throws MessageRunTimeException {

    }

    public void send(Message message) throws MessageRunTimeException {

    }

    public void send(List<Message> messages) throws MessageRunTimeException {

    }
}
