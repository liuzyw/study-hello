package com.study.util.tx;

/**
 * Created on 2018-07-31
 *
 * <p>事物状态</>
 *
 * @author liuzhaoyuan
 */
public enum TransactionStatus {

    NOT_BEGIN(0, "事物未开始"),

    PROGRESS_ING(1, "事物进行中"),

    FAIL(2, "事物失败"),

    SUCCESS(3, "事物成功"),

    ROLLBACK_ING(4, "事物回滚中"),

    ROLLBACK_FAIL(5, "事物回滚失败"),

    ROLLBACK_SUCCESS(6, "事物回滚成功");

    private int code;

    private String value;

    TransactionStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getCode() {
        return code;
    }
}
