package com.study.spring.etc;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created on 2018-06-27
 *
 * @author liuzhaoyuan
 */
public class MyImportSelector implements ImportSelector {

    /**
     * 返回值是要导入的全类名
     *
     * @param importingClassMetadata
     *
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.study.spring.beans.Dog"};
    }
}
