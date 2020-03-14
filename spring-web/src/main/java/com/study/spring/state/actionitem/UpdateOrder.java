package com.study.spring.state.actionitem;

import com.study.spring.state.action.ActionItem;
import java.util.Map;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class UpdateOrder implements ActionItem<String> {

    private static final Long serialVersionUID = 1L;


    @Override
    public String doBiz(Map<String, Object> input) {
        return null;
    }
}
