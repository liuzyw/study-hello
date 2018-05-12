package com.study.data.table.algorithm;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;
import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * Created on 2018-05-11
 *
 * receiptId % 2 == 0 receipt_1
 *
 * receiptId % 2 == 1 receipt_2
 *
 * @author liuzhaoyuan
 */
public class ReceiptSingleKeyTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Long> {

    @Override
    public String doEqualSharding(Collection<String> tableNames, ShardingValue<Long> shardingValue) {
        for (String each : tableNames) {
            if (each.endsWith(((shardingValue.getValue() % 2) + 1) + "")) {
                System.out.println("------find table ------> " + each);
                return each;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Collection<String> doInSharding(Collection<String> tableNames, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(tableNames.size());
        for (Long value : shardingValue.getValues()) {
            for (String tableName : tableNames) {
                if (tableName.endsWith(((value.intValue() % 2) + 1) + "")) {
                    System.out.println("------find table ------> " + tableName);
                    result.add(tableName);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> tableNames, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(tableNames.size());
        Range<Long> range = shardingValue.getValueRange();
        for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : tableNames) {
                if (each.endsWith(((i.intValue() % 2) + 1) + "")) {
                    System.out.println("------find table ------> " + each);
                    result.add(each);
                }
            }
        }
        return result;
    }
}
