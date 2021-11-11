package com.imooc.broker;

import com.imooc.api.Message;

public interface RabbitBroker {
    void rapidSend(Message message);

    void confirmSend(Message message);

    void reliantSend(Message message);

    void sendMessages();
}
