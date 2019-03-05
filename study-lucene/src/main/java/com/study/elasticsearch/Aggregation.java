package com.study.elasticsearch;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.avg.Avg;
import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.min.Min;
import org.elasticsearch.search.aggregations.metrics.min.MinAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.stats.Stats;
import org.elasticsearch.search.aggregations.metrics.stats.StatsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;

/**
 * Created on 2019-03-03
 *
 * @author liuzhaoyuan
 */
public class Aggregation {

    private static final Long serialVersionUID = 1L;


    public static void main(String[] args) throws Exception {

        MinAggregationBuilder minAgg = AggregationBuilders.min("agg").field("price");

        TransportClient client = ClientUtils.getClient();

        SearchResponse response = client.prepareSearch("books").addAggregation(minAgg).execute().actionGet();
        Min min = response.getAggregations().get("agg");

        double minValue = min.getValue();
        System.out.println(minValue);

        SumAggregationBuilder sumAgg = AggregationBuilders.sum("agg").field("price");
        response = client.prepareSearch("books").addAggregation(sumAgg).execute().actionGet();
        Sum sumvalue = response.getAggregations().get("agg");
        System.out.println(sumvalue.getValue());

        AvgAggregationBuilder avgAgg = AggregationBuilders.avg("agg").field("price");
        response = client.prepareSearch("books").addAggregation(avgAgg).execute().actionGet();
        Avg avg = response.getAggregations().get("agg");
        double avgValue = avg.getValue();
        System.out.println(avgValue);

        StatsAggregationBuilder statsAgg = AggregationBuilders.stats("agg").field("price");
        response = client.prepareSearch("books").addAggregation(statsAgg).execute().actionGet();
        Stats statsValues = response.getAggregations().get("agg");
        System.out.println(statsValues.getMin());
        System.out.println(statsValues.getMax());
        System.out.println(statsValues.getAvg());
        System.out.println(statsValues.getSum());
        System.out.println(statsValues.getCount());

    }


}
