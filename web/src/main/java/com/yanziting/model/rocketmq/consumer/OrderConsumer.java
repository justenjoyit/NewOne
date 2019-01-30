package com.yanziting.model.rocketmq.consumer;

import com.yanziting.biz.mq.listener.OrderBackMessageListener;
import lombok.Setter;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;

@Setter
public class OrderConsumer extends DefaultMQPushConsumer {

    private String topic;
    private OrderBackMessageListener orderBackMessageListener;

    public void doStart() throws MQClientException {
        this.registerMessageListener(orderBackMessageListener);
        this.start();
    }

    public void doShutdown() {
        this.shutdown();
    }

    public void setTopic(String topic) throws MQClientException {
        this.topic = topic;
        this.subscribe(this.topic, "*");
    }

    public void registerMsgListener(OrderBackMessageListener orderBackMessageListener) {
        this.orderBackMessageListener = orderBackMessageListener;
    }
}
