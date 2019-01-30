package com.yanziting.biz.mq;

import com.yanziting.WebApplication;
import com.yanziting.model.rocketmq.message.OrderMessage;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class OrderProducerTest {

    @Resource
    private MessageProducer messageProducer;

    @Test
    public void send() throws Exception {
        for (int i = 0; i < 10; ++i) {
            OrderMessage orderMessage = OrderMessage.builder()
                    .orderId(String.valueOf(i))
                    .userId(String.valueOf(i))
                    .productIds(Lists.newArrayList(String.valueOf(i)))
                    .created(new Date())
                    .build();
            messageProducer.sendOrderMessage(orderMessage);
//            receiptProducer.sendMessage("order producer send message: " + i);
        }
    }
}
