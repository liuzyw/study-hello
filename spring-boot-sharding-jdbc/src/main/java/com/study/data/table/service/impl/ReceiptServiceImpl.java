package com.study.data.table.service.impl;

import com.study.data.table.entity.Receipt;
import com.study.data.table.mapper.receipt.ReceiptMapper;
import com.study.data.table.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created on 2018-05-11
 *
 * @author liuzhaoyuan
 */
@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptMapper receiptMapper;

    @Transactional("mybatisTransactionManager")
    @Override
    public Receipt getReceiptByUserIdReceiptId(Integer userId, Long receiptId) {
        return receiptMapper.getReceiptByUserIdReceiptId(userId, receiptId);
    }

    @Transactional("mybatisTransactionManager")
    @Override
    public void saveReceipt(Receipt receipt) {
        receiptMapper.saveReceipt(receipt);
    }

    @Transactional("mybatisTransactionManager")
    @Override
    public int insertReceipt(Integer userId, Long receiptId, String coupon) {
        return receiptMapper.insertReceipt(userId, receiptId, coupon);
    }
}
