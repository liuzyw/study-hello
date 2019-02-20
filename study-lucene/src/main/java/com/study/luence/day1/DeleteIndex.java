package com.study.luence.day1;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * Created on 2019-02-20
 *
 * @author liuzhaoyuan
 */
public class DeleteIndex implements Serializable {

    private static final Long serialVersionUID = 1L;


    public static void main(String[] args) {

        deleteDoc("title", "美国");

    }

    public static void deleteDoc(String field, String key) {
        Analyzer analyzer = new IKAnalyzer6x();
        IndexWriterConfig icw = new IndexWriterConfig(analyzer);
        Path indexPath = Paths.get("/Users/liuzhaoyuan/gitwork/study-hello/study-lucene/indexdir");
        Directory directory;
        try {
            directory = FSDirectory.open(indexPath);
            IndexWriter indexWriter = new IndexWriter(directory, icw);
            indexWriter.deleteDocuments(new Term(field, key));
            indexWriter.commit();
            indexWriter.close();
            System.out.println("删除完成 !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
