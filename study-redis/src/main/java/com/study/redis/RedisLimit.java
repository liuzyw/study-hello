package com.study.redis;

import java.util.Collections;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

/**
 * Function:
 */
public class RedisLimit {

    private JedisCommands jedis;
    private int limit = 200;

    private static final int FAIL_CODE = 0;

    /**
     * lua script
     */
    private String script;

    private RedisLimit(Builder builder) {
        this.limit = builder.limit;
        this.jedis = builder.jedis;
        buildScript();
    }


    /**
     * limit traffic
     *
     * @return if true
     */
    public boolean limit() {
        String key = String.valueOf(System.currentTimeMillis() / 1000);
        Object result = null;
        if (jedis instanceof Jedis) {
            result = ((Jedis) this.jedis)
                .eval(script, Collections.singletonList(key), Collections.singletonList(String.valueOf(limit)));
        } else if (jedis instanceof JedisCluster) {
            result = ((JedisCluster) this.jedis)
                .eval(script, Collections.singletonList(key), Collections.singletonList(String.valueOf(limit)));
        } else {
            //throw new RuntimeException("instance is error") ;
            return false;
        }

        if (FAIL_CODE != (Long) result) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * read lua script
     */
    private void buildScript() {
        script = ScriptUtil.getScript("limit.lua");
    }


    /**
     * the builder
     */
    public static class Builder<T extends JedisCommands> {

        private T jedis = null;

        private int limit = 200;


        public Builder(T jedis) {
            this.jedis = jedis;
        }

        public Builder limit(int limit) {
            this.limit = limit;
            return this;
        }

        public RedisLimit build() {
            return new RedisLimit(this);
        }

    }
}