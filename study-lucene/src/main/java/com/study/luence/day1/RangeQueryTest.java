package com.study.luence.day1;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


/**
 * 范围查询
 */
public class RangeQueryTest {

    public static void main(String[] args) throws IOException {

        Path indexPath = Paths.get("/Users/liuzhaoyuan/gitwork/study-hello/study-lucene/indexdir");
        Directory dir = FSDirectory.open(indexPath);
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);

        Query rangeQuery = IntPoint.newRangeQuery("reply", 500, 1000);
        System.out.println("Query:" + rangeQuery);
        // 返回前10条
        TopDocs tds = searcher.search(rangeQuery, 10);
        for (ScoreDoc sd : tds.scoreDocs) {

            Document doc = searcher.doc(sd.doc);
            System.out.println("DocID:" + sd.doc);
            System.out.println("id:" + doc.get("id"));
            System.out.println("title:" + doc.get("title"));
            System.out.println("Reply:" + doc.get("reply display"));
            System.out.println("文档评分:" + sd.score);
        }
        dir.close();
        reader.close();

    }
}
