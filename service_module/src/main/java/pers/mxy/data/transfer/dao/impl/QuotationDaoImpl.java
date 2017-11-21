package pers.mxy.data.transfer.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Repository;
import pers.mxy.data.transfer.catche.impl.CacheAdapter;
import pers.mxy.data.transfer.common.SystemProperties;
import pers.mxy.data.transfer.dao.QuotationDao;
import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.Map;

/**
 * 操作缓存的持久层类
 */
@Repository
@EnableConfigurationProperties(SystemProperties.class)
public class QuotationDaoImpl extends CacheAdapter implements QuotationDao {

    @Autowired
    private SystemProperties systemProperties;

    /**
     * 批量取得行情数据的redis key
     */
    @Override
    public ScanResult<String> getQuotationKeysInfo(String cursor) {
        return scan(systemProperties.getQuotationUrl(), cursor, systemProperties.getBatchCount(),
                systemProperties.getQuotationHashKey());
    }

    /**
     * 根据key 批量取得行情数据
     */
    @Override
    public Map<String, Map<String, String>> getQuotationInfos(List<String> keyList) {
        return batchGet(systemProperties.getQuotationUrl(), keyList);
    }

    /**
     * 批量设置行情数据
     */
    @Override
    public void setQuotationInfos(Map<String, Map<String, String>> paramsMap) {
        batchSet(systemProperties.getQuotationBackUrl(), paramsMap);
    }

    @Override
    public void setQuotationInfo(String code, Map<String, String> map) {
        hmset(code, map);
    }

    /**
     * key个性化设置（没用到）
     */
    @Override
    public String getCustomKey(String key) {
        return "*{quotation}:*" + key;
    }
    /**
     * key个性化设置（没用到）
     */
    @Override
    public String getCustomFieldKey(String field) {
        return field;
    }
    /**
     * key个性化设置（没用到）
     */
    @Override
    public String[] getCustomFieldKeys(String... fields) {
        return fields;
    }
}
