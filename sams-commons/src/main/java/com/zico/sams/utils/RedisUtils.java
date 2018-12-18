package com.zico.sams.utils;

import com.alibaba.fastjson.JSONObject;
import com.zico.sams.entity.UserInfo;
import com.zico.sams.execption.SamsException;
import com.zico.sams.redis.entity.RedisObject;
import com.zico.sams.redis.entity.RedisObjects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


/**
 * @author zico
 * @version v1.0
 * @title RedisUtil
 * @package com.zico.sams.utils
 * @since 2018-12-15
 * description redis工具类
 **/
@Slf4j
@Component
public class RedisUtils {

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> autowiredRedisTemplate;

    private static RedisTemplate<String, Object> redisTemplate;


    @PostConstruct
    public void init() {
        autowiredRedisTemplate.setKeySerializer(new StringRedisSerializer());
        autowiredRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        autowiredRedisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        autowiredRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 开启事务
        autowiredRedisTemplate.setEnableTransactionSupport(true);
        RedisUtils.redisTemplate = autowiredRedisTemplate;
    }

    /**
     * 半个小时后过期
     */
    private static final Long time = 1800L;

    public RedisUtils() {
    }

    public static UserInfo fetchUserInfo(String token) {
        RedisObject redisObject = fetch(token);
        if (!redisObject.isOk()) {
            throw new SamsException("LOGIN_TIME_OUT");
        }
        return (UserInfo) redisObject.getValue();
    }

    public static void store(String key, Object value) {
        store(key, value, 0);
    }

    public static void store(String key, Object value, int timeout) {
        log.debug("###setObject### key = {}, value = {}", key, JSONObject.toJSONString(value));
        try {
            if (timeout > 0) {
                redisTemplate.opsForValue().set(key, value, timeout);
            } else {
                redisTemplate.opsForValue().set(key, value);
            }
        } catch (Exception e) {
            log.error(String.format("store error! key=%s", key), e);
        }
    }

    public static RedisObject fetch(String key) {
        try {
            Object value = redisTemplate.opsForValue().get(key);
            log.debug("###getObject### key = {}, value = {}", key, JSONObject.toJSONString(value));
            return null == value ? RedisObjects.badConnection() : RedisObjects.ok(value);
        } catch (Exception e) {
            log.error(String.format("fetch error! key=%s", key), e);
            return RedisObjects.badValue();
        }
    }

    public static RedisObject deleteObject(String key) {
        try {
            log.debug("###deleteObject### key = {}", key);
            boolean flag = redisTemplate.opsForValue().getOperations().delete(key);
            return !flag ? RedisObjects.badConnection() : RedisObjects.ok(key);
        } catch (Exception e) {
            log.error(String.format("delete error! key=%s", key), e);
            return RedisObjects.badValue();
        }
    }

    public static void update(String key) {
        redisTemplate.opsForValue().getOperations().expire(key, time, TimeUnit.SECONDS);
    }
}
