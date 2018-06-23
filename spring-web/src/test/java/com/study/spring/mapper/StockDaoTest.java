package com.study.spring.mapper;

import com.study.spring.entity.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created on 2018-06-23
 *
 * @author liuzhaoyuan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:spring/applicationContext.xml"})
public class StockDaoTest {

    @Autowired
    private StockDao stockDao;


    @Test
    public void getStockById() {
        System.out.println(stockDao.getStockById(1L));
    }

    @Test
    public void saveStock() {

        Stock stock = new Stock();
        stock.setCount(6);
        stock.setProductId(103L);

        stockDao.saveStock(stock);

        System.out.println("id: " + stock.getId());


    }

    @Test
    public void updateStockByProductId() {
        System.out.println(stockDao.updateStockByProductId(100L,10,9));
    }

    @Test
    public void getStockByProductId() {
        System.out.println(stockDao.getStockByProductId(100L));
    }


}
