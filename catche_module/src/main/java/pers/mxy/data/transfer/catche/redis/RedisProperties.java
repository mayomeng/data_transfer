package pers.mxy.data.transfer.catche.redis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * redis配置信息类
 */
@ConfigurationProperties(prefix = "redis")
@PropertySource("classpath:redis.properties")
@Component
@Getter
@Setter
public class RedisProperties {
    private List<String> redisHosts; // 集群ip
    private List<Integer> ports; // 集群port
    private int maxTotal;
    private int maxIdle;
    private int maxWaitMillis;
    private int defaultTimeout;
    private int  defaultMaxRedirections;
}
