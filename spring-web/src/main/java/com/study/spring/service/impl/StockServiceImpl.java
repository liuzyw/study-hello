package com.study.spring.service.impl;

import com.study.spring.entity.Stock;
import com.study.spring.mapper.StockDao;
import com.study.spring.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018-06-23
 *
 * @author liuzhaoyuan
 */
@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockDao stockDao;

    @Override
    public Stock getStockById(Long id) {
        return stockDao.getStockById(id);
    }

    @Override
    public int saveStock(Stock book) {
        return stockDao.saveStock(book);
    }

    @Override
    public int updateStockByProductId(Long productId, int fromCount, int toCount) {
        return stockDao.updateStockByProductId(productId, fromCount, toCount);
    }

    @Override
    public Stock getStockByProductId(Long productId) {
        return stockDao.getStockByProductId(productId);
    }

    @Override
    public int updateReduceStockByProductId(Long productId, int reduceCount) {
        return stockDao.updateReduceStockByProductId(productId, reduceCount);
    }
}
