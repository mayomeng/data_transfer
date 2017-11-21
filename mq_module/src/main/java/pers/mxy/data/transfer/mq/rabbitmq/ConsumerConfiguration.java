package pers.mxy.data.transfer.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq消费者配置类
 */
@Configuration
@EnableConfigurationProperties(RabbitMqProperties.class)
@EnableRabbit
public class ConsumerConfiguration {

    @Autowired
    private RabbitMqProperties rabbitMqProperties;

    /**
     * 配置连接工厂
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(rabbitMqProperties.getHost());
        connectionFactory.setPort(rabbitMqProperties.getPort());
        connectionFactory.setUsername(rabbitMqProperties.getUsername());
        connectionFactory.setPassword(rabbitMqProperties.getPassword());
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    /**
     * 配置
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(rabbitMqProperties.getExchangeName());
    }

    /**
     * 配置队列
     */
    @Bean
    public Queue queue() {
        return new Queue(rabbitMqProperties.getQueueName(), true); //队列持久
    }

    /**
     * 配置绑定
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(defaultExchange()).with(rabbitMqProperties.getRoutingKey());
    }


/*    @Autowired
    private Consumer consumer;
    @Bean
    public SimpleMessageListenerContainer messageContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
        container.setQueues(queue());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                byte[] body = message.getBody();
                //System.out.println("receive msg : " + new String(body));
                consumer.handleMessage(new String(body));
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
            }
        });
        return container;
    }*/

    /**
     * 配置监听器工厂类
     */
    @Bean
    public SimpleRabbitListenerContainerFactory listenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setAutoStartup(true);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setConcurrentConsumers(rabbitMqProperties.getConcurrentConsumers());
        factory.setMaxConcurrentConsumers(rabbitMqProperties.getMaxConcurrentConsumers());
        factory.setPrefetchCount(rabbitMqProperties.getPrefetchCount());
        return factory;
    }

}
