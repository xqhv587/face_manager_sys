package com.xqh.commoncore.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Configuration
public class RedisStringUtil {

    private final StringRedisTemplate redisTemplate;

    public RedisStringUtil(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // kv数据结构的测试相关

    /**
     * 设置并获取之间的结果，要求key，value都不能为空；如果之前没有值，返回null
     *
     * @param key
     * @param value
     * @return
     */
    public byte[] setAndGetOldValue(String key, String value) {
        return redisTemplate.execute((RedisCallback<byte[]>) con -> con.getSet(key.getBytes(), value.getBytes()));
    }

    public Boolean setValue(String key, String value) {
        return redisTemplate
                .execute((RedisCallback<Boolean>) connection -> connection.set(key.getBytes(), value.getBytes()));
    }

    public byte[] getValue(String key) {
        return redisTemplate.execute((RedisCallback<byte[]>) connection -> connection.get(key.getBytes()));
    }

    public Boolean mSetValue(Map<String, String> values) {
        Map<byte[], byte[]> map = new HashMap<>(values.size());
        for (Map.Entry<String, String> entry : values.entrySet()) {
            map.put(entry.getKey().getBytes(), entry.getValue().getBytes());
        }

        return redisTemplate.execute((RedisCallback<Boolean>) con -> con.mSet(map));
    }

    public List<byte[]> mGetValue(List<String> keys) {
        return redisTemplate.execute((RedisCallback<List<byte[]>>) con -> {
            byte[][] bkeys = new byte[keys.size()][];
            for (int i = 0; i < keys.size(); i++) {
                bkeys[i] = keys.get(i).getBytes();
            }
            return con.mGet(bkeys);
        });
    }
}