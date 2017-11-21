package pers.mxy.data.transfer.mq.rabbitmq;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * rabbitmq配置信息
 */
@ConfigurationProperties(prefix = "rabbitmq")
@PropertySource("classpath:rabbitmq.properties")
@Component
@Getter
@Setter
public class RabbitMqProperties {
    private String host; // ip
    private int port; // port
    private String username;
    private String password;
    private String exchangeName; // 交换机名
    private String queueName; // 队列名
    private String routingKey; // 路由key

    // 消费者线程相关配置
    private boolean exposeListenerChannel;
    private int maxConcurrentConsumers;
    private int concurrentConsumers;
    private int prefetchCount;
}
