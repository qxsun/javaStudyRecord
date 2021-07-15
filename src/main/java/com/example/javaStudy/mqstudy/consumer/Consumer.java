/**
 * Project:TODO ADD PROJECT NAME
 * Modify Information:
 * ================================================================
 * Author         Date           Description
 * ------------   ----------      --------------------------------
 * ums_qxsun        2021/7/14         TODO:
 * ================================================================
 * Copyright (c) 银联商务股份有限公司 www.chinaums.com
 */
package com.example.javaStudy.mqstudy.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(
        consumerGroup = "boot-consumer-demo",  //组
        topic="01-boot-hello"  //主题
)
public class Consumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String msg) {
        System.out.println("消息的内容" + msg);
    }
}
