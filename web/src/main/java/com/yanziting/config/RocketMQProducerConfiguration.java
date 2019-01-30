package com.yanziting.config;

import com.yanziting.model.rocketmq.producer.OrderProducer;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "yanzt.rocketmq")
@Data
public class RocketMQProducerConfiguration {
    private String namesrvAddr;
    private List<String> topics;
    private List<String> producerGroups;
    private List<String> consumerGroups;

    @Bean(initMethod = "doStart",destroyMethod = "doShutdown")
    public OrderProducer orderProducer(){
        OrderProducer orderProducer = new OrderProducer();
        orderProducer.setNamesrvAddr(namesrvAddr);
        orderProducer.setTopic(topics.get(0));
        orderProducer.setProducerGroup(producerGroups.get(0));
        return orderProducer;
    }

//    @Bean(initMethod = "doStart", destroyMethod = "doShutdown")
//    public ShipProducer shipProducer() {
//        ShipProducer shipProducer = new ShipProducer();
//        shipProducer.setNamesrvAddr(namesrvAddr);
//        shipProducer.setTopic(topics.get(1));
//        shipProducer.setProducerGroup(producerGroups.get(1));
//        return shipProducer;
//    }
//
//    @Bean(initMethod = "doStart", destroyMethod = "doShutdown")
//    public ReceiptProducer receiptProducer() {
//        ReceiptProducer receiptProducer = new ReceiptProducer();
//        receiptProducer.setNamesrvAddr(namesrvAddr);
//        receiptProducer.setTopic(topics.get(2));
//        receiptProducer.setProducerGroup(producerGroups.get(2));
//        return receiptProducer;
//    }
}
