package com.study.spring.controller;

import com.study.spring.util.PropertyUtil;
import com.study.spring.vo.WeatherVO;
import com.study.spring.weather.WeatherUtil;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2018-05-13
 *
 * @author liuzhaoyuan
 */
@Controller
public class WeatherController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/goWeather", method = RequestMethod.GET)
    public String toWeatherPage() {
        return "weather/weather";
    }


    @RequestMapping(value = "/getWeatherByCityCode/{cityCode}", method = RequestMethod.GET)
    @ResponseBody
    public WeatherVO getWeather(@PathVariable("cityCode") String cityCode) {
        return WeatherUtil.getWeathers(cityCode);
    }

    @RequestMapping(value = "/getWeatherByCityName", method = RequestMethod.GET)
    @ResponseBody
    public WeatherVO getWeatherByCityName() throws Exception {
        String name = request.getParameter("name");
        LOGGER.info("get weather cityName:{}", new String(name.getBytes("iso8859-1"), "UTF-8"));
        name = new String(name.getBytes("iso8859-1"), "UTF-8");
        String cityCode = PropertyUtil.getCityCodeByName(name);
        LOGGER.info("get weather cityCode:{}", cityCode);
        if (StringUtils.isEmpty(cityCode)) {
            return WeatherUtil.getWeathers("101020100");
        }
        return WeatherUtil.getWeathers(cityCode);
    }

}
