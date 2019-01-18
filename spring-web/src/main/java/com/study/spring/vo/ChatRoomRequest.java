package com.study.spring.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * Created on 2019-01-18
 *
 * @author liuzhaoyuan
 */
@Data
public class ChatRoomRequest implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String fromName;

    private String chatValue;

    private String toUserId;


}
