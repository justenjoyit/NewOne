package com.yanziting.biz.mq.task;

import com.yanziting.model.rocketmq.producer.OrderProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.IOException;

@Slf4j
public class OrderMessageTask {
    private OrderProducer orderProducer;
    private Object data;


    public OrderMessageTask(OrderProducer orderProducer,Object data){
        this.orderProducer = orderProducer;
        this.data = data;
    }

    public void send() throws InterruptedException, IOException, RemotingException, MQClientException, MQBrokerException {
        orderProducer.sendMessage(data);
    }
}