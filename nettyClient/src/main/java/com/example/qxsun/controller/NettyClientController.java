package com.example.qxsun.controller;

import com.example.qxsun.server.NettyClientBootStrap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class NettyClientController {

    /**
     * @description: 模拟向服务器发送消息
     * @param
     * @Author: wuyong
     * @Date: 2019/08/30 14:10:09
     * @return: java.lang.String
     */
    @RequestMapping("/req")
    public String req() {
        String msg = "{\"msgType\":\"req\",\"clientId\":\"请求数据\"}";
        NettyClientBootStrap.getSocketChannel().writeAndFlush(msg);
        return "success";
    }

}
