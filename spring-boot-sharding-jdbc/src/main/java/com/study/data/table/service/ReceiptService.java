package com.study.data.table.service;

import com.study.data.table.entity.Receipt;

/**
 * Created on 2018-05-11
 *
 * @author liuzhaoyuan
 */
public interface ReceiptService {

    Receipt getReceiptByUserIdReceiptId(Integer userId, Long receiptId);

    void saveReceipt(Receipt receipt);

    int insertReceipt(Integer userId, Long receiptId, String coupon);


}
