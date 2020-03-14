package com.study.spring.state.action;

import com.study.spring.base.ToString;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2020-03-14
 *
 * @author liuzhaoyuan
 */
public class ActionResult extends ToString {


    private boolean success = false;

    private String errorCode;

    private String errorMsg;


    /**
     * 失败的 节点
     */
    private String failedItemCode;


    /**
     * 是否幂等
     */
    private boolean idempotent = false;

    private Map<String, Object> extInfo = new HashMap<>();


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getFailedItemCode() {
        return failedItemCode;
    }

    public void setFailedItemCode(String failedItemCode) {
        this.failedItemCode = failedItemCode;
    }

    public boolean isIdempotent() {
        return idempotent;
    }

    public void setIdempotent(boolean idempotent) {
        this.idempotent = idempotent;
    }


    public Map<String, Object> getExtInfo() {
        return extInfo;
    }

    public void put(String key, Object value) {
        extInfo.put(key, value);
    }

    public void handleFailure(ActionItemDefinition actionItemDefinition, Throwable throwable) {
        this.success = false;
        this.failedItemCode = actionItemDefinition.getCode();
        if (throwable != null) {
            setErrorCode("ERROR");
            setErrorMsg("ERROR");
        }

    }
}
