package com.study.spring.mapper;

import com.study.spring.entity.Stock;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by liuzhaoyuan on 2017/7/23.
 */
@Repository
public interface StockDao {

    Stock getStockById(Long id);

    int saveStock(Stock book);

    int updateStockByProductId(@Param("productId") Long productId, @Param("fromCount") int fromCount, @Param("toCount") int toCount);

    Stock getStockByProductId(Long productId);

}
