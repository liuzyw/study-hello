package com.study.spring.boot.web.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Created on 2018-01-21
 *
 * @author liuzhaoyuan
 */
@Controller
public class FileUploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);
    private static final String path = "/Users/liuzhaoyuan/gitwork/study-hello/spring-boot-web/logs/";

    @RequestMapping("/toFile")
    public String toFile() {
        return "file";
    }

    /**
     * 实现文件上传
     */
    @RequestMapping("/fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("fileName") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return "fail";
        }
        String fileName = file.getOriginalFilename();
        long size = file.getSize();
        LOGGER.info(fileName + " --> " + size);

        File dest = new File(path + fileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return "success";
        } catch (IOException e) {
            LOGGER.error("upload file fail, ", e);
            return "fail";
        }
    }


    @RequestMapping("/toMultifile")
    public String multifile() {
        return "multifile";
    }

    /**
     * 实现多文件上传
     */
    @RequestMapping(value = "multifileUpload", method = RequestMethod.POST)
    @ResponseBody
    public String multifileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileName");
        if (files == null || files.isEmpty()) {
            return "fail";
        }

        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) {
                return "fail";
            } else {
                String fileName = file.getOriginalFilename();
                long size = file.getSize();
                LOGGER.info(fileName + " --> " + size);
                File dest = new File(path + fileName);
                if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                } catch (Exception e) {
                    LOGGER.error("upload file fail, ", e);
                    return "fail";
                }
            }
        }
        return "success";
    }

    @RequestMapping("/download")
    public void downLoad(HttpServletResponse response) {
        String filename = "info.log";
        File file = new File(path + filename);
        if (file.exists()) { //判断文件父目录是否存在
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }
                LOGGER.info("----------file download" + filename);

            } catch (Exception e) {
                LOGGER.error("file download fail, ", e);
            } finally {
                try {
                    bis.close();
                    fis.close();
                } catch (IOException e1) {
                    LOGGER.error("close stream fail, ", e1);
                }
            }
        }
    }
}
