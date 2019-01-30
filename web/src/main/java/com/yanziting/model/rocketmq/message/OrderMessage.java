package com.yanziting.model.rocketmq.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderMessage implements Serializable {
    private String orderId;
    private String userId;
    private List<String> productIds;
    private Date created;
}