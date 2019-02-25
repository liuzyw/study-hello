package com.study.luence.day1;

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
 * Created on 2019-02-21
 *
 * @author liuzhaoyuan
 */
public class AddDoc implements Serializable {

    private static final Long serialVersionUID = 1L;


    public static void main(String[] args) throws Exception{


        News news1 = new News();
        news1.setId(8);
        news1.setTitle("CHINA IS WORLD’S SECOND");
        news1.setContent("Visitors watch a demonstration of robot surgery system during the World Artificial Intelligence Conference (WAIC) 2018 in Shanghai, east China, Sept. 17, 2018.");
        news1.setReply(235);

        News news2 = new News();
        news2.setId(9);
        news2.setTitle("CHINA’S ENGEL COEFFICIENT FALLS TO A RECORD LOW IN 2018");
        news2.setContent("The Engel coefficient, a measure of food expenditure as a proportion of total household spending, fell to 28.4 percent last year in China, down 0.9 percentage points from the previous year, according to the National Bureau of Statistics (NBS).");
        news2.setReply(678);


        Analyzer analyzer = new IKAnalyzer6x();

        IndexWriterConfig icw = new IndexWriterConfig(analyzer);
        icw.setOpenMode(OpenMode.CREATE_OR_APPEND);

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


        writer.addDocument(doc1);
        writer.addDocument(doc2);
        writer.commit();
        writer.close();
        directory.close();

        Date end = new Date();
        System.out.println("索引文档用时:" + (end.getTime() - start.getTime()));
    }

}
