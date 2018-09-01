package com.study.spring.plugin;

import lombok.Data;

/**
 * Created on 2018-05-24
 *
 * @author liuzhaoyuan
 */
@Data
public class Plugin {

    private String id;

    private String name;

    private String className;

    private String jarRemoteUrl;

    private String jarFile;

    private Boolean active;

    private String version;

    private byte[] content;


}
