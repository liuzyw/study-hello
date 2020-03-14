package com.study.spring.state.enums;

import com.study.spring.state.runtime.TypeFormatter;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public enum RuleTypeEnum {


    STRING("String", new TypeFormatter() {
        @Override
        public Comparable format(Object obj) {
            return null == obj ? null : obj.toString();
        }
    }),

    INTEGER("Integer", new TypeFormatter() {
        @Override
        public Comparable format(Object obj) {
            return Integer.valueOf(obj.toString());
        }
    });

    public String type;

    public TypeFormatter typeFormatter;


    RuleTypeEnum(String type, TypeFormatter typeFormatter) {
        this.type = type;
        this.typeFormatter = typeFormatter;
    }


    public static RuleTypeEnum getRuleType(String type) {
        for (RuleTypeEnum typeEnum : values()) {
            if (typeEnum.type.equals(type)) {
                return typeEnum;
            }
        }

        return null;
    }
}
