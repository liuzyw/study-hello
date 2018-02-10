package com.study.mybatis.mapper;

import com.study.mybatis.entity.Fruit;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by liuzhaoyuan on 2017/7/23.
 */
public interface FruitMapper {

    Fruit getFruitById(Integer id);

    Integer saveFruit(Fruit fruit);

    Integer deleteFruit(Integer id);

    List<Fruit> findFruitsByPrice(BigDecimal price);

    List<Fruit> listFruitByPage();

}
