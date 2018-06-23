package com.study.spring.plugin;

import java.util.List;

/**
 * Created on 2018-05-24
 *
 * @author liuzhaoyuan
 */
public interface PluginFactory {

    void activePlugin(String pluginId);

    void disablePlugin(String pluginId);

    void installPlugin(Plugin plugin, Boolean active);

    void unInstallPlugin(String pluginId);

    List<Plugin> getPlugins();

    String getPluginStatus(String pluginId);


}
