package com.yanziting.config;

import com.yanziting.biz.mq.listener.OrderBackMessageListener;
import com.yanziting.model.rocketmq.consumer.OrderConsumer;
import lombok.Data;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "yanzt.rocketmq")
@Data
public class RocketMQConsumerConfiguration {
    private String namesrvAddr;
    private List<String> topics;
    private List<String> producerGroups;
    private List<String> consumerGroups;

    @Resource
    private OrderBackMessageListener orderBackMessageListener;
//    @Resource
//    private ShipBackMessageListener shipBackMessageListener;
//    @Resource
//    private ReceiptBackMessageListener receiptBackMessageListener;

    @Bean(initMethod = "doStart", destroyMethod = "doShutdown")
    public OrderConsumer orderConsumer() throws MQClientException {
        final OrderConsumer orderConsumer = new OrderConsumer();
        orderConsumer.setNamesrvAddr(namesrvAddr);
        orderConsumer.setTopic(topics.get(0));
        orderConsumer.setConsumerGroup(consumerGroups.get(0));
        orderConsumer.setOrderBackMessageListener(orderBackMessageListener);
        return orderConsumer;
    }

//    @Bean(initMethod = "doStart", destroyMethod = "doShutdown")
//    public ShipConsumer shipConsumer() throws MQClientException {
//        ShipConsumer shipConsumer = new ShipConsumer();
//        shipConsumer.setNamesrvAddr(namesrvAddr);
//        shipConsumer.setTopic(topics.get(1));
//        shipConsumer.setConsumerGroup(consumerGroups.get(1));
//        shipConsumer.registerMessageListener(shipBackMessageListener);
//        return shipConsumer;
//    }
//
//    @Bean(initMethod = "doStart", destroyMethod = "doShutdown")
//    public ReceiptConsumer receiptConsumer() throws MQClientException {
//        ReceiptConsumer receiptConsumer = new ReceiptConsumer();
//        receiptConsumer.setNamesrvAddr(namesrvAddr);
//        receiptConsumer.setTopic(topics.get(2));
//        receiptConsumer.setConsumerGroup(consumerGroups.get(2));
//        receiptConsumer.registerMessageListener(receiptBackMessageListener);
//        return receiptConsumer;
//    }
}
