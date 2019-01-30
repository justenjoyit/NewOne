package com.yanziting.model.rocketmq.producer;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@Setter
@Slf4j
public class OrderProducer extends DefaultMQProducer {

    private final int BYTE_SIZE = 10240;
    private String topic;

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void doStart() throws MQClientException {
        this.start();
    }

    public void doShutdown() {
        this.shutdown();
    }

    public void sendMessage(Object data) throws InterruptedException, RemotingException, MQClientException, MQBrokerException, IOException {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream(BYTE_SIZE);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArray);
        objectOutputStream.writeObject(data);
        objectOutputStream.flush();
        byte[] byteData =  byteArray.toByteArray();
        Message message = new Message(topic, byteData);
        this.send(message);
        log.debug(message.toString());
    }
}
