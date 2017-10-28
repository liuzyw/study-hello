package com.study.util;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

/**
 * Created on 2017-10-18
 *
 * @author liuzhaoyuan
 */
public class GuavaTest {

    @Test
    public void tcache() {
        LoadingCache<Integer, String> cache
            = CacheBuilder.newBuilder().expireAfterAccess(2,TimeUnit.SECONDS).initialCapacity(2).maximumSize(8).build(new CacheLoader<Integer, String>() {
            @Override
            public String load(Integer integer) throws Exception {
                return "1";
            }
        });

        for (int i = 1; i < 5; i++) {
            cache.put(i, "name" + i);
        }
        System.out.println(cache.size());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 5; i < 11; i++) {
            cache.put(i, "name" + i);
        }
        System.out.println(cache.asMap().toString());
        try {
            System.out.println(cache.get(1));
            System.out.println(cache.get(4));
        } catch (ExecutionException e) {
        }
        System.out.println(cache.asMap().toString());
        System.out.println();
    }

}
