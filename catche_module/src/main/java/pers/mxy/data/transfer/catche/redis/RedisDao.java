package pers.mxy.data.transfer.catche.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.mxy.data.transfer.catche.CatcheDao;
import redis.clients.jedis.JedisCluster;

import java.util.List;
import java.util.Map;

/**
 * redis操作类
 */
@Component("RedisDao")
public class RedisDao implements CatcheDao {

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public String get(final String key) {
        return jedisCluster.get(key);
    }

    @Override
    public String set(final String key, final String value) {
        return jedisCluster.set(key, value);
    }

    @Override
    public Boolean exists(final String key) {
        return jedisCluster.exists(key);
    }

    @Override
    public Long persist(final String key) {
        return jedisCluster.persist(key);
    }

    @Override
    public String type(final String key) {
        return jedisCluster.type(key);
    }

    @Override
    public Long expire(final String key, final int seconds) {
        return jedisCluster.expire(key, seconds);
    }

    @Override
    public Long hset(final String key, final String field, final String value) {
        return jedisCluster.hset(key, field, value);
    }

    @Override
    public String hget(final String key, final String field) {
        return jedisCluster.hget(key, field);
    }

    @Override
    public Long hsetnx(final String key, final String field, final String value) {
        return jedisCluster.hsetnx(key, field, value);
    }

    @Override
    public String hmset(final String key, final Map<String, String> hash) {
        return jedisCluster.hmset(key, hash);
    }

    @Override
    public List<String> hmget(final String key, final String[] fields) {
        return jedisCluster.hmget(key, fields);
    }
}
