package com.stwen.consumerb.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author ganxianhao
 * @des
 * @create 2019/3/25
 */
@Component
public class TopicListener {

    @JmsListener(destination = "topic",containerFactory = "jmsListenerContainerTopic")
    public void receive(String text){
        System.out.println("TopicListener consumer-b 收到"+text);
    }
}
