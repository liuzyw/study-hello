package com.study.database.table.shardingjdbc;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Created on 2018-05-11
 *
 * 分库的逻辑函数
 *
 * userId % 2 == 0  receipt_1
 *
 * userId % 2 == 1  receipt_2
 *
 * @author liuzhaoyuan
 */
public class ReceiptSingleKeyDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Integer> {

    /**
     * sql 中关键字 匹配符为 =的时候，表的路由函数
     *
     * @return
     */
    @Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
        for (String each : availableTargetNames) {
            if (each.endsWith(((shardingValue.getValue() % 2) + 1) + "")) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * sql 中关键字 匹配符为 in 的时候，表的路由函数
     *
     * @return
     */
    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        for (Integer value : shardingValue.getValues()) {
            for (String tableName : availableTargetNames) {
                if (tableName.endsWith(((value.intValue() % 2) + 1) + "")) {
                    result.add(tableName);
                }
            }
        }
        return result;
    }

    /**
     * sql 中关键字 匹配符为 between的时候，表的路由函数
     *
     * @return
     */
    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames, ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Range<Integer> range = shardingValue.getValueRange();
        for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : availableTargetNames) {
                if (each.endsWith(((i.intValue() % 2) + 1) + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}
