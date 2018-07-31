package com.study.util.tx.demo;

import com.study.util.tx.AbstractOperation;
import com.study.util.tx.TransactionDefinition;
import com.study.util.tx.TransactionManager;

/**
 * Created on 2018-07-31
 *
 * @author liuzhaoyuan
 */
public class Demo {


    public static void main(String[] args) {

        TransactionManager transactionManager = new TransactionManager();

        transactionManager.addOperation(new TransactionDefinition("DB1", new DB1()));
        transactionManager.addOperation(new TransactionDefinition("DB2", new DB2()));
        transactionManager.addOperation(new TransactionDefinition("DB3", new DB3()));
        transactionManager.addOperation(new TransactionDefinition("DB4", new DB4()));


        System.out.println(transactionManager.getStatus());
        System.out.println("-----  " + transactionManager.getOperationId());
        System.out.println();

        transactionManager.doInTransaction();

        System.out.println();
        System.out.println(transactionManager.getStatus());

        System.out.println();

        for (TransactionDefinition definition : transactionManager.getList()) {
            System.out.println(definition.getOperationName() + " : " + definition.getOperationStatus());
        }

    }

}

class DB1 extends AbstractOperation<String> {

    @Override
    public String execute() {
        System.out.println("DB1 execute 。。。 ");

        return null;
    }

    @Override
    public boolean rollback() {
        System.out.println("DB1 rollback 。。。 ");

        return true;
    }
}

class DB2 extends AbstractOperation<String> {

    @Override
    public String execute() {
        System.out.println("DB2 execute 。。。 ");

        return null;
    }

    @Override
    public boolean rollback() {
        System.out.println("DB2 rollback 。。。 ");
        int a = 10 / 0;

        return true;
    }
}

class DB3 extends AbstractOperation<String> {

    @Override
    public String execute() {
        System.out.println("DB3 execute 。。。 ");
        int a = 10 / 0;

        return null;
    }

    @Override
    public boolean rollback() {
        System.out.println("DB3 rollback 。。。 ");
        return true;
    }
}

class DB4 extends AbstractOperation<String> {

    @Override
    public String execute() {
        System.out.println("DB4 execute 。。。 ");

        return null;
    }

    @Override
    public boolean rollback() {
        System.out.println("DB4 rollback 。。。 ");

        return true;
    }
}
