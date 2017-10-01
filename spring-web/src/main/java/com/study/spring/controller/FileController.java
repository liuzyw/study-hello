package com.study.spring.controller;

import com.study.spring.dto.FileImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created on 2017-10-01
 *
 * @author liuzhaoyuan
 */
@Controller
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping("/fileUpload")
    public String fileUpload(@RequestParam("uploadFile") MultipartFile multipartFile, HttpServletRequest request) {
        System.out.println("--------  start fileUpload  ----------");
        if (!multipartFile.isEmpty()) {
            int pre = (int) System.currentTimeMillis();
            //重命名上传的文件
            File newFile = new File(
                "/Users/liuzhaoyuan/gitwork/study-hello/spring-web/upload", multipartFile.getOriginalFilename());
//            File newFile = new File(request.getSession().getServletContext().getRealPath("upload"),multipartFile.getOriginalFilename());
            System.out.println("upLoadFile newName: " + newFile.getName());
            try {
                multipartFile.transferTo(newFile);
                logger.info("upload file to upload dir, file name: " + newFile.getAbsolutePath());
            } catch (IOException e) {
                logger.error("uploadFile fail file name: " + multipartFile.getOriginalFilename() + ", error: " + e);
                return "error";
            }

            int finalTime = (int) System.currentTimeMillis();
            System.out.println("uploadFile use time: " + (finalTime - pre));

        }

        return "success";
    }

    @RequestMapping("/fileUploadList")
    public String fileUploadList(@ModelAttribute FileImage fileImage, HttpServletRequest request, Model model) {
        System.out.println("--------  start fileUpload  ----------");
        List<MultipartFile> files = fileImage.getImages();
        if (CollectionUtils.isNotEmpty(files)) {
            for (MultipartFile file : files) {
                int begin = (int) System.currentTimeMillis();
                //重命名上传的文件
                File newFile1 = new File(
                    "/Users/liuzhaoyuan/gitwork/study-hello/spring-web/upload", file.getOriginalFilename());
                try {
                    file.transferTo(newFile1);
                    logger.info("upload file to upload dir, file name: " + newFile1.getAbsolutePath());
                } catch (IOException e) {
                    logger.error("uploadFile fail file name: " + file.getOriginalFilename() + ", error: " + e);
                    return "error";
                }
                int end = (int) System.currentTimeMillis();
                System.out.println("uploadFile use time: " + (end - begin));
            }

        }
        model.addAttribute("fileImage",fileImage);
        return "fileImage";
    }

}
