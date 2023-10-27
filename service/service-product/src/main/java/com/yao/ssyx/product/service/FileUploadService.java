package com.yao.ssyx.product.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @className: FileUploadService
 * @Description: TODO
 * @author: long
 * @date: 2023-10-19 11:26
 */
public interface FileUploadService {

    //文件上传
    String fileUpload(MultipartFile file) throws Exception;

}
