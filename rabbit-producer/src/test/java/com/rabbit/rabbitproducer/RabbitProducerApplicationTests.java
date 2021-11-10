package com.rabbit.rabbitproducer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class RabbitProducerApplicationTests {

    @Autowired
    private RabbitSender rabbitSender;


    @Test
    public void testSender() throws Exception {
        Map<String, Object> properties = new HashMap<>();
        properties.put("attr1", "123456");
        properties.put("attr2", "abcdeddddd");
        rabbitSender.send("hello rabbitmq!", properties);
    }
}
