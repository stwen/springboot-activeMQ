package com.stwen.consumera.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @author ganxianhao
 * @des activeMQ配置类
 * @create 2019/3/25
 */
@Configuration
public class ActiveMQConfig {

    @Value("${queueName}")
    private String queueName;

    @Value("${topicName}")
    private String topicName;

    @Value("${spring.activemq.user}")
    private String usrName;

    @Value("${spring.activemq.password}")
    private  String password;

    @Value("${spring.activemq.broker-url}")
    private  String brokerUrl;

    @Bean
    public Queue getQueue(){
        return new ActiveMQQueue();
    }
    @Bean
    public Topic getTopic(){
        return new ActiveMQTopic();
    }

    /**
     * MQ连接工厂
     * @return
     */
    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        return new ActiveMQConnectionFactory(usrName,password,brokerUrl);
    }

    /**
     * jms 监听器容器工厂--queue
     * @param connectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory jmsListenerContainerQueue(ActiveMQConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory defaultJms = new DefaultJmsListenerContainerFactory();
        defaultJms.setConnectionFactory(connectionFactory);
        return defaultJms;
    }

    /**
     * jms 监听器容器工厂--topic
     * @param connectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory jmsListenerContainerTopic(ActiveMQConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory defaultJms = new DefaultJmsListenerContainerFactory();
        //设置为发布订阅方式, 默认情况下使用的生产消费者方式
        defaultJms.setPubSubDomain(true);
        defaultJms.setConnectionFactory(connectionFactory);
        return defaultJms;
    }
}
