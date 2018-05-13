package com.study.spring.weather;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

/**
 * Created on 2018-05-13
 *
 * @author liuzhaoyuan
 */
public class WeatherDetail implements Serializable {

    private static final long serialVersionUID = 2122233439719242880L;

    /**
     * 时间
     */
    private String date;

    /**
     * 高温
     */
    private String high;

    /**
     * 低温
     */
    private String low;

    /**
     * 状况
     */
    private String type;

    /**
     * <![CDATA[<3级]]>
     * 风力
     */
    private String fl;
    private String fengli;

    /**
     * 风向
     */
    private String fx;

    private String fengxiang;

    public WeatherDetail() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFl() {
        if (StringUtils.isEmpty(fl)) {
            return "";
        }
        return fl.substring(9, fl.length() - 3);
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getFengli() {
        if (StringUtils.isEmpty(fengli)) {
            return "";
        }
        return fengli.substring(9, fengli.length() - 3);
    }

    public void setFengli(String fengli) {
        this.fengli = fengli;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getFengxiang() {
        return fengxiang;
    }

    public void setFengxiang(String fengxiang) {
        this.fengxiang = fengxiang;
    }

    @Override
    public String toString() {
        return "WeatherDetail{" +
            "date='" + date + '\'' +
            ", high='" + high + '\'' +
            ", low='" + low + '\'' +
            ", type='" + type + '\'' +
            ", fl='" + getFl() + '\'' +
            ", fengli='" + getFengli() + '\'' +
            ", fx='" + fx + '\'' +
            ", fengxiang='" + fengxiang + '\'' +
            '}';
    }
}
