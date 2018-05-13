package com.study.spring.weather;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * Created on 2018-05-13
 *
 * @author liuzhaoyuan
 */
@Data
public class WatherData implements Serializable {

    private static final long serialVersionUID = 1702105318291753770L;

    private WeatherDetail yesterday;

    private String city;

    private String api;

    private List<WeatherDetail> forecast;

    private String ganmao;

    private String wendu;

}
