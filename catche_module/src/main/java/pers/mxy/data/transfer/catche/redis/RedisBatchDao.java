package pers.mxy.data.transfer.catche.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.mxy.data.transfer.catche.CatcheBatchDao;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 批量操作缓存类
 */
@Component("RedisBatchDao")
@Slf4j
public class RedisBatchDao implements CatcheBatchDao {

    @Autowired
    private JedisCluster jedisCluster;
    private Map<String, JedisPool> nodeMap;

    @PostConstruct
    public void init() {
        nodeMap = jedisCluster.getClusterNodes();
        log.info("redis nodes's inofs ", nodeMap);
    }

    /**
     *  批量取得缓存数据
     */
    @Override
    public Map<String, Map<String, String>> batchGet(String nodeUrl, List<String> keyList) {
        Map<String, Map<String, String>> result = new HashMap<>();
        Jedis jedis = null;
        try {
            // TODO 假设要取得的key都在同一个redis节点上(通过插入数据时指定hashtag实现)
            if (CollectionUtils.isNotEmpty(keyList)) {
                Map<String, Response<Map<String, String>>> responseMap = new HashMap<>();
                jedis = nodeMap.get(nodeUrl).getResource();
                Pipeline pipeline = jedis.pipelined();
                for (String key : keyList) {
                    responseMap.put(key, pipeline.hgetAll(key));
                }
                pipeline.sync();
                for(String key : responseMap.keySet()) {
                    result.put(key, responseMap.get(key).get());
                }
            }

        } catch (JedisConnectionException e) {
            init();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    /**
     *  批量写入缓存数据
     */
    @Override
    public void batchSet(String nodeUrl, Map<String, Map<String, String>> paramsMap) {
        // TODO 假设要取得的key都在同一个redis节点上(通过插入数据时指定hashtag实现)
        Jedis jedis = null;
        try {
            jedis = nodeMap.get(nodeUrl).getResource();
            Pipeline pipeline = jedis.pipelined();
            for (String key : paramsMap.keySet()) {
                pipeline.hmset(key, paramsMap.get(key));
            }
            pipeline.sync();
        } catch (JedisConnectionException e) {
            init();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     *  扫描指定数据的key
     */
    @Override
    public ScanResult<String> scan(String nodeUrl, String cursor, int count, String match) {
        Jedis jedis = null;
        ScanResult<String> scanResult = null;
        try {
            jedis = nodeMap.get(nodeUrl).getResource();
            ScanParams scanParams = new ScanParams();
            scanParams.count(count);
            scanParams.match(match);
            scanResult = jedis.scan(cursor, scanParams);
        } catch (JedisConnectionException e) {
            init();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return scanResult;
    }
}
