package com.study.dao.impl;

import com.study.dao.BizSceneDao;
import com.study.model.BizScene;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class BizSceneDaoImpl implements BizSceneDao {

    public static Map<String, BizScene> bizSceneMap = new HashMap<>();

    static {
        bizSceneMap.put("COUPON", new BizScene(1L, "COUPON"));
    }


    @Override
    public BizScene getBizScene(String bizScene) {
        return bizSceneMap.get(bizScene);
    }
}
