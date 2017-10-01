package com.study.spring.dto;

import java.util.List;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created on 2017-10-01
 *
 * @author liuzhaoyuan
 */
@Data
public class FileImage {

    private String name;
    private String type;
    private List<MultipartFile> images;

}
