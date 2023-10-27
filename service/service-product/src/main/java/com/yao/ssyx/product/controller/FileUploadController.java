package com.yao.ssyx.product.controller;

import com.yao.ssyx.common.result.Result;
import com.yao.ssyx.product.service.FileUploadService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @className: FileUploadController
 * @Description: TODO
 * @author: long
 * @date: 2023-10-19 11:25
 */
@Api(tags = "文件上传接口")
@RestController
@RequestMapping("admin/product")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    //文件上传
    @PostMapping("fileUpload")
    public Result fileUpload(MultipartFile file) throws Exception{
        return Result.ok(fileUploadService.fileUpload(file));
    }

}
