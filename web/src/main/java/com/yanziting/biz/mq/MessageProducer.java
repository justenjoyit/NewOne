package com.yanziting.biz.mq;

import com.yanziting.biz.mq.task.OrderMessageTask;
import com.yanziting.model.rocketmq.producer.OrderProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j
@Component
public class MessageProducer {

    @Resource
    private OrderProducer orderProducer;
//    @Resource
//    private ShipProducer shipProducer;
//    @Resource
//    private ReceiptProducer receiptProducer;

    public void sendOrderMessage(Object data) throws InterruptedException, IOException, RemotingException, MQClientException, MQBrokerException {
        OrderMessageTask task = new OrderMessageTask(orderProducer,data);
        task.send();
    }

//    public void sendShipMessage(Object data) throws InterruptedException, IOException, RemotingException, MQClientException, MQBrokerException {
//        ShipMessageTask task = new ShipMessageTask(shipProducer,data);
//        task.send();
//    }
//
//    public void sendReceiptMessage(Object data) throws InterruptedException, RemotingException, MQClientException, MQBrokerException, IOException {
//        ReceiptMessageTask task = new ReceiptMessageTask(receiptProducer,data);
//        task.send();
//    }
}