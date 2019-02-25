package com.study.luence.day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;

public class GetTopTerms {

    public static void main(String[] args) throws Exception {
        //
//        aa();

        bb();
    }

    private static void aa() throws Exception {
        News news1 = new News();
        news1.setId(10);
        news1.setTitle("李开复:无人驾驶进入黄金时代");
        news1.setReply(609);
        news1.setContent(textToString());

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

        writer.addDocument(doc1);
        writer.commit();
        writer.close();
        directory.close();

        Date end = new Date();
        System.out.println("索引文档用时:" + (end.getTime() - start.getTime()));
    }

    private static void bb() throws Exception {
        Path indexPath = Paths.get("/Users/liuzhaoyuan/gitwork/study-hello/study-lucene/indexdir");

        Directory directory = FSDirectory.open(indexPath);
        IndexReader reader = DirectoryReader.open(directory);
        // 因为只索引了一个文档，所以DocID为0，通过getTermVector获取content字段的词项
        Terms terms = reader.getTermVector(9, "content");

        // 遍历词项
        TermsEnum termsEnum = terms.iterator();
        BytesRef thisTerm = null;
        Map<String, Integer> map = new HashMap<String, Integer>();
        while ((thisTerm = termsEnum.next()) != null) {
            // 词项
            String termText = thisTerm.utf8ToString();
            // 通过totalTermFreq()方法获取词项频率
            map.put(termText, (int) termsEnum.totalTermFreq());
        }

        // 按value排序
        List<Entry<String, Integer>> sortedMap = new ArrayList<Entry<String, Integer>>(map.entrySet());
        Collections.sort(sortedMap, new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });

        // System.out.println(sortedMap);
        getTopN(sortedMap, 10);
    }

    // 获取top-n
    private static void getTopN(List<Entry<String, Integer>> sortedMap, int N) {
        for (int i = 0; i < N; i++) {
            System.out.println(sortedMap.get(i).getKey() + ":" + sortedMap.get(i).getValue());
        }
    }

    private static String textToString() {
        File file = new File("/Users/liuzhaoyuan/gitwork/study-hello/study-lucene/file/news.txt");
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
            String str = null;
            while ((str = br.readLine()) != null) {// 使用readLine方法，一次读一行
                result.append(System.lineSeparator() + str);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
