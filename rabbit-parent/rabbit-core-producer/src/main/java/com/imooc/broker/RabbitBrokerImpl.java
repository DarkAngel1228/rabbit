package com.imooc.broker;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.imooc.api.Message;
import com.imooc.api.MessageType;
import com.imooc.common.serializer.SerializerFactory;
import com.imooc.common.serializer.impl.JacksonSerializerFactory;
import com.imooc.constant.BrokerMessageConst;
import com.imooc.constant.BrokerMessageStatus;
import com.imooc.entity.BrokerMessage;
import com.imooc.service.MessageStoreService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
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
//        message.setMessageType(MessageType.RELIANT);
//        BrokerMessage bm = messageStoreService.selectByMessageId(message.getMessageId());
//
//        if (bm == null) {
//            // 1.把数据库的消息发送日志先记录好
//            Date now = new Date();
//            BrokerMessage brokerMessage = new BrokerMessage();
//            brokerMessage.setMessageId(message.getMessageId());
//            brokerMessage.setStatus(BrokerMessageStatus.SENDING.getCode());
//
//            // tryCount 在最开始发送的时候不需要进行设置
//            brokerMessage.setNewRetry(DateUtils.addMinutes(now, BrokerMessageConst.TIMEOUT));
//            brokerMessage.setCreateTime(now);
//            brokerMessage.setUpdateTime(now);
//            brokerMessage.setMessage(message);
//            messageStoreService.insert(brokerMessage);
//        }
//
//        // 2.执行真正的发送消息逻辑
//        sendKernel(message);

    }

    @Override
    public void sendMessages() {

    }


    private void sendKernel(Message message) {
//        AsyncBaseQueue.submit((Runnable) () -> {
//            CorrelationData correlationData =
//                    new CorrelationData(String.format("%s#%s#%s",
//                            message.getMessageId(),
//                            System.currentTimeMillis(),
//                            message.getMessageType()));
//            String topic = message.getTopic();
//            String routingKey = message.getRoutingKey();
//            RabbitTemplate rabbitTemplate = rabbitTemplateContainer.getTemplate(message);
//            rabbitTemplate.convertAndSend(topic, routingKey, message, correlationData);
//            log.info("#RabbitBrokerImpl.sendKernel# send to rabbitmq, messageId: {}", message.getMessageId());
//
//
//        });
    }
}
