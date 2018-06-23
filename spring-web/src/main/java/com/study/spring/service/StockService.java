package com.study.spring.service;

import com.study.spring.entity.Stock;

/**
 * Created on 2018-06-23
 * <p>
 * <p>库存操作</>
 *
 * @author liuzhaoyuan
 */
public interface StockService {

    Stock getStockById(Long id);

    int saveStock(Stock book);

    int updateStockByProductId(Long productId, int fromCount, int toCount);

    Stock getStockByProductId(Long productId);
}
