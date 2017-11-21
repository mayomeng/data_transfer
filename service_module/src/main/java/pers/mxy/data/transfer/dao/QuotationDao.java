package pers.mxy.data.transfer.dao;

import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/6.
 */
public interface QuotationDao {
    public ScanResult<String> getQuotationKeysInfo(String cursor);
    public Map<String, Map<String, String>> getQuotationInfos(List<String> keyList);
    public void setQuotationInfos(Map<String, Map<String, String>> paramsMap);
    public void setQuotationInfo(String code, Map<String, String> map);
}
