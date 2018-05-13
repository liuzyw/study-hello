package com.study.spring.vo;

import com.study.spring.weather.WatherData;
import java.io.Serializable;
import lombok.Data;

/**
 * Created on 2018-05-13
 *
 * @author liuzhaoyuan
 */
@Data
public class WeatherVO implements Serializable {

    private static final long serialVersionUID = -7141065579613169646L;

    private WatherData data;

    private String desc;

    private Integer status;



}
