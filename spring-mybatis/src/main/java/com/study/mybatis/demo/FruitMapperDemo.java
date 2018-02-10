package com.study.mybatis.demo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.mybatis.entity.Fruit;
import com.study.mybatis.mapper.FruitMapper;
import com.study.mybatis.util.MyUtils;
import com.study.mybatis.util.SqlSessionFactoryUtil;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by liuzhaoyuan on 2017/7/22.
 */
public class FruitMapperDemo {

    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = null;

        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            FruitMapper fruitMapper = sqlSession.getMapper(FruitMapper.class);
            Fruit fruit = new Fruit();
            fruit.setName(MyUtils.getRandomString(4));
            fruit.setPrice(new BigDecimal("9.0"));
            fruit.setWeight(4.0);

            int c = fruitMapper.saveFruit(fruit);
            System.out.println("----------  " + fruit.getId());
            System.out.println("----------  " + fruitMapper.getFruitById(34).getName());
            System.out.println(new String(fruitMapper.getFruitById(34).getName().getBytes("utf8"), "utf8"));
            System.out.println(fruitMapper.findFruitsByPrice(new BigDecimal("3.0")));

            System.out.println("list all: " + fruitMapper.listFruitByPage().size());

            // 分页查询
            Page page = PageHelper.startPage(3, 5);
            List<Fruit> list = fruitMapper.listFruitByPage();
            System.out.println("page: " + page);
            System.out.println("list by page " + list);
            for (Fruit fruit1 : list) {
                System.out.println(fruit1.getName());
            }
            PageInfo<Fruit> pageInfo = new PageInfo<>(list);
            System.out.println("pageInfo: " + pageInfo);
            System.out.println(pageInfo.getList());

            System.out.println("------------- done -----------");

            sqlSession.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            sqlSession.rollback();


        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
