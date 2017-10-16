package com.study.util.date;

import com.study.util.tool.IdcardUtil;
import java.util.Date;
import java.util.Locale;
import org.joda.time.DateTime;
import org.joda.time.DateTime.Property;
import org.junit.Test;

/**
 * Created on 2017-10-08
 *
 * @author liuzhaoyuan
 */
public class DateUtilsTest {


    @Test
    public void testDate(){
        String e = DateTime.now().minusYears(1).dayOfMonth().getAsText(Locale.CHINESE);
        System.out.println("上周的周一：" + e);

        System.out.println(DateUtils.parse("000229","yyMMdd"));

        System.out.println(DateUtils.getDay(new Date()));

        System.out.println(IdcardUtil.isvalidCard18("321088199210315942"));
    }

}
