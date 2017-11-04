package com.study.spring.service.impl;

import com.study.spring.mapper.FruitDao;
import com.study.spring.po.Fruit;
import com.study.spring.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2017-08-17 21:46
 *
 * @author liuzhaoyuan
 */
@Service
public class FruitServiceImpl implements FruitService {

    @Autowired
    private FruitDao fruitDao;

    @Override
    public Fruit getFruitById(Integer id){
        return fruitDao.getFruitById(id);
    }

}
