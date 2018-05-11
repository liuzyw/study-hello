package com.study.database.table.service;

import com.study.database.table.entity.Receipt;
import com.study.database.table.mapper.ReceiptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018-05-11
 *
 * @author liuzhaoyuan
 */
@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptMapper receiptMapper;

    @Override
    public Receipt getReceiptByUserIdReceiptId(Integer userId, Long receiptId) {
        return receiptMapper.getReceiptByUserIdReceiptId(userId, receiptId);
    }

    @Override
    public void saveReceipt(Receipt receipt) {
        receiptMapper.saveReceipt(receipt);
    }

    @Override
    public int insertReceipt(Integer userId, Long receiptId, String coupon) {
        return receiptMapper.insertReceipt(userId, receiptId, coupon);
    }
}
