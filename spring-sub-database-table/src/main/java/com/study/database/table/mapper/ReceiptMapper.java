package com.study.database.table.mapper;

import com.study.database.table.entity.Receipt;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created on 2018-05-11
 *
 * @author liuzhaoyuan
 */
@Repository
public interface ReceiptMapper {

    Receipt getReceiptByUserIdReceiptId(@Param("userId") Integer userId, @Param("receiptId") Long receiptId);

    void saveReceipt(Receipt receipt);

    int insertReceipt(@Param("userId") Integer userId, @Param("receiptId") Long receiptId, @Param("coupon") String coupon);


}
