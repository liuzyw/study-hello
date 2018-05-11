package com.study.redis;

import java.util.HashMap;
import java.util.Map;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * Created on 2018-05-05
 *
 * @author liuzhaoyuan
 */
public class TestRedis {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        System.out.println("服务正在运行: " + jedis.ping());
        System.out.println("info" + jedis.info());
        System.out.println();
        System.out.println();
        System.out.println("--------  string  -------");
        System.out.println("jedis set age: " + jedis.set("age", "21"));
        System.out.println("jedis setnx age: " + jedis.setnx("Age", "100"));
        System.out.println("jedis get age: " + jedis.get("age"));
        System.out.println("jedis del age: " + jedis.del("age"));

        System.out.println();
        System.out.println();
        System.out.println("--------  list  -------");
        System.out.println("jedis set list: " + jedis.lpush("list", "d", "c", "b", "b", "a"));
        System.out.println("jedis get list: " + jedis.lrange("list", 0, -1));
        System.out.println("jedis get index3 list: " + jedis.lindex("list", 3));
        System.out.println("jedis pop list: " + jedis.lpop("list"));
        System.out.println("jedis lrem b list: " + jedis.lrem("list", 0, "b"));
        System.out.println("jedis del list: " + jedis.del("list"));

        System.out.println();
        System.out.println();
        System.out.println("--------  hash  -------");
        Map<String, String> map = new HashMap<String, String>();
        map.put("Tom", "4");
        map.put("Jeck", "5");
        map.put("Ead", "6");
        System.out.println("jedis set hash: " + jedis.hmset("hash", map));
        System.out.println("jedis get hashAll: " + jedis.hgetAll("hash"));
        System.out.println("jedis set hash Jeck:" + jedis.hset("hash", "Jeck", "100"));
        System.out.println("jedis get hash Jeck: " + jedis.hget("hash", "Jeck"));
        System.out.println("jedis get hash len: " + jedis.hlen("hash"));
        System.out.println("jedis del hash: " + jedis.del("hash"));

        System.out.println();
        System.out.println();
        System.out.println("--------  set  -------");
        System.out.println("jedis set set: " + jedis.sadd("set", "a", "b", "c", "d"));
        System.out.println("jedis set add set:" + jedis.sadd("set", "a"));
        System.out.println("jedis get set: " + jedis.scard("set"));
        System.out.println("jedis is contain c set: " + jedis.sismember("set", "c"));
        System.out.println("jedis srem set: " + jedis.srem("set", "a"));
        System.out.println("jedis get set mb: " + jedis.smembers("set"));
        System.out.println("jedis del set: " + jedis.del("set"));

        System.out.println();
        System.out.println();
        System.out.println("--------  zset  -------");
        System.out.println("jedis set zset: " + jedis.zadd("zset", 1, "a"));
        System.out.println("jedis set zset: " + jedis.zadd("zset", 2, "b"));
        System.out.println("jedis del zset: " + jedis.del("zset"));

        System.out.println();
        System.out.println();
        System.out.println("--------  redisChat  -------");
        System.out.println("jedis public: " + jedis.publish("redisChat", "my first message.."));

        System.out.println();
        System.out.println();
        System.out.println("--------  Transaction  -------");

        // 开始事务
        Transaction t = jedis.multi();
        t.set("Mt", "mt");
        t.set("Dp", "dp");
        // 执行事务
        t.exec();
        jedis.del("Mt");
        jedis.del("Dp");

// ------discard 取消执行事务内命令---------
        jedis.set("discard", "exec");
        Transaction t2 = jedis.multi();
        t2.set("discard", "discard");
        t2.discard();
        jedis.del("discard");

        jedis.disconnect();
    }
}
