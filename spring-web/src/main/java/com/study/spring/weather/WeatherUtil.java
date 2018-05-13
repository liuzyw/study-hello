package com.study.spring.weather;


import com.alibaba.fastjson.JSON;
import com.study.spring.vo.WeatherVO;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created on 2018-05-13
 *
 * <p>解析第三方的数据</p>
 *
 * @author liuzhaoyuan
 */
public class WeatherUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherUtil.class);

    private static final String WEATHER_SERVICE_URL = "http://wthrcdn.etouch.cn/weather_mini?citykey=";


    public static void main(String[] args) throws Exception {
        HttpGet httpGet = new HttpGet("http://www.weather.com.cn/data/zs/101010100.html");

        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpResponse httpResponse = closeableHttpClient.execute(httpGet);

        httpResponse.getEntity().getContent();
        String json = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

        System.out.println(json);
    }

    /**
     * 获取最近 5天 城市天气情况
     *
     * @return
     */
    public static WeatherVO getWeathers(String cityCode) {
        try {
            HttpGet httpGet = new HttpGet(WEATHER_SERVICE_URL + cityCode);

            CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
            HttpResponse httpResponse = closeableHttpClient.execute(httpGet);

            httpResponse.getEntity().getContent();
            String json = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            WeatherVO weatherVO = JSON.parseObject(json, WeatherVO.class);
            LOGGER.info("find weather cityId:{}, weather:{} ", cityCode, weatherVO);

            return weatherVO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
