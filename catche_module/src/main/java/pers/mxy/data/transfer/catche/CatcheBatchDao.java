package pers.mxy.data.transfer.catche;

import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/6.
 */
public interface CatcheBatchDao {
    public Map<String, Map<String, String>> batchGet(String nodeUrl, List<String> keyList);
    public void batchSet(String nodeUrl, Map<String, Map<String, String>> paramsMap);
    public ScanResult<String> scan(String nodeUrl, String cursor, int count, String match);
}
