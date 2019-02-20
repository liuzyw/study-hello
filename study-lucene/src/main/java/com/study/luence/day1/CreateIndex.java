package com.study.luence.day1;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * Created on 2019-02-20
 *
 * @author liuzhaoyuan
 */
public class CreateIndex implements Serializable {

    private static final Long serialVersionUID = 1L;

    public static void main(String[] args) throws IOException {
        //创建 3 个 News 对象
        News news1 = new News();
        news1.setId(1);
        news1.setTitle("习近平会见美国总统奥巴马，学习国外经验");
        news1.setContent("国家主席习近平 9月 3日在杭州西湖国宾馆会见前米出席二 十国集团领导人杭州峰会的美国总统奥巴马.");
        news1.setReply(672);

        News news2 = new News();
        news2.setId(2);
        news2.setTitle("北大迎 4380名新生农村学生 700多人近年最多");
        news2.setContent("昨天，北京大学迎来 4380名来自全国各地及数卡个同家的本科新生。其中，农村学生共 700 余名，为近年最多...");
        news2.setReply(995);

        News news3 = new News();
        news3.setId(3);
        news3.setTitle("特朗普宣誓(Donald Trump)就任美国第 45任总统");
        news3.setContent("当地时间 1月 20日，唐纳德·特朗普在美国国会宣誓就就职，正式成为美国第45任总统");
        news3.setReply(1872);

        Analyzer analyzer = new IKAnalyzer6x();

        IndexWriterConfig icw = new IndexWriterConfig(analyzer);
        icw.setOpenMode(OpenMode.CREATE);

        Directory directory = null;

        IndexWriter writer = null;

        Path indexPath = Paths.get("/Users/liuzhaoyuan/gitwork/study-hello/study-lucene/indexdir");

        Date start = new Date();

        directory = FSDirectory.open(indexPath);

        writer = new IndexWriter(directory, icw);

        FieldType idType = new FieldType();
        idType.setIndexOptions(IndexOptions.DOCS);
        idType.setStored(true);

        FieldType titleType = new FieldType();
        titleType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
        titleType.setStored(true);
        titleType.setTokenized(true);

        FieldType contentType = new FieldType();
        contentType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
        contentType.setStored(true);
        contentType.setTokenized(true);
        contentType.setStoreTermVectors(true);
        contentType.setStoreTermVectorPositions(true);
        contentType.setStoreTermVectorOffsets(true);
        contentType.setStoreTermVectorPayloads(true);

        Document doc1 = new Document();
        doc1.add(new Field("id", String.valueOf(news1.getId()), idType));
        doc1.add(new Field("title", news1.getTitle(), titleType));
        doc1.add(new Field("content", news1.getContent(), contentType));
        doc1.add(new IntPoint("reply", news1.getReply()));
        doc1.add(new StoredField("reply display", news1.getReply()));

        Document doc2 = new Document();
        doc2.add(new Field("id", String.valueOf(news2.getId()), idType));
        doc2.add(new Field("title", news2.getTitle(), titleType));
        doc2.add(new Field("content", news2.getContent(), contentType));
        doc2.add(new IntPoint("reply", news2.getReply()));
        doc2.add(new StoredField("reply display", news2.getReply()));

        Document doc3 = new Document();
        doc3.add(new Field("id", String.valueOf(news3.getId()), idType));
        doc3.add(new Field("title", news3.getTitle(), titleType));
        doc3.add(new Field("content", news3.getContent(), contentType));
        doc3.add(new IntPoint("reply", news3.getReply()));
        doc3.add(new StoredField("reply display", news3.getReply()));

        writer.addDocument(doc1);
        writer.addDocument(doc2);
        writer.addDocument(doc3);
        writer.commit();
        writer.close();
        directory.close();

        Date end = new Date();
        System.out.println("索引文档用时:" + (end.getTime() - start.getTime()));

    }


}
