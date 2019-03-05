package com.study.elasticsearch;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;

/**
 * Created on 2019-03-03
 *
 * @author liuzhaoyuan
 */
public class EsMatchQueryTest {

    private static final Long serialVersionUID = 1L;

    public static void main(String[] args) throws Exception {
//        QueryBuilder query = QueryBuilders
//            .matchQuery("title", "Python")
//            .operator(Operator.AND.AND);

//        QueryBuilder query = QueryBuilders.termQuery("title", "java");

//        QueryBuilder query = QueryBuilders.rangeQuery("price").from(50).to(70).includeLower(true).includeUpper(false);

        QueryBuilder matchQuery1 = QueryBuilders.matchQuery("title", "java");
        QueryBuilder matchQuery2 = QueryBuilders.matchQuery("description", "虚拟机");
        QueryBuilder rangeQuery = QueryBuilders.rangeQuery("price").gte(70);
        QueryBuilder query = QueryBuilders.boolQuery().must(matchQuery1).should(matchQuery2).mustNot(rangeQuery);

        HighlightBuilder highlighter = new HighlightBuilder()
            .field("title").preTags("<span style =\"color: red\">").preTags("</span>");

        SearchResponse response = ClientUtils.getClient().prepareSearch("books").setQuery(query)
            .highlighter(highlighter).setSize(10).get();

        SearchHits hits = response.getHits();

        for (SearchHit hit : hits) {
            System.out.println("Soucrce:" + hit.getSourceAsString());
            System.out.println("Soucrce As Map:" + hit.getSourceAsMap());
            System.out.println("Index:" + hit.getIndex());
            System.out.println("Type:" + hit.getType());
            System.out.println("ID:" + hit.getId());
            System.out.println("Price:" + hit.getSourceAsMap().get("price"));
            System.out.println("Score:" + hit.getScore());

            Text[] text = hit.getHighlightFields().get("title").getFragments();
            if (text != null) {
                for (Text text1 : text) {
                    System.out.println(text1);
                }
            }
        }
    }


}
