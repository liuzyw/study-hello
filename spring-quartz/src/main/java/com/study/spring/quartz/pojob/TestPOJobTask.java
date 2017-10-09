package com.study.spring.quartz.pojob;

import com.study.util.date.DateUtils;
import java.util.Date;

public class TestPOJobTask {


    public void testPoMet() {
        System.out.println(" Hello Quartz POJO job --- " + DateUtils.formatDateBySecond(new Date()));

    }

} 