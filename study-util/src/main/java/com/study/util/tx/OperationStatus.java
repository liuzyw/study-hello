package com.study.util.tx;

/**
 * Created on 2018-07-31
 *
 * @author liuzhaoyuan
 */
public enum OperationStatus {

    NOT_BEGIN(0, "操作未开始"),

    PROGRESS_ING(1, "操作进行中"),

    FAIL(2, "操作失败"),

    SUCCESS(3, "操作成功"),

    ROLLBACK_ING(4, "操作回滚中"),

    ROLLBACK_FAIL(5, "操作回滚失败"),

    ROLLBACK_SUCCESS(6, "操作回滚成功"),

    WAIT_ROLLBACK(7, "操作待回滚");

    private int code;

    private String value;

    OperationStatus(int code, String value) {
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
