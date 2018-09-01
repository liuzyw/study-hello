package com.study.spring.controller;

import com.study.spring.plugin.Plugin;
import com.study.spring.vo.Result;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Created on 2018-08-25
 *
 * @author liuzhaoyuan
 */
@Controller
public class PluginController {


    private static final Logger LOGGER = LoggerFactory.getLogger(PluginController.class);


    @RequestMapping("/goPlugin")
    public String toPage() {
        return "plugin/plugin";
    }

    @RequestMapping(value = "/plugin/add", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> add(Plugin plugin, HttpServletRequest request) throws IOException {
        // 处理文件
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            // 获取上传的文件
            Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
            Map.Entry<String, MultipartFile> fileEntry = fileMap.entrySet().stream()
                .filter(entry -> "jarFile".equalsIgnoreCase(entry.getKey()))
                .findAny().orElse(null);

            if (fileEntry != null) {
                MultipartFile multipartFile = fileEntry.getValue();
                plugin.setJarFile(multipartFile.getOriginalFilename());
                plugin.setContent(multipartFile.getBytes());
            }
        }
        // 保存
//        Plugin save = service.save(plugin);
        return new Result("添加成功！", 200);
    }

}
