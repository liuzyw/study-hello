package com.study.util.thread;

/**
 * Created on 2018-11-25
 *
 * @author liuzhaoyuan
 */
public enum DelayEnum {

    DEFAULT_TASK(1, 1000L, 5, "默认任务");


    /**
     * 任务类型
     */
    public int type;

    /**
     * 运行间隔时间
     */
    public long time;

    /**
     * 最大运行次数
     */
    public int count;

    /**
     * 任务描述
     */
    public String desc;

    DelayEnum(int type, long time, int count, String desc) {
        this.type = type;
        this.time = time;
        this.count = count;
        this.desc = desc;
    }

}
