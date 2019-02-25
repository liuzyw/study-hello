package com.study.luence.day1;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


/**
 * 模糊查询
 */
public class FuzzyQueryTest {

    public static void main(String[] args) throws IOException {
        String field = "title";
        Path indexPath = Paths.get("/Users/liuzhaoyuan/gitwork/study-hello/study-lucene/indexdir");
        Directory dir = FSDirectory.open(indexPath);
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);

        Term term = new Term("title", "CHINA");
        FuzzyQuery fuzzyQuery = new FuzzyQuery(term);
        System.out.println("Query:" + fuzzyQuery);

        // 返回前10条
        TopDocs tds = searcher.search(fuzzyQuery, 10);
        for (ScoreDoc sd : tds.scoreDocs) {
            // Explanation explanation = searcher.explain(query, sd.doc);
            // System.out.println("explain:" + explanation + "\n");
            Document doc = searcher.doc(sd.doc);
            System.out.println("DocID:" + sd.doc);
            System.out.println("id:" + doc.get("id"));
            System.out.println("title:" + doc.get("title"));
            System.out.println("文档评分:" + sd.score);
        }
        dir.close();
        reader.close();

    }

}
