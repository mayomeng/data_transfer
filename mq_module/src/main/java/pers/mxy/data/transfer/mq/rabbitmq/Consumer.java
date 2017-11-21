package pers.mxy.data.transfer.mq.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * 消费者接口
 */
public interface Consumer {
    //@RabbitListener
    public void handleMessage(String message) throws Exception;
}
