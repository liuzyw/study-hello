package com.study.data.table.mapper.receipt;

import com.study.data.table.entity.Receipt;
import java.util.List;
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

    List<Receipt> getReceiptsByUserIdReceiptIds(@Param("userId") Integer userId, @Param("receiptIds") List<Long> receiptIds);

    void saveReceipt(Receipt receipt);

    int insertReceipt(@Param("userId") Integer userId, @Param("receiptId") Long receiptId, @Param("coupon") String coupon);


}
