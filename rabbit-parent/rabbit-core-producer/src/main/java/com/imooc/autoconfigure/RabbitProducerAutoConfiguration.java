package com.imooc.autoconfigure;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.imooc.producer.*"})
public class RabbitProducerAutoConfiguration {

}
