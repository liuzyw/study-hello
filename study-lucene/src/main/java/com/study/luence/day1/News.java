package com.study.luence.day1;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created on 2019-02-20
 *
 * @author liuzhaoyuan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class News implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer id;
    private String title;

    private String content;

    private int reply;


}
