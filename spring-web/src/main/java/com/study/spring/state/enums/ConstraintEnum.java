package com.study.spring.state.enums;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public enum ConstraintEnum {


    BIGGER(">"),

    LESS("<"),

    EQUALS("="),

    NOT_EQUALS("!="),

    BIGGER_EQUALS("≥"),

    LESS_EQUALS("≤"),

    IS_NULL("IS_NULL");


    public String symbol;


    ConstraintEnum(String symbol) {
        this.symbol = symbol;
    }


    public boolean compareTo(Comparable left, Comparable right) {
        if (this == IS_NULL) {
            return null == left && null == right;
        }

        if (null == left || null == right) {
            return false;
        }

        int result = left.compareTo(right);

        if (result == 0) {
            return this == EQUALS || this == BIGGER_EQUALS || this == LESS_EQUALS;
        } else if (result < 0) {
            return this == LESS || this == LESS_EQUALS || this == NOT_EQUALS;
        } else {
            return this == BIGGER || this == BIGGER_EQUALS || this == NOT_EQUALS;
        }
    }


    public static ConstraintEnum getConstraintEnumBySymbol(String symbol) {
        for (ConstraintEnum constraintEnum : values()) {

            if (constraintEnum.symbol.equals(symbol)) {
                return constraintEnum;
            }
        }

        return null;
    }
}
