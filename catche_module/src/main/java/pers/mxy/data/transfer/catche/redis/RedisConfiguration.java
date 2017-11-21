package pers.mxy.data.transfer.catche.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * redis配置类
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)// 开启属性注入,通过@autowired注入
@ConditionalOnClass(JedisCluster.class) // 判断这个类是否在classpath中存在
public class RedisConfiguration {

    public static int DEFAULT_MAX_REDIRECTIONS;

    @Autowired
    RedisProperties redisProperties;

    /**
     * 配置redis集群信息
     */
    @Bean
    public JedisCluster getJedisCluster() {

        DEFAULT_MAX_REDIRECTIONS = redisProperties.getDefaultMaxRedirections();

        Set<HostAndPort> addressSet = new HashSet<HostAndPort>();
        for (int i = 0; i < redisProperties.getRedisHosts().size() ; i++) {
            addressSet.add(new HostAndPort(redisProperties.getRedisHosts().get(i), redisProperties.getPorts().get(i)));
        }

        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(redisProperties.getMaxTotal());
        poolConfig.setMaxIdle(redisProperties.getMaxIdle());
        poolConfig.setMaxWaitMillis(redisProperties.getMaxWaitMillis());
        JedisCluster jedisCluster = new JedisCluster(
                addressSet, redisProperties.getDefaultTimeout(), poolConfig);
        return jedisCluster;
    }
}
