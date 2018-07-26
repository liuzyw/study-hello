package com.study.spring.controller;

import com.study.spring.dto.FileImage;
import com.study.util.excel.ExcelXlsxUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletOutputStream;
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
    public String fileUpload(@RequestParam("uploadFiles") MultipartFile[] multipartFiles, HttpServletRequest request) {
        System.out.println("--------  start fileUpload  ----------");
        for (MultipartFile multipartFile : multipartFiles) {
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
            "MyBatis从入门到精通.pdf");

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
                os.flush();
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

    @RequestMapping(value = "/downExcel")
    public void downExcel(HttpServletRequest request, HttpServletResponse response) {

        List<String> title = new ArrayList<>();
        title.add("name");
        title.add("age");
        List<List<Object>> list = new ArrayList<>();
        List<Object> sub = new ArrayList<>();
        sub.add("Tom");
        sub.add("22");
        list.add(sub);

        List<Object> sub2 = new ArrayList<>();
        sub2.add("张三");
        sub2.add("34");
        list.add(sub2);

        try {
            String filename = "aaa张三.xlsx";
//            String filename = "bbb张三.xls";
            response.setHeader("Content-disposition", filename);
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
            response.setHeader("Pragma", "No-cache");
            response.setCharacterEncoding("UTF-8");

            OutputStream ouputStream = response.getOutputStream();
            ExcelXlsxUtils.writeToStream(ouputStream, title, list);
//            ExcelXlsUtils.writeToStream(ouputStream, title, list);
        } catch (IOException e) {
            logger.error("down excel fail", e);
        }

    }

    @RequestMapping(value = "/downBigFile")
    public void downBigFile(HttpServletRequest request, HttpServletResponse response) {
        InputStream inputStream = null;
        ServletOutputStream out = null;
        try {
            File file = new File(request.getServletContext().getRealPath("/WEB-INF/upload"),
                "MyBatis从入门到精通.pdf");

            String filename = "mybatis入门.pdf";

            int fSize = Integer.parseInt(String.valueOf(file.length()));
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/x-download");
            response.setHeader("Accept-Ranges", "bytes");
            response.setHeader("Content-Length", String.valueOf(fSize));
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
            inputStream = new FileInputStream(file);
            long pos = 0;
            if (null != request.getHeader("Range")) {
                // 断点续传
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                try {
                    pos = Long.parseLong(request.getHeader("Range").replaceAll(
                        "bytes=", "").replaceAll("-", ""));
                } catch (NumberFormatException e) {
                    pos = 0;
                }
            }
            out = response.getOutputStream();
            String contentRange = new StringBuffer("bytes ").append(pos + "").append(
                "-").append((fSize - 1) + "").append("/").append(fSize + "").toString();
            response.setHeader("Content-Range", contentRange);
            inputStream.skip(pos);
            byte[] buffer = new byte[1024 * 10];
            int length = 0;
            while ((length = inputStream.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, length);
                Thread.sleep(100);
            }
            out.flush();
        } catch (Exception e) {
            logger.error("软件下载异常：" + e);
        } finally {
            try {
                if (null != out) {
                    out.close();
                }
                if (null != inputStream) {
                    inputStream.close();
                }
            } catch (IOException e) {
            }
        }
    }
}
