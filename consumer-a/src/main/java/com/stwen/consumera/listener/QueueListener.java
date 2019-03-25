package com.stwen.consumera.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author ganxianhao
 * @des
 * @create 2019/3/25
 */
@Component
public class QueueListener {

    /**
     * SendTo 会将此方法返回的数据, 写入到 queue : out.queue 中去.
     * @param text
     * @return
     */
    @JmsListener(destination = "queue",containerFactory = "jmsListenerContainerQueue")
    @SendTo("out.queue")
    public String getText(String text){
        System.out.println("QueueListener consumer-a 收到消息："+text);
        return "QueueListener consumer-a 已经收到："+text;
    }
}
