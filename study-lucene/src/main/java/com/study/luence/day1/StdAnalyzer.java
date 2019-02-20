package com.study.luence.day1;

import java.io.Serializable;
import java.io.StringReader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

/**
 * Created on 2019-02-20
 *
 * @author liuzhaoyuan
 */
public class StdAnalyzer implements Serializable {

    private static final Long serialVersionUID = 1L;


    private static String srtCh = "IK Analyzer 是一个开源的，基于java语言开发的轻量级的中文分词工具包。从2006年12月推出1.0版开始， "
        + "IKAnalyzer已经推出了4个大版本。最初，它是以开源项目Luence为应用主体的，结合词典分词和文法分析算法的中文分词组件。"
        + "从3.0版本开始，IK发展为面向Java的公用分词组件，独立于Lucene项目，同时提供了对Lucene的默认优化实现。在2012版本中，"
        + "IK实现了简单的分词歧义排除算法，标志着IK分词器从单纯的词典分词向模拟语义分词衍化。";


    public static void main(String[] args) throws Exception {
//        variousAnalyzer();

        iKAnalyzer6x();

    }

    private static void printResult(Analyzer analyzer) throws Exception {

        StringReader reader = new StringReader(srtCh);

        TokenStream stream = analyzer.tokenStream(srtCh, reader);

        stream.reset();

        CharTermAttribute termAttribute = stream.getAttribute(CharTermAttribute.class);

        while (stream.incrementToken()) {
            System.out.println(termAttribute.toString());
        }
        System.out.println("-----------------> ");
        analyzer.close();
    }

    private static void variousAnalyzer() throws Exception {
        Analyzer analyzer = new StandardAnalyzer();
        System.out.println("标准分词结果：");
        printResult(analyzer);

        analyzer = new WhitespaceAnalyzer();
        System.out.println("空格分词结果：");
        printResult(analyzer);

        analyzer = new SimpleAnalyzer();
        System.out.println("简单分词结果：");
        printResult(analyzer);

        analyzer = new CJKAnalyzer();
        System.out.println("二分分词结果：");
        printResult(analyzer);

        analyzer = new KeywordAnalyzer();
        System.out.println("关键字分词结果：");
        printResult(analyzer);

        analyzer = new StopAnalyzer();
        System.out.println("停用分词结果：");
        printResult(analyzer);

        analyzer = new SmartChineseAnalyzer();
        System.out.println("中文分词结果：");
        printResult(analyzer);


    }

    private static void iKAnalyzer6x() throws Exception {
        Analyzer analyzer = new IKAnalyzer6x();
        System.out.println("分词结果：");
        printResult(analyzer);

        analyzer = new IKAnalyzer6x(true);
        System.out.println("分词结果：");
        printResult(analyzer);
    }


}
