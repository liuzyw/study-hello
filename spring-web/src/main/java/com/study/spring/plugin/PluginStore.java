package com.study.spring.plugin;

import java.util.List;
import lombok.Data;

/**
 * Created on 2018-05-24
 *
 * @author liuzhaoyuan
 */
@Data
public class PluginStore {

    private long lastModify;

    private List<Plugin> plugins;
}
