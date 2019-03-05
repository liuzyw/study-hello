package com.study.elasticsearch;


import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentFactory;

public class TestClient {


    public static void main(String[] args) throws Exception {

        TransportClient client = ClientUtils.getClient();

        // 查询
        GetResponse getResponse = client.prepareGet("books", "IT", "2").get();
        System.out.println(getResponse.getSourceAsString());

        // 判断索引
//        IndicesAdminClient adminClient = client.admin().indices();
//        IndicesExistsResponse indexName = adminClient.prepareExists("indexName").get();
//        System.out.println(indexName.isExists());

        // 创建一个索引
//        CreateIndexResponse createIndexResponse = adminClient.prepareCreate("twitter").addMapping("tweet",
//            XContentFactory.jsonBuilder().startObject()
//                .startObject("properties").startObject("name").field("type", "keyword").endObject().endObject().endObject()).get();
//        System.out.println(createIndexResponse.isAcknowledged());
//        indexName = adminClient.prepareExists("twitter").get();
//        System.out.println(indexName.isExists());

        // 删除索引
//        AcknowledgedResponse acknowledgedResponse = adminClient
//            .prepareDelete("twitter").get();
//        System.out.println(acknowledgedResponse.isAcknowledged());

        // 添加信息
//        IndexResponse response = client.prepareIndex("twitter", "tweet", "1").setSource(XContentFactory.jsonBuilder()
//            .startObject().field("user", "kimchy")
//            .field("postDate", "2013-01-30")
//            .field("message", "trying out Elasticsearch").endObject()).get();
//        System.out.println(response.status());

        // 更新信息
        UpdateResponse updateResponse = client.prepareUpdate("twitter", "tweet", "1")
            .setDoc(XContentFactory.jsonBuilder().startObject()
            .field("user","male").endObject()).get();
//        System.out.println(updateResponse.getGetResult().getVersion());

        getResponse = client.prepareGet("twitter", "tweet", "1").get();
        System.out.println(getResponse.getSourceAsString());

    }


}