package com.study.redis;

import java.util.Collections;
import redis.clients.jedis.Jedis;

/**
 * Created on 2018-11-23
 *
 * @author liuzhaoyuan
 */
public class LockU {

    private static final String LOCK_PREFIX = "lock_";

    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final String LOCK_MSG = "OK";
    private static final Long UNLOCK_MSG = 1L;


    private static final String UNLOCK_LUA = "if redis.call('get', KEYS[1]) == ARGV[1] then\n"
        + "    return redis.call('del', KEYS[1])\n"
        + "else\n"
        + "    return 0\n"
        + "end";


    private Jedis jedis;

    public LockU(Jedis jedis) {
        this.jedis = jedis;
    }

    /**
     * 时间是毫秒
     *
     * @param key
     * @param value
     * @param time
     *
     * @return
     */
    public boolean tryLock(String key, String value, int time) {

        try {
            String msg = jedis.set(key, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, time);

            if (LOCK_MSG.equals(msg)) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean unlock(String key, String value) {
        try {
            Object result = jedis.eval(UNLOCK_LUA, Collections.singletonList(key), Collections.singletonList(value));
            if (UNLOCK_MSG.equals(result)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
