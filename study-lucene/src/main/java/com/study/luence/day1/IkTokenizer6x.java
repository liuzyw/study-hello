package com.study.luence.day1;

import java.io.IOException;
import java.io.Serializable;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

/**
 * Created on 2019-02-20
 *
 * @author liuzhaoyuan
 */
public class IkTokenizer6x extends Tokenizer implements Serializable {

    private static final Long serialVersionUID = 1L;


    private IKSegmenter ikSegmenter;

    private CharTermAttribute termAttribute;

    private OffsetAttribute offsetAttribute;

    private TypeAttribute typeAttribute;

    private int endPoition;

    public IkTokenizer6x(boolean useSmart) {

        super();

        offsetAttribute = addAttribute(OffsetAttribute.class);
        termAttribute = addAttribute(CharTermAttribute.class);
        typeAttribute = addAttribute(TypeAttribute.class);
        ikSegmenter = new IKSegmenter(input, useSmart);

    }

    @Override
    public boolean incrementToken() throws IOException {

        clearAttributes();

        Lexeme lexeme = ikSegmenter.next();

        if (lexeme != null) {
            termAttribute.append(lexeme.getLexemeText());
            termAttribute.setLength(lexeme.getLength());
            offsetAttribute.setOffset(lexeme.getBeginPosition(), lexeme.getEndPosition());
            endPoition = lexeme.getEndPosition();
            typeAttribute.setType(lexeme.getLexemeTypeString());
            return true;
        }

        return false;
    }


    @Override
    public void reset() throws IOException {
        super.reset();
        ikSegmenter.reset(input);
    }

    @Override
    public void end() throws IOException {
        int finalOffset = correctOffset(this.endPoition);
        offsetAttribute.setOffset(finalOffset, finalOffset);
    }
}
