package com.study.util.tx;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created on 2018-07-31
 *
 * @author liuzhaoyuan
 */
public class TransactionManager {

    private List<TransactionDefinition> list = new ArrayList<>();

    private TransactionStatus status = TransactionStatus.NOT_BEGIN;

    private int index;

    private String operationId;


    public TransactionManager() {
        init();
    }

    private void init() {
        operationId = UUID.randomUUID().toString();
        setStatus(TransactionStatus.NOT_BEGIN);
    }


    public void addOperation(TransactionDefinition transactionDefinition) {
        transactionDefinition.setOperationId(operationId);
        list.add(transactionDefinition);
    }


    public void doInTransaction() {
        try {
            setStatus(TransactionStatus.PROGRESS_ING);
            for (int i = 0; i < list.size(); i++) {
                TransactionDefinition transactionDefinition = list.get(i);
                Operation operation = transactionDefinition.getOperation();
                transactionDefinition.setOperationStatus(OperationStatus.PROGRESS_ING);

                operation.execute();
                if (!operation.isContinue()) {
                    transactionDefinition.setOperationStatus(OperationStatus.FAIL);
                    throw new RuntimeException("transaction is break by " + transactionDefinition.getOperationName());
                }
                index++;
                transactionDefinition.setOperationStatus(OperationStatus.SUCCESS);
            }
            setStatus(TransactionStatus.SUCCESS);
        } catch (Exception e) {
            setStatus(TransactionStatus.FAIL);
            doRollBack();
        }
    }

    public void doRollBack() {
        int currFail = index;
        try {
            setStatus(TransactionStatus.ROLLBACK_ING);
            setOperationStatus();
            for (int i = index; i >= 0; i--) {
                currFail = i;
                TransactionDefinition transactionDefinition = list.get(i);
                Operation operation = transactionDefinition.getOperation();
                transactionDefinition.setOperationStatus(OperationStatus.ROLLBACK_ING);
                boolean re = operation.rollback();
                if (!re) {
                    transactionDefinition.setOperationStatus(OperationStatus.ROLLBACK_FAIL);
                    throw new RuntimeException("transaction rollback fail " + transactionDefinition.getOperationName());
                }
                transactionDefinition.setOperationStatus(OperationStatus.ROLLBACK_SUCCESS);
            }
            setStatus(TransactionStatus.ROLLBACK_SUCCESS);
        } catch (Exception e) {
            list.get(currFail).setOperationStatus(OperationStatus.ROLLBACK_FAIL);
            setStatus(TransactionStatus.ROLLBACK_FAIL);
        }
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public int getIndex() {
        return index;
    }

    public int getTransactionSize() {
        return list.size();
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public List<TransactionDefinition> getList() {
        return list;
    }

    private void setOperationStatus() {
        for (int i = 0; i <= index; i++) {
            TransactionDefinition transactionDefinition = list.get(i);
            transactionDefinition.setOperationStatus(OperationStatus.WAIT_ROLLBACK);
        }
    }
}
