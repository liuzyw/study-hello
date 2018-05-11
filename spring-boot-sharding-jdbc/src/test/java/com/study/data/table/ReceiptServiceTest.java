package com.study.data.table;

import com.study.data.table.entity.Receipt;
import com.study.data.table.service.ReceiptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created on 2018-05-12
 *
 * @author liuzhaoyuan
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReceiptServiceTest {

    @Autowired
    private ReceiptService receiptService;


    @Test
    public void getReceiptByUserIdReceiptId() {
        System.out.println(receiptService.getReceiptByUserIdReceiptId(119,111L));
    }


    @Test
    public void saveReceipt() {
        Receipt receipt = new Receipt();

        receipt.setUserId(119);
        receipt.setReceiptId(109L);
        receipt.setCoupon("66666666");

        receiptService.saveReceipt(receipt);
//        receiptService.insertReceipt(118,110L,"343434");

        System.out.println("------------ " + receipt.getId());
    }

}
