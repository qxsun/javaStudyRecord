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
package com.example.javaStudy.mqstudy.producer;


import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMsgController {
    @Autowired
    private RocketMQTemplate template;

    @RequestMapping("/sendMsg")
    public String sendMsg(String msg) {
        //第一个参数目的地(主题)
        //第二个参数:发送的消息
        //同步发送的方式
        template.syncSend("01-boot-hello", msg);
        return "发送成功";
    }

    @RequestMapping("/asyncMsg")
    public String asyncMsg(String msg) {
        //异步发送的方式
        template.asyncSend("01-boot-hello", msg, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("发送成功");
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println("发送失败");
            }
        });
        return "发送成功";
    }

    @RequestMapping("/sendOneWay")
    public String sendOneWay(String msg){
    //一次性发送的方式
        template.sendOneWay("01-boot-hello", msg);
        return "发送成功";
    }

}
