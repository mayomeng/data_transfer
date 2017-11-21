package pers.mxy.data.transfer.catche;

import java.util.List;
import java.util.Map;

public interface CatcheDao {

    public String get(String key);

    public String set(String key, String value);

    public Boolean exists(final String key);

    public Long persist(final String key);

    public String type(final String key);

    public Long expire(final String key, final int seconds);

    public Long hset(String key, String field, String value);

    public String hget(String key, String field);

    public Long hsetnx(String key, String field, String value);

    public String hmset(String key, Map<String, String> hash);

    public List<String> hmget(String key, String... fields);
}
