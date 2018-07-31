package com.study.util.tx;

/**
 * Created on 2018-07-31
 *
 * @author liuzhaoyuan
 */
public class TransactionDefinition<T extends Operation<T>> {

    private String operationName;

    private String operationId;

    private Operation<T> operation;

    private boolean isContinue = false;

    private OperationStatus operationStatus;


    public TransactionDefinition(Operation<T> operation) {
        this.operation = operation;
        this.operationName = operation.getClass().getName();
        this.operationStatus = OperationStatus.NOT_BEGIN;
    }

    public TransactionDefinition(String operationName, Operation<T> operation) {
        this.operationName = operationName;
        this.operation = operation;
        this.operationStatus = OperationStatus.NOT_BEGIN;
    }

    public Operation<T> getOperation() {
        return operation;
    }

    public void setOperation(Operation<T> operation) {
        this.operation = operation;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public boolean isContinue() {
        return isContinue;
    }

    public void setContinue(boolean aContinue) {
        isContinue = aContinue;
    }

    public OperationStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }
}
