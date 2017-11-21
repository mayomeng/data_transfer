package pers.mxy.data.transfer.catche.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pers.mxy.data.transfer.catche.CatcheBatchDao;
import pers.mxy.data.transfer.catche.CatcheDao;
import redis.clients.jedis.ScanResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 缓存适配器类
 */
public abstract class CacheAdapter implements CatcheDao, CatcheBatchDao {

    @Autowired
    @Qualifier("RedisDao")
    private CatcheDao catcheDao;

    @Autowired
    @Qualifier("RedisBatchDao")
    private CatcheBatchDao catcheBatchDao;

    public abstract String getCustomKey(String key);
    public abstract String getCustomFieldKey(String field);
    public abstract String[] getCustomFieldKeys(String... fields);

    @Override
    public String get(String key) {
        return catcheDao.get(getCustomKey(key));
    }

    @Override
    public String set(String key, String value) {
        return catcheDao.set(getCustomKey(key), value);
    }

    @Override
    public Boolean exists(String key) {
        return catcheDao.exists(getCustomKey(key));
    }

    @Override
    public Long persist(String key) {
        return catcheDao.persist(getCustomKey(key));
    }

    @Override
    public String type(String key) {
        return catcheDao.type(getCustomKey(key));
    }

    @Override
    public Long expire(String key, int seconds) {
        return catcheDao.expire(getCustomKey(key), seconds);
    }

    @Override
    public Long hset(String key, String field, String value) {
        return catcheDao.hset(getCustomKey(key), getCustomFieldKey(field), value);
    }

    @Override
    public String hget(String key, String field) {
        return catcheDao.hget(getCustomKey(key), getCustomFieldKey(field));
    }

    @Override
    public Long hsetnx(String key, String field, String value) {
        return catcheDao.hsetnx(getCustomKey(key), getCustomFieldKey(field), value);
    }

    @Override
    public String hmset(String key, Map<String, String> hash) {
        return catcheDao.hmset(getCustomKey(key), hash);
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        return catcheDao.hmget(getCustomKey(key), getCustomFieldKeys(fields));
    }

    @Override
    public Map<String, Map<String, String>> batchGet(String nodeUrl, List<String> keyList) {
        return catcheBatchDao.batchGet(nodeUrl, keyList);
    }

    @Override
    public void batchSet(String nodeUrl, Map<String, Map<String, String>> paramsMap) {
        catcheBatchDao.batchSet(nodeUrl, paramsMap);
    }

    @Override
    public ScanResult<String> scan(String nodeUrl, String cursor, int count, String match) {
        return catcheBatchDao.scan(nodeUrl, cursor, count, match);
    }

    public Map<String, Object> getKeysIteration(String nodeUrl, String cursor, int count, String match) {
        ScanResult<String> scanResult = scan(nodeUrl, cursor, count, match);
        Map<String, Object> keysInfo = new HashMap<>();
        keysInfo.put("offset", scanResult.getStringCursor());
        keysInfo.put("results", scanResult.getResult());
        return keysInfo;
    }
}
