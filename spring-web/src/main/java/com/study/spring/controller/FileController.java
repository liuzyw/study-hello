package com.study.spring.controller;

import com.study.spring.dto.FileImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public String fileUpload(@RequestParam("uploadFile") MultipartFile multipartFile, HttpServletRequest request) {
        System.out.println("--------  start fileUpload  ----------");
        if (!multipartFile.isEmpty()) {
            int pre = (int) System.currentTimeMillis();
            //重命名上传的文件
//            File newFile = new File(
//                "/Users/liuzhaoyuan/gitwork/study-hello/spring-web/upload", multipartFile.getOriginalFilename());
            File newFile = new File(request.getServletContext().getRealPath("/WEB-INF/upload"),
                multipartFile.getOriginalFilename());

            System.out.println("upLoadFile newName: " + newFile.getAbsolutePath());
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
    public String fileUploadList(FileImage fileImage, HttpServletRequest request, Model model) {
        System.out.println("--------  start fileUpload  ----------");
        List<MultipartFile> files = fileImage.getImages();
        if (CollectionUtils.isNotEmpty(files)) {
            for (MultipartFile file : files) {
                int begin = (int) System.currentTimeMillis();
                //重命名上传的文件
//                File newFile1 = new File(
//                    "/Users/liuzhaoyuan/gitwork/study-hello/spring-web/upload", file.getOriginalFilename());
                File newFile1 = new File(request.getServletContext().getRealPath("/WEB-INF/upload"),
                    file.getOriginalFilename());

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
        model.addAttribute("fileImage", fileImage);
        return "fileImage";
    }

    @RequestMapping("/fileDownload")
    public String fileDownload(HttpServletRequest request, HttpServletResponse response) {
        File file = new File(request.getServletContext().getRealPath("/WEB-INF/upload"),
            "testDownload.pdf");

        if (file.exists()) {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename=testDownload.pdf");
            byte[] bytes = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();

                int len = bis.read(bytes);
                while (len != -1) {
                    os.write(bytes, 0, len);
                    len = bis.read(bytes);
                }
            } catch (IOException e) {
                logger.error("down file error:", e);
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e1) {
                        logger.error("close bis fail,", e1);
                    }
                }

                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e2) {
                        logger.error("close fis fail," + e2);
                    }
                }
            }
        }

        return null;
    }
}
