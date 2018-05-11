package com.study.database.table.service;

import com.study.database.table.BaseTest;
import com.study.database.table.entity.Receipt;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 2018-05-11
 *
 * @author liuzhaoyuan
 */
public class ReceiptServiceTest extends BaseTest {

    @Autowired
    private ReceiptService receiptService;


    @Test
    public void getReceiptByUserIdReceiptId() {
        System.out.println(receiptService.getReceiptByUserIdReceiptId(102,101L));
    }


    @Test
    public void saveReceipt() {
        Receipt receipt = new Receipt();

        receipt.setUserId(112);
        receipt.setReceiptId(111L);
        receipt.setCoupon("66666666");

//        receiptService.saveReceipt(receipt);
        receiptService.insertReceipt(117,111L,"343434");

        System.out.println("------------ " + receipt.getId());
    }

}
