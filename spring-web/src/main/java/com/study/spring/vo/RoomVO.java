package com.study.spring.vo;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * Created on 2018-05-05
 *
 * @author liuzhaoyuan
 */
@Data
public class RoomVO implements Serializable {

    private static final long serialVersionUID = -8257762467247385194L;

    private Integer id;
    private String name;

    /**
     * 空闲时间段
     */
    private List<RoomPair> list;

}
