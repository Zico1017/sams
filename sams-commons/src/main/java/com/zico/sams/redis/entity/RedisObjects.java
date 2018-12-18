package com.zico.sams.redis.entity;

/**
 * @author zico
 * @version v1.0
 * @title RedisObjects
 * @package com.zico.sams.redis.entity
 * @since 2018-12-15
 * description redis实体类
 **/
public class RedisObjects {
    public RedisObjects() {
    }

    public static RedisObject ok(Object value) {
        return new RedisObject(value, 0);
    }

    public static RedisObject badConnection() {
        return new RedisObject((Object) null, 1);
    }

    public static RedisObject badValue() {
        return new RedisObject((Object) null, 2);
    }

    public static RedisObject noValue() {
        return new RedisObject((Object) null, 4);
    }

    public static RedisObject sessionNotFound() {
        return new RedisObject((Object) null, 3);
    }
}
