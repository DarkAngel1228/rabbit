package com.imooc.broker;

import com.imooc.api.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitBrokerImpl implements RabbitBroker {


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
