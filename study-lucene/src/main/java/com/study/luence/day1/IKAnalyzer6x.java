package com.study.luence.day1;

import java.io.Serializable;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;

/**
 * Created on 2019-02-20
 *
 * @author liuzhaoyuan
 */
public class IKAnalyzer6x extends Analyzer implements Serializable {

    private static final Long serialVersionUID = 1L;

    private boolean useSmart;


    public IKAnalyzer6x(boolean useSmart) {
        this.useSmart = useSmart;
    }

    public IKAnalyzer6x() {
        this(true);
    }

    public boolean isUseSmart() {
        return useSmart;
    }

    public void setUseSmart(boolean useSmart) {
        this.useSmart = useSmart;
    }

    @Override
    protected TokenStreamComponents createComponents(String s) {

        Tokenizer tokenizer = new IkTokenizer6x(this.useSmart);

        return new TokenStreamComponents(tokenizer);
    }
}
