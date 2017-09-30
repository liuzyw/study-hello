package com.study.spring.mapper;

import com.study.spring.po.Fruit;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * Created by liuzhaoyuan on 2017/7/23.
 */
@Repository
public interface FruitDao {

  Fruit getFruitById(Integer id);

  Integer saveFruit(Fruit fruit);

  Integer deleteFruit(Integer id);

  List<Fruit> findFruitsByPrice(BigDecimal price);

}
