package pers.mxy.data.transfer.mq.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 发送者类
 */
@Component
@EnableConfigurationProperties(RabbitMqProperties.class)
public class Producer implements RabbitTemplate.ConfirmCallback  {

    @Autowired
    private RabbitMqProperties rabbitMqProperties;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     */
    public void sendMessage(String message) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(rabbitMqProperties.getExchangeName(),
                rabbitMqProperties.getRoutingKey(), message, correlationId);
    }

    /**
     * 成功回调函数
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        // TODO
        System.out.println(" 回调id:" + correlationData);
        if (ack) {
            System.out.println("消息成功消费");
        } else {
            System.out.println("消息消费失败:" + cause);
        }
    }
}
