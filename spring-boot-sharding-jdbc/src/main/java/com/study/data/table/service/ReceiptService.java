package com.study.data.table.service;

import com.study.data.table.entity.Receipt;
import java.util.List;

/**
 * Created on 2018-05-11
 *
 * @author liuzhaoyuan
 */
public interface ReceiptService {

    Receipt getReceiptByUserIdReceiptId(Integer userId, Long receiptId);

    List<Receipt> getReceiptsByUserIdReceiptIds(Integer userId, List<Long> receiptId);


    void saveReceipt(Receipt receipt);

    int insertReceipt(Integer userId, Long receiptId, String coupon);


}
