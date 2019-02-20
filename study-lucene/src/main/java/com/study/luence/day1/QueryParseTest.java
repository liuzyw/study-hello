package com.study.luence.day1;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.QueryParser.Operator;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * Created on 2019-02-20
 *
 * @author liuzhaoyuan
 */
public class QueryParseTest implements Serializable {

    private static final Long serialVersionUID = 1L;


    public static void main(String[] args) throws Exception {

        String field = "title";

        Path indexPath = Paths.get("/Users/liuzhaoyuan/gitwork/study-hello/study-lucene/indexdir");

        Directory directory = FSDirectory.open(indexPath);

        IndexReader reader = DirectoryReader.open(directory);

        IndexSearcher searcher = new IndexSearcher(reader);

        Analyzer analyzer = new IKAnalyzer6x();

        QueryParser queryParser = new QueryParser(field, analyzer);

        queryParser.setDefaultOperator(Operator.AND);

        // 查询关键字
        Query query = queryParser.parse("北京大学");

        System.out.println("Query:" + query.toString());

        TopDocs topDocs = searcher.search(query, 10);

        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document doc = searcher.doc(scoreDoc.doc);
            System.out.println("DocId:" + scoreDoc.doc);
            System.out.println("id:" + doc.get("id"));
            System.out.println("title:" + doc.get("title"));
            System.out.println("文档评分:" + scoreDoc.score);
        }




    }

}
