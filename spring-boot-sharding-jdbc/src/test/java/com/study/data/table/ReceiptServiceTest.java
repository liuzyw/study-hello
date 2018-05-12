package com.study.data.table;

import com.study.data.table.entity.Receipt;
import com.study.data.table.service.ReceiptService;
import java.util.ArrayList;
import java.util.List;
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
        System.out.println(receiptService.getReceiptByUserIdReceiptId(103, 108L));

        // 脏数据
        System.out.println(receiptService.getReceiptByUserIdReceiptId(112, 111L));

    }

    @Test
    public void getReceiptsByUserIdReceiptIds() {

        List<Long> receiptIds = new ArrayList<>();
        receiptIds.add(100L);
        receiptIds.add(102L);
        receiptIds.add(104L);

        receiptIds.add(103L);
        receiptIds.add(105L);

        System.out.println(receiptService.getReceiptsByUserIdReceiptIds(100, receiptIds));

        System.out.println("============");

    }


    @Test
    public void saveReceipt() {
        Receipt receipt = new Receipt();

        receipt.setUserId(121);
        receipt.setReceiptId(121L);
        receipt.setCoupon("66666664446");

        receiptService.saveReceipt(receipt);
        receiptService.insertReceipt(112, 112L, "3434444434");

        System.out.println("------------ " + receipt.getId());
    }

}
