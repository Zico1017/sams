package com.zico.sams.redis.entity;

/**
 * @author zico
 * @version v1.0
 * @title RedisObject
 * @package com.zico.sams.redis
 * @since 2018-12-15
 * description redis实体类
 **/
public class RedisObject {

    public static final int STATUS_OK = 0;
    public static final int STATUS_BAD_CONNECTION = 1;
    public static final int STATUS_BAD_VALUE = 2;
    public static final int SESSION_NOT_FOUND = 3;
    public static final int NO_VAUE = 4;
    private Object value;
    private int status;

    public RedisObject() {
        this.status = 0;
        this.value = null;
    }

    public RedisObject(Object value, int status) {
        this.value = value;
        this.status = status;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isOk() {
        return this.status == 0;
    }

    public <T> T unwrap(Class<T> c) {
        return c.cast(this.value);
    }

    public Object getValue() {
        return this.value;
    }
}
